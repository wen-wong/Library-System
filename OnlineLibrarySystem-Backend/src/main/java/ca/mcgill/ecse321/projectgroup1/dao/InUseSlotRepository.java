package ca.mcgill.ecse321.projectgroup1.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup1.model.InUseSlot;
import java.util.List;

/**
 * InUseSlotRepository - This class extends the CrudRepository and contains all
 * query methods related to the InUseSlot class
 * 
 * Author: Wen Hin Brandon Wong
 */

public interface InUseSlotRepository extends CrudRepository<InUseSlot, Integer> {
    // Query method to check if an InUseSlot instance exists with id
    boolean existsById(int id);
    // Query method to find an InUseSlot instance with an id
    InUseSlot findById(int id);
    //Query method to find inUseSlots based on library id,
    List<InUseSlot> findInUseSlotByLibraryItemId(int id);
    //Query method to find inUseSlots based on User id
    List<InUseSlot> findInUseSlotByClientId(int id);
}
