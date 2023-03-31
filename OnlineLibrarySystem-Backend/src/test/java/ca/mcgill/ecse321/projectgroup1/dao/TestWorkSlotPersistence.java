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
import ca.mcgill.ecse321.projectgroup1.model.Employee;
import ca.mcgill.ecse321.projectgroup1.model.Library;
import ca.mcgill.ecse321.projectgroup1.model.WorkSlot;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestWorkSlotPersistence {
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
     * Tests creation, saving of a WorkSlot, and retrieving its attributes
     * 
     * @author Simon
     */
    @Test
    public void testPersistAndLoadWorkSlot1() {
        // initiate all variables at the beginning of the test
        WorkSlot work = new WorkSlot();
        Date startDate = Date.valueOf("2021-10-26");
        Date endDate = Date.valueOf("2021-10-27");
        Time startTime = Time.valueOf("00:00:00");
        Time endTime = Time.valueOf("12:00:00");

        Employee employee = new Employee();
        Library library = new Library();
        String name = "library";

        library.setName(name);
        libraryRepository.save(library);
        employee.setLibrary(library);
        employeeRepository.save(employee);

        work.setStartDate(startDate);
        work.setEndDate(endDate);
        work.setStartTime(startTime);
        work.setEndTime(endTime);
        work.setEmployee(employee);
        workSlotRepository.save(work);
        int workSlotId = work.getId();
        // nullify variable
        work = null;
        // get item from repository
        work = workSlotRepository.findById(workSlotId);
        // throughly test this part
        assertEquals(workSlotId, work.getId());
        assertEquals(startDate, work.getStartDate());
        assertEquals(endDate, work.getEndDate());
        assertEquals(startTime, work.getStartTime());
        assertEquals(endTime, work.getEndTime());
    }

    /**
     * Second test for WorkSlot, testing with regards to Employee
     * 
     * @author Simon
     */
    @Test
    public void testPersistAndLoadWorkSlot2() {
        // initiate all variables at the beginning of the test
        WorkSlot work = new WorkSlot();

        Employee employee = new Employee();
        Library library = new Library();
        String name = "library";

        library.setName(name);
        libraryRepository.save(library);

        employee.setLibrary(library);
        employeeRepository.save(employee);
        int employeeId = employee.getId();
        
        work.setEmployee(employee);
        workSlotRepository.save(work);

        List<WorkSlot> workSlots = new ArrayList<WorkSlot>();
        workSlots.add(work);

        List<WorkSlot> testWorks = new ArrayList<WorkSlot>();
        testWorks = workSlotRepository.findWorkSlotByEmployeeId(employeeId);
        // get item from repository
        assertEquals(workSlots.get(0).getId(), testWorks.get(0).getId());
    }
}