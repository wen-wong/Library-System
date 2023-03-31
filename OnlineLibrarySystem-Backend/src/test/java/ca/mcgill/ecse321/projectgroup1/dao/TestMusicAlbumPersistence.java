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
import ca.mcgill.ecse321.projectgroup1.model.MusicAlbum;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestMusicAlbumPersistence {
    
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
     * This is the first test for the MusicAlbum Class to check if a music album can
     * be saved and retrieved in the system.
     * 
     * (Attributes)
     * 
     * @author Anika
     */

    @Test
    public void testPersistAndLoadMusicAlbum1() {

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

        // Initialize all the values for the music album
        String artist1 = "Justin Bieber";
        String genre1 = "pop";
        String albumDescription = "stuff";
        String titleMusicAlbum1 = "Peaches out in Georgia";
        Boolean musicAlbumArchive = false;
        Date date = java.sql.Date.valueOf(LocalDate.of(2001, Month.OCTOBER, 31));

        // Set the attributes and save the instance to the repository
        MusicAlbum album1 = new MusicAlbum();
        album1.setIsArchive(musicAlbumArchive);
        album1.setDescription(albumDescription);
        album1.setReleaseDate(date);
        album1.setTitle(titleMusicAlbum1);
        album1.setArtist(artist1);
        album1.setGenre(genre1);
        album1.setLibrary(library);
        musicAlbumRepository.save(album1);
        libraryItemRepository.save(album1);

        int idAlbum1= musicAlbumRepository.findByArtist(artist1).get(0).getId();
        album1 = null;

        album1 = musicAlbumRepository.findByArtistAndGenre(artist1, genre1).get(0);

        assertEquals(artist1, album1.getArtist());
        assertEquals(genre1, album1.getGenre());
        assertEquals(musicAlbumArchive, album1.getIsArchive());
        assertEquals(albumDescription, album1.getDescription());
        assertEquals(date, album1.getReleaseDate());
        assertEquals(idAlbum1, album1.getId());
        assertEquals(titleMusicAlbum1, album1.getTitle());

    }

    /**
     * This is the second test for the MusicAlbum Class to check if a music album
     * can be saved and retrieved in the system using a Library reference.
     * 
     * (Music Album with library reference using query method from libraryItem repo)
     * 
     * @author Anika
     */

    @Test
    public void testPersistAndLoadMusicAlbum2() {

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

        // Initialize all the values for the music album
        String artist1 = "Justin Bieber";
        String genre1 = "pop";
        String albumDescription = "stuff";
        String titleMusicAlbum1 = "Peaches out in Georgia";
        Boolean musicAlbumArchive = false;
        Date date = java.sql.Date.valueOf(LocalDate.of(2001, Month.OCTOBER, 31));

        // Set the attributes and save the instance to the repository
        MusicAlbum album1 = new MusicAlbum();
        album1.setIsArchive(musicAlbumArchive);
        album1.setDescription(albumDescription);
        album1.setReleaseDate(date);
        album1.setTitle(titleMusicAlbum1);
        album1.setArtist(artist1);
        album1.setGenre(genre1);
        album1.setLibrary(library);
        musicAlbumRepository.save(album1);
        libraryItemRepository.save(album1);

        int idAlbum1= musicAlbumRepository.findByArtist(artist1).get(0).getId();
        album1 = null;

        album1 = (MusicAlbum) libraryItemRepository.findLibraryItemByLibraryName(name).get(0);

        assertEquals(artist1, album1.getArtist());
        assertEquals(genre1, album1.getGenre());
        assertEquals(musicAlbumArchive, album1.getIsArchive());
        assertEquals(albumDescription, album1.getDescription());
        assertEquals(date, album1.getReleaseDate());
        assertEquals(idAlbum1, album1.getId());
        assertEquals(titleMusicAlbum1, album1.getTitle());
    }
}
