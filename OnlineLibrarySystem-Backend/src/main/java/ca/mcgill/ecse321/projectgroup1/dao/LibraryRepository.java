package ca.mcgill.ecse321.projectgroup1.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup1.model.Library;

/**
 * LibraryRepository - This class contains all query methods related to the
 * Library class
 * 
 * @author Wen Hin Brandon Wong
 */

public interface LibraryRepository extends CrudRepository<Library, String> {
    // check if library repository exists by name
    boolean existsByName(String name);

    // find library by name
    Library findByName(String name);
}
