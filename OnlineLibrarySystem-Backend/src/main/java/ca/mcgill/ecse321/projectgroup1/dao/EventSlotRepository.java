package ca.mcgill.ecse321.projectgroup1.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup1.model.EventSlot;

/**
 * WorkSlotRepository - This class extends the CrudRepository and contains all
 * query methods related to the EventSlot class
 * 
 * Author: Simon Wong
 */

public interface EventSlotRepository extends CrudRepository<EventSlot, Integer> {
        // see if WorkSlot exists by Id
        Boolean existsById(int id);

        // find List of event slots by WorkSlot id
        EventSlot findById(int id);

        // find WorkSlot by Library
        List<EventSlot> findEventSlotByClientId(int id);
}
