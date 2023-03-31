package ca.mcgill.ecse321.projectgroup1.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup1.model.BusinessHours;

// Import Java packages
import java.time.DayOfWeek;
import java.util.List;

public interface BusinessHoursRepository extends CrudRepository<BusinessHours, Integer> {

    // Verifies if ScheduleHours instance exists by Id
    Boolean existsById(int id);

    // Query method to find BusinessHours
    BusinessHours findByDayOfWeek(DayOfWeek dayOfWeek);

    // Query method to find BusinessHours by Library
    List<BusinessHours> findBusinessHoursByLibraryName(String name);
}
