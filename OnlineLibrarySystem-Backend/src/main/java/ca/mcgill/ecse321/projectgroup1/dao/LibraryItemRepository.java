package ca.mcgill.ecse321.projectgroup1.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup1.model.LibraryItem;
import java.util.List;

/**
 * Since an id will be unique for each Library Item, it will be possible to
 * optain to obtain only one item per id. A client will be able to search a
 * Library Item by title or by both title and release date.
 * 
 * Author: Anika Kabir
 */

public interface LibraryItemRepository extends CrudRepository<LibraryItem, Integer> {

    // Query method to find all Library Item instances

    // Verifies if the LibraryItem instance exists
    Boolean existsById(int id);

    // Query method to find a Library Item instance using an id
    LibraryItem findById(int id);

    // Query method to find a list of library items in Library
    List<LibraryItem> findLibraryItemByLibraryName(String name);

    //Query method to find items by their title
    List<LibraryItem> findLibraryItemsByTitle(String title);
}