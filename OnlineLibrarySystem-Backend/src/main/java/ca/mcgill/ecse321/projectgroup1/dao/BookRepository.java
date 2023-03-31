package ca.mcgill.ecse321.projectgroup1.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup1.model.Book;
import java.util.List;

/**
 * Since a book has no unique attribute, simply checking for a single book with
 * an author will not lead to efficient results. So, listing the search results
 * is better, as there may be many books with the same author. The most logical
 * way to search for a book would be with its author (no one would want to
 * search with the amount of pages).
 * 
 * Author: Anika Kabir
 */
public interface BookRepository extends CrudRepository<Book, Integer> {

    // Verifies if Book instance exists
    Boolean existsById(int id);

    // Query method to find Book instance with common author
    Book findById(int id);

    // Query to check if a Book instance exists using its author
    boolean existsByAuthor(String author);

    // Query method to find all Book instances with common author
    List<Book> findByAuthor(String author);

    // Query method to find a list of books in Library
    List<Book> findBookByLibraryName(String name);

    // Query method to find all books with common title
    //  List<Book> findByTtile(String title);
}
