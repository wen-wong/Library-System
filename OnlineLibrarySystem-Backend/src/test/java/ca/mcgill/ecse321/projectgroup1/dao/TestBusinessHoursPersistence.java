package ca.mcgill.ecse321.projectgroup1.dao;
// Import Assertion methods
import static org.junit.jupiter.api.Assertions.assertEquals;
// Import Java packages
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
import ca.mcgill.ecse321.projectgroup1.model.BusinessHours;
import ca.mcgill.ecse321.projectgroup1.model.Library;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestBusinessHoursPersistence {
    @Autowired
    private BusinessHoursRepository businessHoursRepository;
    @Autowired
    private LibraryRepository libraryRepository;

    @AfterEach
    public void clearDatabase(){
        businessHoursRepository.deleteAll();
        libraryRepository.deleteAll();

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
}
