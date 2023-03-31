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
import ca.mcgill.ecse321.projectgroup1.model.Client;
import ca.mcgill.ecse321.projectgroup1.model.EventSlot;
import ca.mcgill.ecse321.projectgroup1.model.Library;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestEventSlotPersistence {
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
     * Tests creation, saving of a EventSlot, and retrieving its attributes
     * 
     * @author Simon
     */
    @Test
    public void testPersistAndLoadEventSlot1() {
        // initiate all variables at the beginning of the test
        EventSlot event = new EventSlot();
        Client client = new Client();
        // int clientId = 2;
        Date startDate = Date.valueOf("2021-10-26");
        Date endDate = Date.valueOf("2021-10-27");
        Time startTime = Time.valueOf("00:00:00");
        Time endTime = Time.valueOf("12:00:00");
        String description = "This is an event";

        Library library = new Library();
        String name = "library";

        library.setName(name);
        libraryRepository.save(library);

        client.setLibrary(library);
        clientRepository.save(client);
        // client.setId(clientId);

        event.setClient(client);
        event.setStartDate(startDate);
        event.setEndDate(endDate);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setDescription(description);
        eventSlotRepository.save(event);
        int id = event.getId();

        // nullify variable
        event = null;
        // get item from repository
        event = eventSlotRepository.findById(id);
        // throughly test this part
        assertEquals(id, event.getId());
        assertEquals(startDate, event.getStartDate());
        assertEquals(endDate, event.getEndDate());
        assertEquals(startTime, event.getStartTime());
        assertEquals(endTime, event.getEndTime());
        assertEquals(description, event.getDescription());
    }

    /**
     * Second test for EventSlot, testing with regards to Client
     * 
     * @author Simon
     */
    @Test
    public void testPersistAndLoadEventSlot2() {
        // initiate all variables at the beginning of the test
        EventSlot event = new EventSlot();
        List<EventSlot> events = new ArrayList<EventSlot>();
        Library library = new Library();
        String name = "library";
        Client client = new Client();

        library.setName(name);
        libraryRepository.save(library);
        client.setLibrary(library);
        clientRepository.save(client);

        event.setClient(client);
        events.add(event);
        eventSlotRepository.save(event);
        int id = client.getId();

        List<EventSlot> testEvents = new ArrayList<EventSlot>();
        // get item from repository
        testEvents = eventSlotRepository.findEventSlotByClientId(id);

        assertEquals(events.get(0).getId(), testEvents.get(0).getId());
    }
}