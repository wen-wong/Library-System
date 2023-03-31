package ca.mcgill.ecse321.projectgroup1.dao;

// Import Assertion methods
import static org.junit.jupiter.api.Assertions.assertEquals;
// Import Java packages
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

// Import Spring Persistence Tags
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// Import Model Classes
import ca.mcgill.ecse321.projectgroup1.model.Library;
import ca.mcgill.ecse321.projectgroup1.model.Movie;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestMoviePersistence {
    
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
     * This is the first test for the Movie Class to check if a movie can be saved
     * and retrieved in the system.
     * 
     * (Attributes)
     * 
     * @author Anika
     */

    @Test
    public void testPersistAndLoadMovie1() {

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

        // Initialize all the values for the movie
        String director1 = "Larry Rogers";
        String movieDescription = "stuff";
        String titleMovie1 = "Moana";
        Boolean movieArchive = false;
        Date date = java.sql.Date.valueOf(LocalDate.of(2019, Month.FEBRUARY, 18));
        int lengthMovie1 = 130;

        // Set the attributes and save the instance to the repository
        Movie movie1 = new Movie();
        movie1.setDirector(director1);
        movie1.setDescription(movieDescription);
        movie1.setTitle(titleMovie1);
        movie1.setIsArchive(movieArchive);
        movie1.setReleaseDate(date);
        movie1.setMovieLength(lengthMovie1);
        movie1.setLibrary(library);
        movieRepository.save(movie1);
        libraryItemRepository.save(movie1);

        int idMovie1= movieRepository.findByDirector(director1).get(0).getId();
        movie1 = null;

        movie1 = movieRepository.findByDirector(director1).get(0);

        assertEquals(director1, movie1.getDirector());
        assertEquals(movieArchive, movie1.getIsArchive());
        assertEquals(movieDescription, movie1.getDescription());
        assertEquals(date, movie1.getReleaseDate());
        assertEquals(idMovie1, movie1.getId());
        assertEquals(titleMovie1, movie1.getTitle());
        assertEquals(lengthMovie1, movie1.getMovieLength());
    }

    /**
     * This is the second test for the Movie Class to check if a list of movies can
     * be saved and retrieved in the system.
     * 
     * (List of movies)
     * 
     * @author Anika
     */

    @Test
    public void testPersistAndLoadMovie2() {

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

        // Initialize all the values for the first movie
        String director1 = "Larry Rogers";
        String movieDescription = "stuff";
        String titleMovie1 = "Moana";
        Boolean movieArchive = false;
        Date date = java.sql.Date.valueOf(LocalDate.of(2019, Month.FEBRUARY, 18));
        int lengthMovie1 = 130;

        // Set the attributes and save the instance to the repository
        Movie movie1 = new Movie();
        movie1.setDirector(director1);
        movie1.setDescription(movieDescription);
        movie1.setTitle(titleMovie1);
        movie1.setIsArchive(movieArchive);
        movie1.setReleaseDate(date);
        movie1.setMovieLength(lengthMovie1);
        movie1.setLibrary(library);
        movieRepository.save(movie1);
        libraryItemRepository.save(movie1);

        // Initialize all the values for the second movie
        String titleMovie2 = "Snow White";
        int lengthMovie2 = 110;

        // Set the attributes and save the instance to the repository
        Movie movie2 = new Movie();
        movie2.setDirector(director1);
        movie2.setDescription("Disney");
        movie2.setTitle(titleMovie2);
        movie2.setIsArchive(movieArchive);
        movie2.setReleaseDate(date);
        movie2.setMovieLength(lengthMovie2);
        movie2.setLibrary(library);
        movieRepository.save(movie2);
        libraryItemRepository.save(movie2);

        List<Movie> MoviesWithSameDirector = new ArrayList<Movie>();
        MoviesWithSameDirector.add(movie1);
        MoviesWithSameDirector.add(movie2);

        movie1 = null;
        movie2 = null;

        List<Movie> testWorks = new ArrayList<Movie>();
        testWorks = movieRepository.findByDirector(director1);

        assertEquals(2, MoviesWithSameDirector.size());
        assertEquals(MoviesWithSameDirector.get(0).getDirector(), testWorks.get(0).getDirector());
        assertEquals(director1, testWorks.get(0).getDirector());
        assertEquals(director1, testWorks.get(1).getDirector());
        assertEquals(titleMovie1, testWorks.get(0).getTitle());
        assertEquals(titleMovie2, testWorks.get(1).getTitle());
    }
}
