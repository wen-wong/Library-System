package ca.mcgill.ecse321.projectgroup1.dao;

// Import Assertion methods
import static org.junit.jupiter.api.Assertions.assertEquals;
// Import Java packages
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.Time;

// Import Spring Persistence Tags
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
// Import Model Classes
// import ca.mcgill.ecse321.projectgroup1.model.Account;
import ca.mcgill.ecse321.projectgroup1.model.Library;
import ca.mcgill.ecse321.projectgroup1.model.HolidaySlot;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestHolidaySlotPersistence {
    // All instances of the Persistence Layer
    @Autowired
    private InUseSlotRepository inUseSlotRepository;
    @Autowired
    private BusinessHoursRepository businessHoursRepository;
    @Autowired
    private HolidaySlotRepository holidaySlotRepository;
    @Autowired
    private WorkSlotRepository workSlotRepository;
    @Autowired
    private EventSlotRepository eventSlotRepository;
    @Autowired
    private LibraryItemRepository libraryItemRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private NewspaperRepository newspaperRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MusicAlbumRepository musicAlbumRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private LibraryRepository libraryRepository;

    // Clear all existing data in the repositories
    @AfterEach
    public void clearDatabase() {
        businessHoursRepository.deleteAll();
        inUseSlotRepository.deleteAll();
        // Remove all library item instances
        libraryItemRepository.deleteAll();
        bookRepository.deleteAll();
        newspaperRepository.deleteAll();
        movieRepository.deleteAll();
        musicAlbumRepository.deleteAll();
        businessHoursRepository.deleteAll();
        // Remove all instances related to events, holidays, and business hours
        holidaySlotRepository.deleteAll();
        workSlotRepository.deleteAll();
        eventSlotRepository.deleteAll();
        // Remove all users' account instances
        clientRepository.deleteAll();
        employeeRepository.deleteAll();
        // Remove all library instances
        libraryRepository.deleteAll();
    }

    /**
     * Tests creation, saving of a HolidaySlot, and retrieving its attributes
     * 
     * @author Simon
     */
    @Test
    public void testPersistAndLoadHolidaySlot1() {
        // initiate all variables at the beginning of the test
        HolidaySlot holiday = new HolidaySlot();
        Date startDate = Date.valueOf("2021-10-26");
        Date endDate = Date.valueOf("2021-10-27");
        Time startTime = Time.valueOf("00:00:00");
        Time endTime = Time.valueOf("12:00:00");

        Library library = new Library();
        String name = "library";

        library.setName(name);
        libraryRepository.save(library);

        holiday.setStartDate(startDate);
        holiday.setEndDate(endDate);
        holiday.setStartTime(startTime);
        holiday.setEndTime(endTime);
        holiday.setLibrary(library);
        holidaySlotRepository.save(holiday);
        int id = holiday.getId();
        // nullify variable
        holiday = null;
        // get item from repository
        holiday = holidaySlotRepository.findById(id);
        // throughly test this part
        assertEquals(id, holiday.getId());
        assertEquals(startDate, holiday.getStartDate());
        assertEquals(endDate, holiday.getEndDate());
        assertEquals(startTime, holiday.getStartTime());
        assertEquals(endTime, holiday.getEndTime());
    }

    /**
     * Tests creation, saving of a Timeslot, and retrieving its attributes Second
     * test for HolidaySlot, testing with regards to Library
     * 
     * @author Simon
     */
    @Test
    public void testPersistAndLoadHolidaySlot2() {
        // initiate all variables at the beginning of the test
        HolidaySlot holiday = new HolidaySlot();
        Library library = new Library();
        String name = "library";

        library.setName(name);
        libraryRepository.save(library);

        holiday.setLibrary(library);
        holidaySlotRepository.save(holiday);
        List<HolidaySlot> holidays = new ArrayList<HolidaySlot>();
        holidays.add(holiday);

        // get item from repository
        List<HolidaySlot> testHolidays = holidaySlotRepository.findByLibraryName(name);

        assertEquals(holidays.get(0).getId(), testHolidays.get(0).getId());
    }
}