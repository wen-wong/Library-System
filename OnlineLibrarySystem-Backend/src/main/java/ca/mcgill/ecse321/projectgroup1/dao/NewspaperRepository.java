package ca.mcgill.ecse321.projectgroup1.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup1.model.Newspaper;

import java.util.List;

/**
 * NewspaperRepository - This class extends the CrudRepository and contains all
 * query methods related to the Newspaper class
 * 
 * Author: Anika Kabir
 */
public interface NewspaperRepository extends CrudRepository<Newspaper, Integer> {

    // Verifies if NewsPaper instance exists by Id
    Boolean existsById(int id);

    // Query method to find NewsPaper by Id
    Newspaper findById(int id);

    // Query to check if a Newspaper instance exists using its author
    boolean existsNewspaperByPublisher(String publisher);

    // Query method to find all Newspaper instances with common author
    List<Newspaper> findByPublisher(String publisher);

    // Query method to find a list of newspapers in Library
    List<Newspaper> findNewspaperByLibraryName(String name);

    // Query method to find all newspapers with common title
    // List<Newspaper> findNewspaperByTtile(String title);
}
