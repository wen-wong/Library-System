package ca.mcgill.ecse321.projectgroup1.dao;

// Import Java packages
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

// Import Spring Persistence Tags
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup1.model.Book;
// Import Model Classes
// import ca.mcgill.ecse321.projectgroup1.model.Account;
import ca.mcgill.ecse321.projectgroup1.model.Client;
import ca.mcgill.ecse321.projectgroup1.model.InUseSlot;
import ca.mcgill.ecse321.projectgroup1.model.Library;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestClientPersistence {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private InUseSlotRepository inUseSlotRepository;
    @Autowired
    private BookRepository bookRepository;

    // Clear all existing data in the repositories
    @AfterEach
    public void clearDatabase() {
        inUseSlotRepository.deleteAll();
        // Remove all library item instances
        bookRepository.deleteAll();
        // Remove all users' account instances
        clientRepository.deleteAll();
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
        int inUseSlotId = 12345;
        Book book = new Book();

        // Set attributes and save items to repositories
        library.setName(libraryName);
        libraryRepository.save(library);

        client.setLibrary(library);
        clientRepository.save(client);
        int clientId = client.getId();

        book.setLibrary(library);
        bookRepository.save(book);

        inUseSlot.setId(inUseSlotId);
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
}