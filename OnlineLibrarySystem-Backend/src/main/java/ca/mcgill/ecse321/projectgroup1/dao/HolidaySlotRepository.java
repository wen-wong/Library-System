package ca.mcgill.ecse321.projectgroup1.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup1.model.HolidaySlot;

/**
 * HolidayRepository - This class extends the CrudRepository and contains all
 * query methods related to the HolidaySlot class
 * 
 * Author: Simon Wong
 */

public interface HolidaySlotRepository extends CrudRepository<HolidaySlot, Integer> {
        // see if HolidaySlot exists by Id
        Boolean existsById(int id);

        // find List of holiday slots by HolidaySlot id
        HolidaySlot findById(int id);

        // find HolidaySlot by Library
        List<HolidaySlot> findByLibraryName(String name);
}
