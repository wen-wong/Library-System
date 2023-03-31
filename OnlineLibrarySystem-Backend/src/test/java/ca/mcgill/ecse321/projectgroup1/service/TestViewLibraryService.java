package ca.mcgill.ecse321.projectgroup1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;
import java.time.DayOfWeek;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.projectgroup1.dao.LibraryRepository;
import ca.mcgill.ecse321.projectgroup1.dao.BusinessHoursRepository;
import ca.mcgill.ecse321.projectgroup1.dao.HolidaySlotRepository;
import ca.mcgill.ecse321.projectgroup1.model.Library;
import ca.mcgill.ecse321.projectgroup1.model.BusinessHours;
import ca.mcgill.ecse321.projectgroup1.model.HolidaySlot;

/**
 * TestViewLibraryService - All tests that cover the use case of viewing the
 * library.
 * 
 * @author Brandon
 */
@ExtendWith(MockitoExtension.class)
public class TestViewLibraryService {

    @Mock
    private LibraryRepository libraryDao;
    @Mock
    private BusinessHoursRepository businessHoursDao;
    @Mock
    private HolidaySlotRepository holidaySlotDao;

    @InjectMocks
    private ViewLibraryService service;

    private static final String LIBRARY_KEY = "TestLibrary";
    private static final String ADDRESS_KEY = "Library Street";
    private static final String PHONENUMBER_KEY = "514-123-1234";
    private static final String EMAIL_KEY = "library@mail.ca";

    private static final DayOfWeek BUSINESSHOURS_KEY1 = DayOfWeek.MONDAY;
    private static final DayOfWeek BUSINESSHOURS_KEY2 = DayOfWeek.TUESDAY;

    private static final LocalDate STARTDATE_KEY1 = LocalDate.of(2021, 12, 25);
    private static final LocalDate ENDDATE_KEY1 = LocalDate.of(2021, 12, 31);
    private static final LocalDate STARTDATE_KEY2 = LocalDate.of(2021, 03, 01);
    private static final LocalDate ENDDATE_KEY2 = LocalDate.of(2021, 03, 06);

    private static final LocalTime STARTTIME_KEY1 = LocalTime.of(9, 0);
    private static final LocalTime ENDTIME_KEY1 = LocalTime.of(18, 0);
    private static final LocalTime STARTTIME_KEY2 = LocalTime.of(10, 0);
    private static final LocalTime ENDTIME_KEY2 = LocalTime.of(15, 0);

    @BeforeEach
    public void setMockOutput() {
        // findByName query method from the libraryRepository
        // returns a Library
        lenient().when(libraryDao.findByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(LIBRARY_KEY)) {
                Library library = new Library();
                String address = ADDRESS_KEY;
                String phoneNumber = PHONENUMBER_KEY;
                String email = EMAIL_KEY;

                library.setName(LIBRARY_KEY);
                library.setAddress(address);
                library.setPhoneNumber(phoneNumber);
                library.setEmail(email);

                return library;
            } else {
                return null;
            }
        });

        // findBusinessHoursByLibraryName query method from the BusinessHoursRepository
        // returns a BusinessHours
        lenient().when(businessHoursDao.findBusinessHoursByLibraryName(anyString()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if (invocation.getArgument(0).equals(LIBRARY_KEY)) {
                        Library library = new Library();
                        String address = ADDRESS_KEY;
                        String phoneNumber = PHONENUMBER_KEY;
                        String email = EMAIL_KEY;

                        library.setName(LIBRARY_KEY);
                        library.setAddress(address);
                        library.setPhoneNumber(phoneNumber);
                        library.setEmail(email);

                        BusinessHours businessHours1 = new BusinessHours();
                        BusinessHours businessHours2 = new BusinessHours();

                        businessHours1.setDayOfWeek(BUSINESSHOURS_KEY1);
                        businessHours1.setLibrary(library);
                        businessHours1.setStartTime(Time.valueOf(STARTTIME_KEY1));
                        businessHours1.setEndTime(Time.valueOf(ENDTIME_KEY1));

                        businessHours2.setDayOfWeek(BUSINESSHOURS_KEY2);
                        businessHours2.setLibrary(library);
                        businessHours2.setStartTime(Time.valueOf(STARTTIME_KEY2));
                        businessHours2.setEndTime(Time.valueOf(ENDTIME_KEY2));

                        List<BusinessHours> allBusinessHours = new ArrayList<>();

                        allBusinessHours.add(businessHours1);
                        allBusinessHours.add(businessHours2);

                        return allBusinessHours;
                    } else {
                        return null;
                    }
                });

        // findByLibraryName query method from the HolidaySlotRepository
        // returns a HolidaySlot
        lenient().when(holidaySlotDao.findByLibraryName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(LIBRARY_KEY)) {
                Library library = new Library();
                String address = ADDRESS_KEY;
                String phoneNumber = PHONENUMBER_KEY;
                String email = EMAIL_KEY;

                library.setName(LIBRARY_KEY);
                library.setAddress(address);
                library.setPhoneNumber(phoneNumber);
                library.setEmail(email);

                HolidaySlot holidaySlot1 = new HolidaySlot();
                HolidaySlot holidaySlot2 = new HolidaySlot();

                holidaySlot1.setLibrary(library);
                holidaySlot1.setStartDate(Date.valueOf(STARTDATE_KEY1));
                holidaySlot1.setEndDate(Date.valueOf(ENDDATE_KEY1));
                holidaySlot1.setStartTime(Time.valueOf(STARTTIME_KEY1));
                holidaySlot1.setEndTime(Time.valueOf(ENDTIME_KEY1));

                holidaySlot2.setLibrary(library);
                holidaySlot2.setStartDate(Date.valueOf(STARTDATE_KEY2));
                holidaySlot2.setEndDate(Date.valueOf(ENDDATE_KEY2));
                holidaySlot2.setStartTime(Time.valueOf(STARTTIME_KEY2));
                holidaySlot2.setEndTime(Time.valueOf(ENDTIME_KEY2));

                List<HolidaySlot> allHolidaySlots = new ArrayList<>();

                allHolidaySlots.add(holidaySlot1);
                allHolidaySlots.add(holidaySlot2);

                return allHolidaySlots;
            } else {
                return null;
            }
        });

        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };

        // save Library instance to the Repository
        lenient().when(libraryDao.save(any(Library.class))).thenAnswer(returnParameterAsAnswer);

        // existsByName query method from the LibraryRepository
        lenient().when(libraryDao.existsByName(anyString())).thenReturn(true);
    }

    // Get a valid Library instance
    @Test
    public void testViewLibrary() {
        String name = "TestLibrary";
        String address = "Library Street";
        String phoneNumber = "514-123-1234";
        String email = "library@mail.ca";
        Library library = null;

        try {
            library = service.getLibrary(name);
        } catch (IllegalArgumentException error) {
            fail();
        }

        assertNotNull(library);
        assertEquals(name, library.getName());
        assertEquals(address, library.getAddress());
        assertEquals(phoneNumber, library.getPhoneNumber());
        assertEquals(email, library.getEmail());
    }

    // Get a library instance using an empty name
    @Test
    public void testViewLibraryEmptyName() {
        String name = "";
        Library library = null;
        String error = null;

        try {
            library = service.getLibrary(name);
        } catch (IllegalArgumentException errMessage) {
            error = errMessage.getMessage();
        }

        assertNull(library);
        assertEquals("Library name cannot be empty.", error);
    }

    // Get a library using a null name
    @Test
    public void testViewLibraryNullName() {
        String name = null;
        Library library = null;
        String error = null;

        try {
            library = service.getLibrary(name);
        } catch (IllegalArgumentException errMessage) {
            error = errMessage.getMessage();
        }

        assertNull(library);
        assertEquals("Library name cannot be empty.", error);
    }

    // Get a library that does not exists in the repository
    @Test
    public void testViewLibraryNull() {
        String name = "TestingLibrary";
        Library library = null;

        try {
            library = service.getLibrary(name);
        } catch (IllegalArgumentException errMessage) {
            fail();
        }

        assertNull(library);
    }

    // Get all valid business hours using the library name
    @Test
    public void testViewAllBusinessHours() {
        String name = "TestLibrary";
        Library library = service.getLibrary(name);

        DayOfWeek dayOfWeek1 = DayOfWeek.MONDAY;
        LocalTime startTime1 = LocalTime.of(9, 0);
        LocalTime endTime1 = LocalTime.of(18, 0);

        DayOfWeek dayOfWeek2 = DayOfWeek.TUESDAY;
        LocalTime startTime2 = LocalTime.of(10, 0);
        LocalTime endTime2 = LocalTime.of(15, 0);

        List<BusinessHours> allBusinessHours = new ArrayList<>();

        try {
            allBusinessHours = service.getAllBusinessHours(library);
        } catch (IllegalArgumentException error) {
            fail();
        }

        assertEquals(2, allBusinessHours.size());
        assertIndividualBusinessHours(allBusinessHours.get(0), library, dayOfWeek1, startTime1, endTime1);
        assertIndividualBusinessHours(allBusinessHours.get(1), library, dayOfWeek2, startTime2, endTime2);
    }

    // helper method that asserts all attributes of a BusinessHours instance
    public void assertIndividualBusinessHours(BusinessHours businessHours, Library library, DayOfWeek dayOfWeek,
            LocalTime startTime, LocalTime endTime) {
        assertNotNull(businessHours);
        assertEquals(library.getName(), businessHours.getLibrary().getName());
        assertEquals(library.getAddress(), businessHours.getLibrary().getAddress());
        assertEquals(library.getPhoneNumber(), businessHours.getLibrary().getPhoneNumber());
        assertEquals(library.getEmail(), businessHours.getLibrary().getEmail());
        assertEquals(dayOfWeek, businessHours.getDayOfWeek());
        assertEquals(Time.valueOf(startTime), businessHours.getStartTime());
        assertEquals(Time.valueOf(endTime), businessHours.getEndTime());
    }

    // Get all business hours with a null library
    @Test
    public void testViewAllBusinessHoursLibraryNull() {
        Library library = null;
        List<BusinessHours> allBusinessHours = new ArrayList<>();
        String error = null;

        try {
            allBusinessHours = service.getAllBusinessHours(library);
        } catch (IllegalArgumentException errMessage) {
            error = errMessage.getMessage();
        }

        assertTrue(allBusinessHours.isEmpty());
        assertTrue(error.contains("Library does not exist."));
    }

    // Get all business hours with a library that does not exist
    @Test
    public void testViewAllBusinessHoursLibraryNotExist() {
        Library library = new Library();
        List<BusinessHours> allBusinessHours = new ArrayList<>();
        String error = null;

        try {
            allBusinessHours = service.getAllBusinessHours(library);
        } catch (IllegalArgumentException errMessage) {
            error = errMessage.getMessage();
        }

        assertTrue(allBusinessHours.isEmpty());
        assertTrue(error.contains("Library does not exist."));
    }

    // Get all business hours with a library with an empty name
    @Test
    public void testViewAllBusinessHoursLibraryEmptyName() {
        String name = "";
        Library library = new Library();
        library.setName(name);

        List<BusinessHours> allBusinessHours = new ArrayList<>();
        String error = null;

        try {
            allBusinessHours = service.getAllBusinessHours(library);
        } catch (IllegalArgumentException errMessage) {
            error = errMessage.getMessage();
        }

        assertTrue(allBusinessHours.isEmpty());
        assertTrue(error.contains("Library does not exist."));
    }

    // Get all valid holiday slots with a library name
    @Test
    public void testViewAllHolidaySlot() {
        String name = "TestLibrary";
        Library library = service.getLibrary(name);

        LocalDate startDate1 = LocalDate.of(2021, 12, 25);
        LocalDate endDate1 = LocalDate.of(2021, 12, 31);
        LocalTime startTime1 = LocalTime.of(9, 0);
        LocalTime endTime1 = LocalTime.of(18, 0);

        LocalDate startDate2 = LocalDate.of(2021, 03, 01);
        LocalDate endDate2 = LocalDate.of(2021, 03, 06);
        LocalTime startTime2 = LocalTime.of(10, 0);
        LocalTime endTime2 = LocalTime.of(15, 0);

        List<HolidaySlot> allHolidaySlots = new ArrayList<>();

        try {
            allHolidaySlots = service.getAllHolidaySlots(library);
        } catch (IllegalArgumentException error) {
            fail();
        }

        assertEquals(2, allHolidaySlots.size());
        assertIndividualHolidaySlot(allHolidaySlots.get(0), library, startDate1, endDate1, startTime1, endTime1);
        assertIndividualHolidaySlot(allHolidaySlots.get(1), library, startDate2, endDate2, startTime2, endTime2);
    }

    // helper method that asserts all attributes of a HolidaySlot instance
    public void assertIndividualHolidaySlot(HolidaySlot holidaySlot, Library library, LocalDate startDate,
            LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        assertNotNull(holidaySlot);
        assertEquals(library.getName(), holidaySlot.getLibrary().getName());
        assertEquals(library.getAddress(), holidaySlot.getLibrary().getAddress());
        assertEquals(library.getPhoneNumber(), holidaySlot.getLibrary().getPhoneNumber());
        assertEquals(library.getEmail(), holidaySlot.getLibrary().getEmail());
        assertEquals(Date.valueOf(startDate), holidaySlot.getStartDate());
        assertEquals(Date.valueOf(endDate), holidaySlot.getEndDate());
        assertEquals(Time.valueOf(startTime), holidaySlot.getStartTime());
        assertEquals(Time.valueOf(endTime), holidaySlot.getEndTime());
    }

    // Get all holiday slots with a null librarys
    @Test
    public void testViewAllHolidaySlotLibraryNull() {
        Library library = null;
        List<HolidaySlot> allHolidaySlots = new ArrayList<>();
        String error = null;

        try {
            allHolidaySlots = service.getAllHolidaySlots(library);
        } catch (IllegalArgumentException errMessage) {
            error = errMessage.getMessage();
        }

        assertTrue(allHolidaySlots.isEmpty());
        assertTrue(error.contains("Library does not exist."));
    }

    // Get all holiday slots with a library that does not exist
    @Test
    public void testViewAllHolidaySlotLibraryNotExist() {
        Library library = new Library();
        List<HolidaySlot> allHolidaySlots = new ArrayList<>();
        String error = null;

        try {
            allHolidaySlots = service.getAllHolidaySlots(library);
        } catch (IllegalArgumentException errMessage) {
            error = errMessage.getMessage();
        }

        assertTrue(allHolidaySlots.isEmpty());
        assertTrue(error.contains("Library does not exist."));
    }

    // Get all holiday slots with a library with an empty name
    @Test
    public void testViewAllHolidaySlotLibraryEmptyName() {
        String name = "";
        Library library = new Library();
        library.setName(name);

        List<HolidaySlot> allHolidaySlots = new ArrayList<>();
        String error = null;

        try {
            allHolidaySlots = service.getAllHolidaySlots(library);
        } catch (IllegalArgumentException errMessage) {
            error = errMessage.getMessage();
        }

        assertTrue(allHolidaySlots.isEmpty());
        assertTrue(error.contains("Library does not exist."));
    }

}