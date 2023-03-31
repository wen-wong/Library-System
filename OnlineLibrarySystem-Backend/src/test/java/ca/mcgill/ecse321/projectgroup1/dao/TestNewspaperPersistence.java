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
import ca.mcgill.ecse321.projectgroup1.model.Newspaper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestNewspaperPersistence {
    
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
     * This is the first test for the Newspaper Class to check if a newspaper can be
     * saved and retrieved in the system.
     * 
     * (Attributes)
     * 
     * @author Anika
     */

    @Test
    public void testPersistAndLoadNewspaper1() {

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

        // Initialize all the values for the newspaper
        String publisher1 = "JWD R.";
        String newspaperDescription = "im tired";
        String titleNewspapaer1 = "Crazy Students";
        Boolean newsPaperArchive = false;
        Date date = java.sql.Date.valueOf(LocalDate.of(1999, Month.APRIL, 15));

        // Set the attributes and save the instance to the repository
        Newspaper newspaper1 = new Newspaper();
        newspaper1.setPublisher(publisher1);
        newspaper1.setDescription(newspaperDescription);
        newspaper1.setIsArchive(newsPaperArchive);
        newspaper1.setReleaseDate(date);
        newspaper1.setTitle(titleNewspapaer1);
        newspaper1.setLibrary(library);
        newspaperRepository.save(newspaper1);
        libraryItemRepository.save(newspaper1);

        int idNewspaper1= newspaperRepository.findByPublisher(publisher1).get(0).getId();
        newspaper1 = null;

        newspaper1 = newspaperRepository.findByPublisher(publisher1).get(0);

        assertEquals(publisher1, newspaper1.getPublisher());
        assertEquals(newsPaperArchive, newspaper1.getIsArchive());
        assertEquals(newspaperDescription, newspaper1.getDescription());
        assertEquals(date, newspaper1.getReleaseDate());
        assertEquals(idNewspaper1, newspaper1.getId());
        assertEquals(titleNewspapaer1, newspaper1.getTitle());

    }

    /**
     * This is the second test for the Newspaper Class to check if a newspaper can
     * be saved and retrieved in the system using a Library reference.
     * 
     * (Newspaper with library reference)
     * 
     * @author Anika
     */

    @Test
    public void testPersistAndLoadNewspaper2() {

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

        // Initialize all the values for the newspaper
        String publisher1 = "JWD R.";
        String newspaperDescription = "im tired";
        String titleNewspapaer1 = "Crazy Students";
        Boolean newsPaperArchive = false;
        Date date = java.sql.Date.valueOf(LocalDate.of(1999, Month.APRIL, 15));

        // Set the attributes and save the instance to the repository
        Newspaper newspaper1 = new Newspaper();
        newspaper1.setPublisher(publisher1);
        newspaper1.setDescription(newspaperDescription);
        newspaper1.setIsArchive(newsPaperArchive);
        newspaper1.setReleaseDate(date);
        newspaper1.setTitle(titleNewspapaer1);
        newspaper1.setLibrary(library);
        newspaperRepository.save(newspaper1);
        libraryItemRepository.save(newspaper1);

        int idNewspaper1= newspaperRepository.findByPublisher(publisher1).get(0).getId();
        newspaper1 = null;

        newspaper1 = newspaperRepository.findNewspaperByLibraryName(name).get(0);

        assertEquals(publisher1, newspaper1.getPublisher());
        assertEquals(newsPaperArchive, newspaper1.getIsArchive());
        assertEquals(newspaperDescription, newspaper1.getDescription());
        assertEquals(date, newspaper1.getReleaseDate());
        assertEquals(idNewspaper1, newspaper1.getId());
        assertEquals(titleNewspapaer1, newspaper1.getTitle());

    }
}
