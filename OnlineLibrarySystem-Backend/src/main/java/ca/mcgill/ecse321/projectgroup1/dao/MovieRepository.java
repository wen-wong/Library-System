package ca.mcgill.ecse321.projectgroup1.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup1.model.Movie;

import java.util.List;


/**
 * Since a Movie has no unique attribute, simply checking for a single movie
 * with a director will not lead to efficient results. So, listing the search
 * results according to a director is most efficient. The most logical way to
 * search for a movie would be with its director (no one would want to search
 * with its length).
 * 
 * Author: Anika Kabir
 */

public interface MovieRepository extends CrudRepository<Movie, Integer> {
    // Verifies if Movie instance exists by Id
    Boolean existsById(int id);

    // Query method to find Movie by Id
    Movie findById(int id);

    // Query to check if a Movie instance exists using its director
    boolean existsMovieByDirector(String director);

    // Query method to find all Movie instances with common director
    List<Movie> findByDirector(String director);

    // Query method to find a list of movies in Library
    List<Movie> findMovieByLibraryName(String name);

    // Query method to find all movies with common title
    // List<Movie> findMovieByTtile(String title);
}
