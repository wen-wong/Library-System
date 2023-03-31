package ca.mcgill.ecse321.projectgroup1.dao;

// Import Assertion methods
import static org.junit.jupiter.api.Assertions.assertEquals;
// Import Java packages
import java.sql.Date;
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
// import ca.mcgill.ecse321.projectgroup1.model.Account;
import ca.mcgill.ecse321.projectgroup1.model.Client;
import ca.mcgill.ecse321.projectgroup1.model.InUseSlot;
import ca.mcgill.ecse321.projectgroup1.model.Library;
import ca.mcgill.ecse321.projectgroup1.model.Movie;
import ca.mcgill.ecse321.projectgroup1.model.MusicAlbum;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestInUseSlotPersistence {
    @Autowired
    private InUseSlotRepository inUseSlotRepository;
    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MusicAlbumRepository musicAlbumRepository;

    // Clear all existing data in the repositories
    @AfterEach
    public void clearDatabase() {
        inUseSlotRepository.deleteAll();
        // Remove all library item instances
        movieRepository.deleteAll();
        musicAlbumRepository.deleteAll();

        // Remove all instances related to events, holidays, and business hours
        inUseSlotRepository.deleteAll();

        // Remove all users' account instances
        clientRepository.deleteAll();

        // Remove all library instances
        libraryRepository.deleteAll();
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
        int inUseSlotId = inUseSlot.getId();
        // Nullify and retrieve item from repository
        inUseSlot = null;
        inUseSlot = inUseSlotRepository.findById(inUseSlotId);

        // Tests
        assertEquals(inUseSlotId, inUseSlot.getId());
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
}