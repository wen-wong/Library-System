package ca.mcgill.ecse321.projectgroup1.dao;

// Import Assertion methods
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
// Import Java packages
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.Time;
import java.time.DayOfWeek;

// Import Spring Persistence Tags
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
// Import Model Classes
import ca.mcgill.ecse321.projectgroup1.model.Book;
import ca.mcgill.ecse321.projectgroup1.model.BusinessHours;
import ca.mcgill.ecse321.projectgroup1.model.Client;
import ca.mcgill.ecse321.projectgroup1.model.Employee;
import ca.mcgill.ecse321.projectgroup1.model.InUseSlot;
import ca.mcgill.ecse321.projectgroup1.model.Library;
import ca.mcgill.ecse321.projectgroup1.model.Movie;
import ca.mcgill.ecse321.projectgroup1.model.MusicAlbum;
import ca.mcgill.ecse321.projectgroup1.model.WorkSlot;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestOnlineLibrarySystemPersistence {
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
     * This is the first test for the Client class to check if the Client can be
     * saved and retrieved from the system. It will check if the attributes of
     * Client remain the same.
     * 
     * @author Philippe Shi
     */
    @Test
    public void testPersistAndLoadClient1() {
        // Initiate all variables
        Client client = new Client();
        int numOfFlags = 3;
        Boolean isResident = true;
        String clientName = "Bob";
        String password = "StrongPassword";
        String address = "1 First Avenue";
        String phoneNumber = "514-000-0000";
        String email = "name@mail.com";

        Library library = new Library();
        String libraryName = "library";

        // Set attributes and save items to repositories
        library.setName(libraryName);
        libraryRepository.save(library);

        client.setLibrary(library);
        client.setNumOfFlags(numOfFlags);
        client.setIsResident(isResident);
        client.setName(clientName);
        client.setPassword(password);
        client.setAddress(address);
        client.setPhoneNumber(phoneNumber);
        client.setEmail(email);
        clientRepository.save(client);
        int clientId = client.getId();

        // Nullify and // Create lists for testing
        client = null;
        client = clientRepository.findById(clientId);

        // Test
        assertEquals(numOfFlags, client.getNumOfFlag());
        assertEquals(isResident, client.getIsResident());
        assertEquals(clientId, client.getId());
        assertEquals(clientName, client.getName());
        assertEquals(password, client.getPassword());
        assertEquals(address, client.getAddress());
        assertEquals(phoneNumber, client.getPhoneNumber());
        assertEquals(email, client.getEmail());

    }

    /**
     * This is the second test for Client class to check if Client can be saved and
     * retrieved from the system. It will check for its reference with a Book class
     * instance under the abstract class LibraryItem.
     * 
     * @author Philippe Shi
     */
    @Test
    public void testPersistAndLoadClient2() {
        // Initiate all variables
        Client client = new Client();
        Library library = new Library();
        String libraryName = "library";
        InUseSlot inUseSlot = new InUseSlot();
        Book book = new Book();

        // Set attributes and save items to repositories
        library.setName(libraryName);
        libraryRepository.save(library);

        client.setLibrary(library);
        clientRepository.save(client);
        int clientId = client.getId();
        book.setLibrary(library);
        bookRepository.save(book);

        inUseSlot.setClient(client);
        inUseSlot.setLibraryItem(book);
        inUseSlotRepository.save(inUseSlot);

        // Create list to retrieve item
        List<InUseSlot> inUseSlotList = new ArrayList<InUseSlot>();
        // Retrieve item from repository
        inUseSlotList = inUseSlotRepository.findInUseSlotByClientId(clientId);
        // Test
        assertEquals(inUseSlot.getClient().getId(), inUseSlotList.get(0).getClient().getId());

    }

    /**
     * This is the first test for the BusinessHours class to check if the
     * ScheduleHours can be saved and retrieved from the system. It will check if
     * the attributes remain the same.
     * 
     * @author Philippe Shi
     */
    @Test
    public void testPersistAndLoadBusinessHours1() {
        // Initiate all variables
        BusinessHours businessHours = new BusinessHours();
        DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
        Time startTime = Time.valueOf("9:30:00");
        Time endTime = Time.valueOf("18:00:00");

        Library library = new Library();
        String libraryName = "library";

        // Set attributes and save items to repositories
        library.setName(libraryName);
        libraryRepository.save(library);
        businessHours.setLibrary(library);
        businessHours.setDayOfWeek(dayOfWeek);
        businessHours.setStartTime(startTime);
        businessHours.setEndTime(endTime);
        businessHoursRepository.save(businessHours);

        // Nullify and retrieve item from repository
        businessHours = null;
        businessHours = businessHoursRepository.findByDayOfWeek(DayOfWeek.MONDAY);

        // Tests
        assertEquals(dayOfWeek, businessHours.getDayOfWeek());
        assertEquals(startTime, businessHours.getStartTime());
        assertEquals(endTime, businessHours.getEndTime());
    }

    /**
     * This is the second test for BusinessHours class to check if the BusinessHours
     * can be saved and retrieved from the system. It will check its reference to
     * the Library class.
     * 
     * @author Philippe Shi
     */
    @Test
    public void testTersistAndLoadBusinessHours2() {
        // Initiate all variables
        BusinessHours businessHours = new BusinessHours();
        DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
        Time startTime = Time.valueOf("9:30:00");
        Time endTime = Time.valueOf("18:00:00");

        Library library = new Library();
        String libraryName = "library";

        // Set attributes and save items to repositories
        library.setName(libraryName);
        libraryRepository.save(library);
        businessHours.setLibrary(library);
        businessHours.setDayOfWeek(dayOfWeek);
        businessHours.setStartTime(startTime);
        businessHours.setEndTime(endTime);
        businessHoursRepository.save(businessHours);

        // Create list to retrieve item
        List<BusinessHours> businessHoursListTest = new ArrayList<BusinessHours>();
        // Retrieve item from repository
        businessHoursListTest = businessHoursRepository.findBusinessHoursByLibraryName(libraryName);
        // Test
        assertEquals(businessHours.getLibrary().getName(), businessHoursListTest.get(0).getLibrary().getName());
    }

    /**
     * This is the first test for the InUseSlot class to check if it can be saved
     * and retrieved from the system. It will check if the attributes remain the
     * same.
     * 
     * @author Philippe Shi
     */
    @Test
    public void testPersistAndLoadInUseSlot1() {
        // Initiate all variables
        InUseSlot inUseSlot = new InUseSlot();
        Date startDate = Date.valueOf("2021-10-29");
        Date endDate = Date.valueOf("2021-11-10");
        InUseSlot.Status status = InUseSlot.Status.Booked;

        Client client = new Client();
        Library library = new Library();
        String libraryName = "library";
        Movie movie = new Movie();

        // Set attributes and save items to repositories
        library.setName(libraryName);
        libraryRepository.save(library);

        client.setLibrary(library);
        clientRepository.save(client);

        movie.setLibrary(library);
        movieRepository.save(movie);

        inUseSlot.setStartDate(startDate);
        inUseSlot.setEndDate(endDate);
        inUseSlot.setStatus(status);
        inUseSlot.setClient(client);
        inUseSlot.setLibraryItem(movie);
        inUseSlotRepository.save(inUseSlot);
        int inUseSlotid = inUseSlot.getId();

        // Nullify and retrieve item from repository
        inUseSlot = null;
        inUseSlot = inUseSlotRepository.findById(inUseSlotid);

        // Tests
        assertEquals(inUseSlotid, inUseSlot.getId());
        assertEquals(startDate, inUseSlot.getStartDate());
        assertEquals(endDate, inUseSlot.getEndDate());
        assertEquals(status, inUseSlot.getStatus());
    }

    /**
     * This is the second test for the InUseSlot class to check if it can be saved
     * and retrieved from the system. It will check its reference to the MusicAlbum
     * subclass of the LibraryItem class.
     * 
     * @author Philippe Shi
     */
    @Test
    public void testPersistAndLoadInUseSlot2() {
        // Initiate all variables
        InUseSlot inUseSlot = new InUseSlot();
        Client client = new Client();
        Library library = new Library();
        String libraryName = "library";
        MusicAlbum musicAlbum = new MusicAlbum();

        // Set attributes and save items to repositories
        library.setName(libraryName);
        libraryRepository.save(library);

        client.setLibrary(library);
        clientRepository.save(client);

        musicAlbum.setLibrary(library);
        musicAlbumRepository.save(musicAlbum);
        int musicAlbumId = musicAlbum.getId();

        inUseSlot.setClient(client);
        inUseSlot.setLibraryItem(musicAlbum);
        inUseSlotRepository.save(inUseSlot);

        // Create list to retrieve item
        List<InUseSlot> inUseSlotListTest = new ArrayList<InUseSlot>();

        // Retrieve item from repository
        inUseSlotListTest = inUseSlotRepository.findInUseSlotByLibraryItemId(musicAlbumId);

        // Test
        assertEquals(inUseSlot.getLibraryItem().getId(), inUseSlotListTest.get(0).getLibraryItem().getId());
    }

    /**
     * This is the first test for the Library Class to check if the Library can be
     * saved and retrieved to the system.
     * 
     * @author Brandon
     */
    @Test
    public void testPersistAndLoadLibrary() {
        // Initialize all the values for the tests
        Library library = new Library();
        String name = "library";
        String address = "123 mcgill str";
        String phoneNumber = "5145555555";
        String email = "mail@mail.com";

        // Set the attributes and save the instance to the repository
        library.setName(name);
        library.setAddress(address);
        library.setPhoneNumber(phoneNumber);
        library.setEmail(email);
        libraryRepository.save(library);

        // Set the instance to null
        library = null;

        // Call the query method to find the library from the library repository
        library = libraryRepository.findByName("library");

        // Verify if the library instance in the repository has the same attributes as
        // test values
        assertEquals(name, library.getName());
        assertEquals(address, library.getAddress());
        assertEquals(phoneNumber, library.getPhoneNumber());
        assertEquals(email, library.getEmail());
    }

    /**
     * This is the first test for the Library Class to check if the saved Library
     * exists in the repository.
     * 
     * @author Brandon
     */
    @Test
    public void testPersistAndLoadLibrary2() {
        // Initialize all the values for the tests
        Library library = new Library();
        String name = "library";
        String address = "123 mcgill str";
        String phoneNumber = "5145555555";
        String email = "mail@mail.com";

        // Set the attributes and save the instance to the repository
        library.setName(name);
        library.setAddress(address);
        library.setPhoneNumber(phoneNumber);
        library.setEmail(email);
        libraryRepository.save(library);

        // Call the query method to find the library from the library repository
        Boolean existsLibrary = libraryRepository.existsByName("library");

        // Verify if the library instance in the repository has the same attributes as
        // test values
        assertTrue(existsLibrary);
    }

    @Test
    public void testPersistAndLoadEmployee1() {
        // Create library instance
        Employee employee = new Employee();
        String name = "Jenny";
        String password = "Jenny123";
        String address = "123 road";
        String phoneNumber = "5141234567";
        String email = "Jenny@mail.com";
        Employee.TypeOfEmployee typeOfEmployee1 = Employee.TypeOfEmployee.Librarian;

        Library library = new Library();
        String libraryName = "library";

        // Set attributes and save items to repositories
        library.setName(libraryName);
        libraryRepository.save(library);

        employee.setLibrary(library);
        employee.setName(name);
        employee.setPassword(password);
        employee.setAddress(address);
        employee.setPhoneNumber(phoneNumber);
        employee.setEmail(email);
        employee.setTypeOfEmployee(typeOfEmployee1);
        employeeRepository.save(employee);
        int id = employee.getId();
        employee = null;

        employee = employeeRepository.findById((id));

        assertEquals(name, employee.getName());
        assertEquals(password, employee.getPassword());
        assertEquals(id, employee.getId());
        assertEquals(address, employee.getAddress());
        assertEquals(phoneNumber, employee.getPhoneNumber());
        assertEquals(email, employee.getEmail());
        assertEquals(typeOfEmployee1, employee.getTypeOfEmployee());

    }

    // Testing link of employee to WorkSlot
    @Test
    public void testPersistanceAndLoadEmployee2() {

        Employee employee = new Employee();
        String name = "Jenny";
        String password = "Jenny123";
        String address = "123 road";
        String phoneNumber = "5141234567";
        String email = "Jenny@mail.com";
        Employee.TypeOfEmployee typeOfEmployee = Employee.TypeOfEmployee.Librarian;

        Library library = new Library();
        String libraryName = "library";

        library.setName(libraryName);
        libraryRepository.save(library);

        employee.setLibrary(library);
        employee.setName(name);
        employee.setPassword(password);
        employee.setAddress(address);
        employee.setPhoneNumber(phoneNumber);
        employee.setEmail(email);
        employee.setTypeOfEmployee(typeOfEmployee);
        employeeRepository.save(employee);
        int id2 = employee.getId();
        WorkSlot workSlot = new WorkSlot();
        Date startDate = Date.valueOf("2021-10-26");
        Date endDate = Date.valueOf("2021-10-27");
        Time startTime = Time.valueOf("00:00:00");
        Time endTime = Time.valueOf("12:00:00");

        workSlot.setEmployee(employee);
        workSlot.setStartDate(startDate);
        workSlot.setEndDate(endDate);
        workSlot.setStartTime(startTime);
        workSlot.setEndTime(endTime);

        workSlotRepository.save(workSlot);

        employee = null;
        List<WorkSlot> workSlotList = new ArrayList<WorkSlot>();
        workSlotList = workSlotRepository.findWorkSlotByEmployeeId(id2);
        employee = workSlotList.get(0).getEmployee();

        assertEquals(id2, employee.getId());
        assertEquals(name, employee.getName());
        assertEquals(password, employee.getPassword());
        assertEquals(address, employee.getAddress());
        assertEquals(phoneNumber, employee.getPhoneNumber());
        assertEquals(email, employee.getEmail());
        assertEquals(typeOfEmployee, employee.getTypeOfEmployee());
    }

    // @Test
    // public void testIdGeneration(){
    // Book book = new Book();
    // Library library = new Library();
    // library.setName("lib");
    // libraryRepository.save(library);
    // book.setLibrary(library);
    // bookRepository.save(book);
    // book = null;

    // book = bookRepository.findBookByLibraryName("lib").get(0);
    // assertNotNull(book.getId());

    // }
}