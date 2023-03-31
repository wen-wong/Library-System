package ca.mcgill.ecse321.projectgroup1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.projectgroup1.dao.EmployeeRepository;
import ca.mcgill.ecse321.projectgroup1.dao.LibraryRepository;
import ca.mcgill.ecse321.projectgroup1.dao.WorkSlotRepository;
import ca.mcgill.ecse321.projectgroup1.model.Employee;
import ca.mcgill.ecse321.projectgroup1.model.Library;
import ca.mcgill.ecse321.projectgroup1.model.WorkSlot;



@ExtendWith(MockitoExtension.class)
public class TestManageScheduleService {
    @Mock
    private EmployeeRepository employeeDao;

    @Mock
    private WorkSlotRepository workSlotDao;

    @Mock 
    private LibraryRepository libraryDao;

	@InjectMocks
	private ManageScheduleService scheduleService;

    @InjectMocks
	private ManageAccountService accountService;

    private static final String LIBRARY_KEY = "TestLibrary";
    private static final String ADDRESS_KEY = "Library Street";
	private static final String PHONENUMBER_KEY = "514-123-1234";
	private static final String EMAIL_KEY = "library@mail.ca";
    
    private static final int SCHEDULE_KEY1 = 1;
    
	private static final Date START_DATE = Date.valueOf(("2021-11-10"));
    private static final Date END_DATE = Date.valueOf(("2021-11-15"));
    private static final Time START_TIME = Time.valueOf("12:00:00");
    private static final Time END_TIME = Time.valueOf("18:00:00");

    private static final int EMPLOYEE_ID1 = 1;
    

	@BeforeEach
    public void setMockOutput() {
		
		 // Library needed to create an Employee
		 Library library = new Library();
		 library.setName("library");
		 String address = ADDRESS_KEY;
		 String phoneNumber = PHONENUMBER_KEY;
		 String email = EMAIL_KEY;
		 library.setName(LIBRARY_KEY);
		 library.setAddress(address);
		 library.setPhoneNumber(phoneNumber);
		 library.setEmail(email);
 
		 // Employee needed to create a work slot
		 Employee employee1 = new Employee();
		 employee1.setId(EMPLOYEE_ID1);
		 employee1.setLibrary(library);

		 // Creating valid work slots
		 WorkSlot workSlot1 = new WorkSlot();
		 
		 workSlot1.setEmployee(employee1); 
		 workSlot1.setId(SCHEDULE_KEY1);
		 workSlot1.setStartDate(START_DATE);
		 workSlot1.setEndDate(END_DATE);
		 workSlot1.setStartTime(START_TIME);
		 workSlot1.setEndTime(END_TIME);

		 // Dummy workslot that will be used to attempt the creation of invalid work slots
		 WorkSlot workSlotOverlap = new WorkSlot();
		 workSlotOverlap.setEmployee(employee1); 
		 workSlotOverlap.setId(EMPLOYEE_ID1);
 
		 // List of all the schedules
		 List<WorkSlot> allSchedules = new ArrayList<WorkSlot>();
		 allSchedules.add(workSlot1);


    	// Simulating repository methods
		lenient().when(libraryDao.findByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(LIBRARY_KEY)) {
                return library;
            } else {
                return null;
            }
        });

		lenient().when(employeeDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(EMPLOYEE_ID1)) {
                return employee1;
            } else {
                return null;
            }
        });

        lenient().when(employeeDao.existsById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(EMPLOYEE_ID1)) {
                return true;
            } else {
                return false;
            }
        });
        
        lenient().when(workSlotDao.existsById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(SCHEDULE_KEY1)) {
                return true;
            } else {
                return false;
            }
        });

        lenient().when(workSlotDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(EMPLOYEE_ID1)) {
                return workSlot1;
            } else {
                return null;
            }
        });


        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        
		// Return an object instance whenever it is saved
        lenient().when(libraryDao.save(any(Library.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(employeeDao.save(any(Employee.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(workSlotDao.save(any(WorkSlot.class))).thenAnswer(returnParameterAsAnswer);		
    }

	/**
     * The test successfully creates a work slot.
     * 
     * @author Chloe
     */
    @Test
	public void testCreateWorkSlot() {

        Employee employee = employeeDao.findById(EMPLOYEE_ID1);
    	WorkSlot workSlot = null;
        Date startDate = Date.valueOf(("2022-11-11"));
        Date endDate = Date.valueOf(("2022-11-14"));  
        Time startTime = Time.valueOf("12:00:00");
        Time endTime = Time.valueOf("18:00:00");
    

        try {
            workSlot = scheduleService.createSchedule(employee, startTime, endTime, startDate, endDate);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(workSlot);
        assertEquals(startDate, workSlot.getStartDate());
        assertEquals(endDate, workSlot.getEndDate());
        assertEquals(startTime, workSlot.getStartTime());
        assertEquals(endTime, workSlot.getEndTime());
	}

	 /**
     * The test fails to create a work slot with all the attributes set to null.
     * 
     * @author Chloe
     */
    @Test
    public void testCreateWorkSlotNull() {
        Employee employee = null;
	    WorkSlot workSlot = null;
        Date startDate = null;
        Date endDate = null;
        Time startTime = null;
        Time endTime = null;

        String error = null;

        try {
            workSlot = scheduleService.createSchedule(employee, startTime, endTime, startDate, endDate);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(workSlot);
        assertTrue(error.contains("Employee must exist to create a schedule!"));
    }

	/**
     * The test fails to create a schedule because there is no selected employee.
     * 
     * @author Chloe
     */
	@Test
	public void testEmployeeNull() {
		Employee employee = null;
		Date startDate = Date.valueOf("2022-11-12");
        Date endDate = Date.valueOf(("2022-11-12"));
        Time startTime = Time.valueOf("08:00:00");
        Time endTime = Time.valueOf("13:00:00");

		String error = null;
		WorkSlot workSlot = null;
		
        try {
            workSlot = scheduleService.createSchedule(employee, startTime, endTime, startDate, endDate);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
	    assertNull(workSlot);
		// check error
		assertEquals("Employee must exist to create a schedule!",error);
    }

	/**
     * The test fails to create a schedule because there is no start Date.
     * 
     * @author Chloe
     */
	@Test
	public void testStartDateNull() {
		Employee employee = new Employee();
		Date startDate =null;
        Date endDate = Date.valueOf(("2022-11-12"));
        Time startTime = Time.valueOf("08:00:00");
        Time endTime = Time.valueOf("13:00:00");
       
  
		String error = null;
		WorkSlot workSlot = null;
		
        try {
            workSlot = scheduleService.createSchedule(employee, startTime, endTime, startDate, endDate);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
	    assertNull(workSlot);
		// check error
		assertTrue(error.contains("Schedule needs a start date!"));
    }

	/**
     * The test fails to create a schedule because there is no end Date.
     * 
     * @author Chloe
     */
	@Test
	public void testEndDateNull() {
		Employee employee = new Employee();
		Date endDate =null;
        Date startDate = Date.valueOf(("2022-11-12"));
        Time startTime = Time.valueOf("08:00:00");
        Time endTime = Time.valueOf("13:00:00");
        
  
		String error = null;
		WorkSlot workSlot = null;
		
        try {
            workSlot = scheduleService.createSchedule(employee, startTime, endTime, startDate, endDate);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
	    assertNull(workSlot);
		// check error
	    assertTrue(error.contains("Schedule needs an end date!"));
    }

	/**
     * The test fails to create a schedule because there is no start time.
     * 
     * @author Chloe
     */
	@Test
	public void testStartTimeNull() {
		Employee employee = new Employee();
		Date startDate = Date.valueOf(("2022-11-12"));
        Date endDate = Date.valueOf(("2022-11-12"));
        Time startTime = null;
        Time endTime = Time.valueOf("13:00:00");
       
  
		String error = null;
		WorkSlot workSlot = null;
		
        try {
            workSlot = scheduleService.createSchedule(employee, startTime, endTime, startDate, endDate);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
	    assertNull(workSlot);
		// check error
	    assertTrue(error.contains("Schedule needs a start time!"));
    }

	/**
     * The test fails to create a schedule because there is no end time.
     * 
     * @author Chloe
     */
	@Test
	public void testEndTimeNull() {
		Employee employee = new Employee();
		Date startDate = Date.valueOf(("2022-11-12"));
        Date endDate = Date.valueOf(("2022-11-12"));
        Time endTime = null;
        Time startTime = Time.valueOf("13:00:00");
        //String name = "Jenny";
  
		String error = null;
		WorkSlot workSlot = null;
		
        try {
            workSlot = scheduleService.createSchedule(employee, startTime, endTime, startDate, endDate);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
	    assertNull(workSlot);
		// check error
	    assertTrue(error.contains("Schedule needs an end time!"));
    }



	 /**
     * The test fails to create a schedule because of the start date is after the end date.
     * 
     * @author Chloe
     */
    @Test
    public void testCreateScheduleInvalidDates() {
        String error = null;
        Date newStartDate = Date.valueOf("2022-11-12");
        Date newEndDate = Date.valueOf(("2022-11-11"));
        Time newStartTime = Time.valueOf("00:00:00");
        Time newEndTime = Time.valueOf("18:00:00");
        Employee employee = employeeDao.findById(EMPLOYEE_ID1);

        try {
            scheduleService.createSchedule(employee,  newStartTime, newEndTime, newStartDate, newEndDate);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals("schedule end date cannot be before schedule start date.",error);
    }

	/**
     * The test fails to create a schedule because of the start time is after the end time.
     * 
     * @author Chloe
     */
    @Test
    public void testCreateScheduleInvalidTimes() {
        String error = null;
        Date newStartDate = Date.valueOf("2022-11-12");
        Date newEndDate = Date.valueOf(("2022-11-12"));
        Time newStartTime = Time.valueOf("05:00:00");
        Time newEndTime = Time.valueOf("04:00:00");
        Employee employee = employeeDao.findById(EMPLOYEE_ID1);

        try {
            scheduleService.createSchedule(employee,  newStartTime, newEndTime, newStartDate, newEndDate);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals("schedule end time cannot be before schedule start time.",error);
    }
    
    public void assertWorkSlot(WorkSlot workSlot, Employee employee, Time startTime, Time endTime, Date startDate, Date endDate) {
		assertNotNull(workSlot);
        assertEquals(employee.getName(), workSlot.getEmployee().getName());
        assertEquals(employee.getId(), workSlot.getEmployee().getId());
        assertEquals(employee.getPassword(), workSlot.getEmployee().getPassword());
        assertEquals(employee.getPhoneNumber(), workSlot.getEmployee().getPhoneNumber());
        assertEquals(employee.getEmail(), workSlot.getEmployee().getEmail());
        assertEquals(employee.getAddress(), workSlot.getEmployee().getAddress());
        assertEquals(employee.getTypeOfEmployee(), workSlot.getEmployee().getTypeOfEmployee());
        assertEquals(startDate, workSlot.getStartDate());
		assertEquals(endDate, workSlot.getEndDate());
		assertEquals(startTime, workSlot.getStartTime());
		assertEquals(endTime, workSlot.getEndTime());
	}

	/**
     * The test fails to retrieve a schedule with an id that does not exist.
     * 
     * @author Chloe
     */

	@Test
	public void testGetScheduleNotExists() {        
        WorkSlot workSlot = null;
        int id = 123;
        
        String error = null;
        
        try {
        	workSlot = scheduleService.getSchedule(id); 
        } catch (IllegalArgumentException errMessage) {
			error = errMessage.getMessage();
		}
        
        assertNull(workSlot);
        
	}
	
	// delete holiday slot with a valid argument
		@Test
		public void testDeleteWorkSlot() {
			Employee employee = accountService.getEmployee(EMPLOYEE_ID1);


			LocalDate startDate = LocalDate.of(2021, 11, 10);
			LocalDate endDate = LocalDate.of(2021, 11, 15);
			LocalTime startTime = LocalTime.of(12, 0);
			LocalTime endTime = LocalTime.of(18, 0);

			WorkSlot workSlot = null;

			try {
				workSlot = scheduleService.deleteWorkSlot(EMPLOYEE_ID1);
			} catch (IllegalArgumentException errMessage) {
				fail();
			}

			assertWorkSlot(workSlot, employee, startDate, endDate, startTime, endTime);
		}
		
		// helper method that asserts all attributes of a HolidaySlot instance
		public void assertWorkSlot(WorkSlot workSlot, Employee employee, LocalDate startDate, LocalDate endDate,
				LocalTime startTime, LocalTime endTime) {
			assertNotNull(workSlot);
			assertEquals(employee.getName(), workSlot.getEmployee().getName());
			assertEquals(employee.getId(), workSlot.getEmployee().getId());
			assertEquals(employee.getAddress(), workSlot.getEmployee().getAddress());
			assertEquals(employee.getPhoneNumber(), workSlot.getEmployee().getPhoneNumber());
			assertEquals(employee.getPassword(), workSlot.getEmployee().getPassword());
			assertEquals(employee.getTypeOfEmployee(), workSlot.getEmployee().getTypeOfEmployee());
			assertEquals(Date.valueOf(startDate), workSlot.getStartDate());
			assertEquals(Date.valueOf(endDate), workSlot.getEndDate());
			assertEquals(Time.valueOf(startTime), workSlot.getStartTime());
			assertEquals(Time.valueOf(endTime), workSlot.getEndTime());
		}

}

