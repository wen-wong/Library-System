package ca.mcgill.ecse321.projectgroup1.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup1.model.WorkSlot;

/**
 * WorkSlotRepository - This class extends the CrudRepository and contains all
 * query methods related to the TimeSlot class
 * 
 * Author: Simon Wong
 */

public interface WorkSlotRepository extends CrudRepository<WorkSlot, Integer> {
        // see if WorkSlot exists by Id
        Boolean existsById(int id);

        // find List of WorkSlots by WorkSlot id
        WorkSlot findById(int id);

        // find WorkSlot by Library
        List<WorkSlot> findWorkSlotByEmployeeId(int id);
     
}
