package ca.mcgill.ecse321.projectgroup1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.time.LocalDate;

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
 * TestManageLibraryService - All tests that cover the use case of managing the
 * library.
 * 
 * @author Brandon
 */
@ExtendWith(MockitoExtension.class)
public class TestManageLibraryService {

	@Mock
	private LibraryRepository libraryDao;
	@Mock
	private BusinessHoursRepository businessHoursDao;
	@Mock
	private HolidaySlotRepository holidaySlotDao;

	@InjectMocks
	private ManageLibraryService service;

	@InjectMocks
	private ViewLibraryService view;

	private static final String LIBRARY_KEY = "TestLibrary";
	private static final String ADDRESS_KEY = "Library Street";
	private static final String PHONENUMBER_KEY = "514-123-1234";
	private static final String EMAIL_KEY = "library@mail.ca";

	private static final DayOfWeek BUSINESSHOURS_KEY = DayOfWeek.MONDAY;

	private static final int HOLIDAYSLOT_KEY = 1;

	private static final LocalDate STARTDATE_KEY = LocalDate.of(2021, 12, 25);
	private static final LocalDate ENDDATE_KEY = LocalDate.of(2021, 12, 31);

	private static final LocalTime STARTTIME_KEY = LocalTime.of(9, 0);
	private static final LocalTime ENDTIME_KEY = LocalTime.of(18, 0);

	@BeforeEach
	public void setMockOutput() {
		// findByName query method from the LibraryRepository
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

		// findByDayOfWeek query method from the BusinessHoursRepository
		lenient().when(businessHoursDao.findByDayOfWeek(any(DayOfWeek.class)))
				.thenAnswer((InvocationOnMock invocation) -> {
					if (invocation.getArgument(0).equals(BUSINESSHOURS_KEY)) {
						Library library = new Library();
						String address = ADDRESS_KEY;
						String phoneNumber = PHONENUMBER_KEY;
						String email = EMAIL_KEY;

						library.setName(LIBRARY_KEY);
						library.setAddress(address);
						library.setPhoneNumber(phoneNumber);
						library.setEmail(email);

						BusinessHours businessHours = new BusinessHours();
						LocalTime startTime = STARTTIME_KEY;
						LocalTime endTime = ENDTIME_KEY;

						businessHours.setDayOfWeek(BUSINESSHOURS_KEY);
						businessHours.setLibrary(library);
						businessHours.setStartTime(Time.valueOf(startTime));
						businessHours.setEndTime(Time.valueOf(endTime));

						return businessHours;
					} else {
						return null;
					}
				});

		// findById query method from the HolidaySlotRepository
		lenient().when(holidaySlotDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(HOLIDAYSLOT_KEY)) {
				Library library = new Library();
				String address = ADDRESS_KEY;
				String phoneNumber = PHONENUMBER_KEY;
				String email = EMAIL_KEY;

				library.setName(LIBRARY_KEY);
				library.setAddress(address);
				library.setPhoneNumber(phoneNumber);
				library.setEmail(email);

				HolidaySlot holidaySlot = new HolidaySlot();
				LocalDate startDate = STARTDATE_KEY;
				LocalDate endDate = ENDDATE_KEY;
				LocalTime startTime = STARTTIME_KEY;
				LocalTime endTime = ENDTIME_KEY;

				holidaySlot.setLibrary(library);
				holidaySlot.setStartDate(Date.valueOf(startDate));
				holidaySlot.setEndDate(Date.valueOf(endDate));
				holidaySlot.setStartTime(Time.valueOf(startTime));
				holidaySlot.setEndTime(Time.valueOf(endTime));

				return holidaySlot;
			} else {
				return null;
			}
		});

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};

		// save method from the Library, BusinessHours, HolidaySlot Repository
		lenient().when(libraryDao.save(any(Library.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(businessHoursDao.save(any(BusinessHours.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(holidaySlotDao.save(any(HolidaySlot.class))).thenAnswer(returnParameterAsAnswer);

		// existsByName method from the Library and HolidaySlot Repository
		lenient().when(libraryDao.existsByName(anyString())).thenReturn(true);
		lenient().when(holidaySlotDao.existsById(anyInt())).thenReturn(true);
	}

	// create a valid library
	@Test
	public void testCreateLibrary() {
		String name = "TestLibrary";
		String address = "Library Street";
		String phoneNumber = "514-123-1234";
		String email = "library@mail.ca";

		Library library = null;

		try {
			library = service.createLibrary(name, address, phoneNumber, email);
		} catch (IllegalArgumentException error) {
			fail();
		}

		assertNotNull(library);
		assertEquals(name, library.getName());
		assertEquals(address, library.getAddress());
		assertEquals(phoneNumber, library.getPhoneNumber());
		assertEquals(email, library.getEmail());
	}

	// create a library with empty arguments
	@Test
	public void testCreateLibraryEmptyArgument() {
		String name = "";
		String address = "Library Street";
		String phoneNumber = "514-123-1234";
		String email = "library@mail.ca";

		Library library = null;
		String error = null;

		try {
			library = service.createLibrary(name, address, phoneNumber, email);
		} catch (IllegalArgumentException errMessage) {
			error = errMessage.getMessage();
		}
		
		assertNull(library);
		assertTrue(error.contains("Library name cannot be empty."));
	}

	// create a library with null arguments
	@Test
	public void testCreateLibraryNullArgument() {
		String name = null;
		String address = "Library Street";
		String phoneNumber = "514-123-1234";
		String email = "library@mail.ca";

		Library library = null;
		String error = null;

		try {
			library = service.createLibrary(name, address, phoneNumber, email);
		} catch (IllegalArgumentException errMessage) {
			error = errMessage.getMessage();
		}

		assertNull(library);
		assertTrue(error.contains("Library name cannot be empty."));
	}

	// edit library with valid arguments
	@Test
	public void testEditLibrary() {
		String name = "TestLibrary";
		String address = "Library Street";
		String phoneNumber = "514-123-1234";
		String email = "library@mail.ca";
		Library library = view.getLibrary(name);
		Library editedLib = null;

		try {
			editedLib = service.editLibrary(name, address, phoneNumber, email);
		} catch (IllegalArgumentException error) {
			fail();
		}

		assertNotNull(library);
		assertEquals(name, editedLib.getName());
		assertEquals(address, editedLib.getAddress());
		assertEquals(phoneNumber, editedLib.getPhoneNumber());
		assertEquals(email, editedLib.getEmail());
	}

	// edit library with null arguments
	@Test
	public void testEditLibraryNullArguments() {
		String name = null;
		String address = null;
		String phoneNumber = null;
		String email = null;
		Library library = null;
		String error = null;

		try {
			library = service.editLibrary(name, address, phoneNumber, email);
		} catch (IllegalArgumentException errMessage) {
			error = errMessage.getMessage();
		}

		assertFailedEditLibrary(library, error);
	}

	// edit library with empty arguments
	@Test
	public void testEditLibraryEmptyArguments() {
		String name = "";
		String address = "";
		String phoneNumber = "";
		String email = "";
		Library library = null;
		String error = null;

		try {
			library = service.editLibrary(name, address, phoneNumber, email);
		} catch (IllegalArgumentException errMessage) {
			error = errMessage.getMessage();
		}

		assertFailedEditLibrary(library, error);
	}

	// helper method that asserts all error messages for editLibrary
	public void assertFailedEditLibrary(Library library, String error) {
		assertNull(library);
		assertTrue(error.contains("Library does not exist."));
		assertTrue(error.contains("Library name cannot be empty."));
		assertTrue(error.contains("Library address cannot be empty."));
		assertTrue(error.contains("Library phone number cannot be empty."));
		assertTrue(error.contains("Library email cannot be empty."));
	}

	// create business hours with valid arguments
	@Test
	public void testCreateBusinessHours() {
		String name = "TestLibrary";
		Library library = view.getLibrary(name);

		DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
		LocalTime startTime = LocalTime.of(9, 0);
		LocalTime endTime = LocalTime.of(18, 0);

		BusinessHours businessHours = null;

		try {
			businessHours = service.createBusinessHours(library, dayOfWeek, Time.valueOf(startTime),
					Time.valueOf(endTime));
		} catch (IllegalArgumentException error) {
			fail();
		}

		assertBusinessHours(businessHours, library, dayOfWeek, startTime, endTime);
	}

	// create business hours with a library that does not exists
	@Test
	public void testCreateBusinessHoursLibraryNotExist() {
		Library library = new Library();

		DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
		LocalTime startTime = LocalTime.of(9, 0);
		LocalTime endTime = LocalTime.of(18, 0);

		BusinessHours businessHours = null;
		String error = null;

		try {
			businessHours = service.createBusinessHours(library, dayOfWeek, Time.valueOf(startTime),
					Time.valueOf(endTime));
		} catch (IllegalArgumentException errMessage) {
			error = errMessage.getMessage();
		}

		assertNull(businessHours);
		assertTrue(error.contains("Library does not exist."));
	}

	// create business hours with wrong arguments
	@Test
	public void testCreateBusinessHoursWrongArguments() {
		Library library = new Library();

		DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
		LocalTime startTime = LocalTime.of(10, 0);
		LocalTime endTime = LocalTime.of(7, 0);

		BusinessHours businessHours = null;
		String error = null;

		try {
			businessHours = service.createBusinessHours(library, dayOfWeek, Time.valueOf(startTime),
					Time.valueOf(endTime));
		} catch (IllegalArgumentException errMessage) {
			error = errMessage.getMessage();
		}

		assertNull(businessHours);
		assertTrue(error.contains("Business hour end time cannot be before business hour start time."));
	}

	// create business hours with null arguments
	@Test
	public void testCreateBusinessHoursNullArguments() {

		DayOfWeek dayOfWeek = null;
		Time startTime = null;
		Time endTime = null;

		BusinessHours businessHours = null;
		Library library = null;
		String error = null;

		try {
			businessHours = service.createBusinessHours(library, dayOfWeek, startTime, endTime);
		} catch (IllegalArgumentException errMessage) {
			error = errMessage.getMessage();
		}

		assertNull(businessHours);
		assertTrue(error.contains("Library needs to be selected to create the business hours."));
		assertTrue(error.contains("Day of the week needs to be selected to create the business hours."));
		assertTrue(error.contains("Business hour's start time cannot be empty."));
		assertTrue(error.contains("Business hour's end time cannot be empty."));
	}

	// edit business hours with valid arguments
	@Test
	public void testEditBusinessHours() {
		String name = "TestLibrary";
		Library library = view.getLibrary(name);

		DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
		LocalTime startTime = LocalTime.of(9, 0);
		LocalTime endTime = LocalTime.of(18, 0);

		BusinessHours businessHours = null;

		try {
			businessHours = service.editBusinessHours(dayOfWeek, Time.valueOf(startTime), Time.valueOf(endTime));
		} catch (IllegalArgumentException error) {
			fail();
		}

		assertBusinessHours(businessHours, library, dayOfWeek, startTime, endTime);

	}

	// edit business hours with wrong arguments
	@Test
	public void testEditBusinessHoursWrongArguments() {
		DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
		LocalTime startTime = LocalTime.of(10, 0);
		LocalTime endTime = LocalTime.of(7, 0);

		BusinessHours businessHours = null;
		String error = null;

		try {
			businessHours = service.editBusinessHours(dayOfWeek, Time.valueOf(startTime), Time.valueOf(endTime));
		} catch (IllegalArgumentException errMessage) {
			error = errMessage.getMessage();
		}

		assertNull(businessHours);
		assertTrue(error.contains("Business hour end time cannot be before business hour start time."));
	}

	// edit business hours with null arguments
	@Test
	public void testEditBusinessHoursNullParameters() {

		DayOfWeek dayOfWeek = null;
		Time startTime = null;
		Time endTime = null;

		BusinessHours businessHours = null;
		String error = null;

		try {
			businessHours = service.editBusinessHours(dayOfWeek, startTime, endTime);
		} catch (IllegalArgumentException errMessage) {
			error = errMessage.getMessage();
		}

		assertNull(businessHours);
		assertTrue(error.contains("Business hour does not exist."));
		assertTrue(error.contains("Business hour's start time cannot be empty."));
		assertTrue(error.contains("Business hour's end time cannot be empty."));
	}

	// get business hours with a valid argument
	@Test
	public void testGetBusinessHours() {
		String name = "TestLibrary";
		Library library = view.getLibrary(name);

		DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
		LocalTime startTime = LocalTime.of(9, 0);
		LocalTime endTime = LocalTime.of(18, 0);

		BusinessHours businessHours = null;

		try {
			businessHours = service.getBusinessHours(dayOfWeek);
		} catch (IllegalArgumentException error) {
			fail();
		}

		assertBusinessHours(businessHours, library, dayOfWeek, startTime, endTime);
	}

	// helper method that asserts all attributes of a BusinessHours instance
	public void assertBusinessHours(BusinessHours businessHours, Library library, DayOfWeek dayOfWeek,
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

	// get business hours with a null argument
	@Test
	public void testGetBusinessHoursNull() {

		DayOfWeek dayOfWeek = null;
		BusinessHours businessHours = null;
		String error = null;

		try {
			businessHours = service.getBusinessHours(dayOfWeek);
		} catch (IllegalArgumentException errMessage) {
			error = errMessage.getMessage();
		}

		assertNull(businessHours);
		assertTrue(error.contains("Day of the week needs to be selected to get the business hours."));
	}

	// create holiday slot with valid arguments
	@Test
	public void testCreateHolidaySlot() {
		String name = "TestLibrary";
		Library library = view.getLibrary(name);

		LocalDate startDate = LocalDate.of(2021, 12, 25);
		LocalDate endDate = LocalDate.of(2021, 12, 31);
		LocalTime startTime = LocalTime.of(9, 0);
		LocalTime endTime = LocalTime.of(18, 0);

		HolidaySlot holidaySlot = null;

		try {
			holidaySlot = service.createHolidaySlot(library, Date.valueOf(startDate), Date.valueOf(endDate),
					Time.valueOf(startTime), Time.valueOf(endTime));
		} catch (IllegalArgumentException error) {
			fail();
		}

		assertHolidaySlot(holidaySlot, library, startDate, endDate, startTime, endTime);
	}

	// create holiday slot with a library that does not exists
	@Test
	public void testCreateHolidaySlotLibraryNotExist() {
		Library library = new Library();

		LocalDate startDate = LocalDate.of(2021, 12, 25);
		LocalDate endDate = LocalDate.of(2021, 12, 31);
		LocalTime startTime = LocalTime.of(9, 0);
		LocalTime endTime = LocalTime.of(18, 0);

		HolidaySlot holidaySlot = null;
		String error = null;

		try {
			holidaySlot = service.createHolidaySlot(library, Date.valueOf(startDate), Date.valueOf(endDate),
					Time.valueOf(startTime), Time.valueOf(endTime));
		} catch (IllegalArgumentException errMessage) {
			error = errMessage.getMessage();
		}

		assertNull(holidaySlot);
		assertTrue(error.contains("Library does not exist."));
	}

	// create holiday slot with wrong arguments
	@Test
	public void testCreateHolidaySlotWrongArguments() {
		Library library = new Library();

		LocalDate startDate = LocalDate.of(2021, 12, 25);
		LocalDate endDate = LocalDate.of(2021, 1, 31);
		LocalTime startTime = LocalTime.of(23, 0);
		LocalTime endTime = LocalTime.of(16, 0);

		HolidaySlot holidaySlot = null;
		String error = null;

		try {
			holidaySlot = service.createHolidaySlot(library, Date.valueOf(startDate), Date.valueOf(endDate),
					Time.valueOf(startTime), Time.valueOf(endTime));
		} catch (IllegalArgumentException errMessage) {
			error = errMessage.getMessage();
		}

		assertNull(holidaySlot);
		assertTrue(error.contains("Holiday slot end date cannot be before holiday slot start date."));
		assertTrue(error.contains("Holiday slot end time cannot be before holiday slot start time."));
	}

	// create holiday slot with null arguments
	@Test
	public void testCreateHolidaySlotNullArguments() {

		Date startDate = null;
		Date endDate = null;
		Time startTime = null;
		Time endTime = null;

		HolidaySlot holidaySlot = null;
		Library library = null;
		String error = null;

		try {
			holidaySlot = service.createHolidaySlot(library, startDate, endDate, startTime, endTime);
		} catch (IllegalArgumentException errMessage) {
			error = errMessage.getMessage();
		}

		assertNull(holidaySlot);
		assertTrue(error.contains("Library needs to be selected to create the holiday slot."));
		assertTrue(error.contains("Holiday slot's start date cannot be empty."));
		assertTrue(error.contains("Holiday slot's end date cannot  be empty."));
		assertTrue(error.contains("Holiday slot's start time cannot be empty."));
		assertTrue(error.contains("Holiday slot's end time cannot be empty."));
	}

	// edit holiday slot with valid arguments
	@Test
	public void testEditHolidaySlot() {
		String name = "TestLibrary";
		Library library = view.getLibrary(name);

		LocalDate startDate = LocalDate.of(2021, 03, 01);
		LocalDate endDate = LocalDate.of(2021, 03, 06);
		LocalTime startTime = LocalTime.of(0, 0);
		LocalTime endTime = LocalTime.of(23, 0);

		HolidaySlot holidaySlot = null;

		try {
			holidaySlot = service.editHolidaySlot(HOLIDAYSLOT_KEY, Date.valueOf(startDate), Date.valueOf(endDate),
					Time.valueOf(startTime), Time.valueOf(endTime));
		} catch (IllegalArgumentException error) {
			fail();
		}

		assertHolidaySlot(holidaySlot, library, startDate, endDate, startTime, endTime);
	}

	// edit holiday slot with wrong arguments
	@Test
	public void testEditHolidaySlotWrongArguments() {
		LocalDate startDate = LocalDate.of(2021, 12, 25);
		LocalDate endDate = LocalDate.of(2020, 1, 31);
		LocalTime startTime = LocalTime.of(23, 0);
		LocalTime endTime = LocalTime.of(16, 0);

		HolidaySlot holidaySlot = null;
		String error = null;

		try {
			holidaySlot = service.editHolidaySlot(HOLIDAYSLOT_KEY, Date.valueOf(startDate), Date.valueOf(endDate),
					Time.valueOf(startTime), Time.valueOf(endTime));
		} catch (IllegalArgumentException errMessage) {
			error = errMessage.getMessage();
		}

		assertNull(holidaySlot);
		assertTrue(error.contains("Holiday slot end date cannot be before holiday slot start date."));
		assertTrue(error.contains("Holiday slot end time cannot be before holiday slot start time."));
	}

	// edit holiday slot with null arguments
	@Test
	public void testEditHolidaySlotNullArguments() {

		Date startDate = null;
		Date endDate = null;
		Time startTime = null;
		Time endTime = null;

		HolidaySlot holidaySlot = null;
		String error = null;

		try {
			holidaySlot = service.editHolidaySlot(HOLIDAYSLOT_KEY, startDate, endDate, startTime, endTime);
		} catch (IllegalArgumentException errMessage) {
			error = errMessage.getMessage();
		}

		assertNull(holidaySlot);
		assertTrue(error.contains("Holiday slot's start date cannot be empty."));
		assertTrue(error.contains("Holiday slot's end date cannot  be empty."));
		assertTrue(error.contains("Holiday slot's start time cannot be empty."));
		assertTrue(error.contains("Holiday slot's end time cannot be empty."));
	}

	// delete holiday slot with a valid argument
	@Test
	public void testDeleteHolidaySlot() {
		String name = "TestLibrary";
		Library library = view.getLibrary(name);

		LocalDate startDate = LocalDate.of(2021, 12, 25);
		LocalDate endDate = LocalDate.of(2021, 12, 31);
		LocalTime startTime = LocalTime.of(9, 0);
		LocalTime endTime = LocalTime.of(18, 0);

		HolidaySlot holidaySlot = null;

		try {
			holidaySlot = service.deleteHolidaySlot(HOLIDAYSLOT_KEY);
		} catch (IllegalArgumentException errMessage) {
			fail();
		}

		assertHolidaySlot(holidaySlot, library, startDate, endDate, startTime, endTime);
	}

	// get holiday slot with a valid argument
	@Test
	public void testGetHolidaySlot() {
		String name = "TestLibrary";
		Library library = view.getLibrary(name);

		LocalDate startDate = LocalDate.of(2021, 12, 25);
		LocalDate endDate = LocalDate.of(2021, 12, 31);
		LocalTime startTime = LocalTime.of(9, 0);
		LocalTime endTime = LocalTime.of(18, 0);

		HolidaySlot holidaySlot = null;

		try {
			holidaySlot = service.getHolidaySlot(HOLIDAYSLOT_KEY);
		} catch (IllegalArgumentException error) {
			fail();
		}

		assertHolidaySlot(holidaySlot, library, startDate, endDate, startTime, endTime);
	}

	// helper method that asserts all attributes of a HolidaySlot instance
	public void assertHolidaySlot(HolidaySlot holidaySlot, Library library, LocalDate startDate, LocalDate endDate,
			LocalTime startTime, LocalTime endTime) {
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

	// get holiday slot with a library that does not exists
	@Test
	public void testGetHolidaySlotLibraryNotExists() {
		HolidaySlot holidaySlot = null;
		int id = 123;

		try {
			holidaySlot = service.getHolidaySlot(id);
		} catch (IllegalArgumentException errMessage) {
			fail();
		}

		assertNull(holidaySlot);
	}
}