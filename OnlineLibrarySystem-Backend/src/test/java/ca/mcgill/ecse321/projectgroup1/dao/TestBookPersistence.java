package ca.mcgill.ecse321.projectgroup1.dao;

// Import Assertion methods
import static org.junit.jupiter.api.Assertions.assertEquals;
// Import Java packages
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;

// Import Spring Persistence Tags
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// Import Model Classes
import ca.mcgill.ecse321.projectgroup1.model.Library;
import ca.mcgill.ecse321.projectgroup1.model.Book;
import ca.mcgill.ecse321.projectgroup1.model.Client;
import ca.mcgill.ecse321.projectgroup1.model.InUseSlot;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestBookPersistence {
    
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
     * This is the first test for the Book Class to check if a book can be saved and
     * retrieved in the system.
     * 
     * (Attributes)
     * 
     * @author Anika
     */
    
    @Test
    public void testPersistAndLoadBook1() {

        // Initialize all the values for the library
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

        // Initialize all the values for the book
        String author1 = "George Orwell";
        Boolean bookArchive = false;
        String bookDescription = "stuff";
        int pgNumbs = 10;
        Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
        String titleBook1 = "1984";

        // Set the attributes and save the instance to the repository
        Book book1 = new Book();
        book1.setIsArchive(bookArchive);
        book1.setDescription(bookDescription);
        book1.setPageNumber(pgNumbs);
        book1.setReleaseDate(date);
        book1.setTitle(titleBook1);
        book1.setAuthor(author1);
        book1.setLibrary(library);
        bookRepository.save(book1);
        libraryItemRepository.save(book1);

        int bookId= bookRepository.findByAuthor(author1).get(0).getId();
        
        book1 = null;

        book1 = bookRepository.findByAuthor(author1).get(0);

        assertEquals(author1, book1.getAuthor());
        assertEquals(bookArchive, book1.getIsArchive());
        assertEquals(bookDescription, book1.getDescription());
        assertEquals(pgNumbs, book1.getPageNumber());
        assertEquals(date, book1.getReleaseDate());
        assertEquals(titleBook1, book1.getTitle());
        assertEquals(bookId, book1.getId());
    }

    /**
     * This is the second test for the Book Class to check if a book can be saved
     * and retrieved through the use of a inUseSlot in the system.
     * 
     * (Reference with inUseSlot)
     * 
     * @author Anika
     */

    @Test
    public void testPersistAndLoadBook2() {

        // Initialize all the values for the inUseSlot
        InUseSlot inUse = new InUseSlot();
       // int slotId = 2234;
        Date startDate = Date.valueOf("2021-10-19");
        Date endDate = Date.valueOf("2021-10-25");
        InUseSlot.Status status = InUseSlot.Status.Booked;

        // client attributes and creation
        Client client = new Client();

        // library attributes and creation
        Library library = new Library();
        String libraryName = "name";

        // set and save library to system
        library.setName(libraryName);
        libraryRepository.save(library);

        // Creating and setting client
        client.setLibrary(library);
        clientRepository.save(client);

        // Initialize all the values for the book
        String author1 = "George Orwell";
        Boolean bookArchive = false;
        String bookDescription = "stuff";
        int pgNumbs = 10;
        Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
        String titleBook1 = "1984";

        // Set the attributes and save the instance to the repository
        Book book1 = new Book();
        book1.setIsArchive(bookArchive);
        book1.setDescription(bookDescription);
        book1.setPageNumber(pgNumbs);
        book1.setReleaseDate(date);
        book1.setTitle(titleBook1);
        book1.setAuthor(author1);
        book1.setLibrary(library);
        bookRepository.save(book1);
        libraryItemRepository.save(book1);

        // Set the attributes and save the instance to the repository
        inUse.setLibraryItem(book1);
        inUse.setClient(client);
        inUse.setStartDate(startDate);
        inUse.setEndDate(endDate);
        inUse.setStatus(status);
        inUseSlotRepository.save(inUse);

        inUse = inUseSlotRepository.findById(inUse.getId());

        int bookId= bookRepository.findByAuthor(author1).get(0).getId();
        book1 = null;

        book1 = bookRepository.findByAuthor(author1).get(0);

        assertEquals(author1, ((Book) inUse.getLibraryItem()).getAuthor());
        assertEquals(bookArchive, inUse.getLibraryItem().getIsArchive());
        assertEquals(bookDescription, inUse.getLibraryItem().getDescription());
        assertEquals(pgNumbs, ((Book) inUse.getLibraryItem()).getPageNumber());
        assertEquals(date, inUse.getLibraryItem().getReleaseDate());
        assertEquals(bookId, inUse.getLibraryItem().getId());
        assertEquals(titleBook1, inUse.getLibraryItem().getTitle());

    }
}
