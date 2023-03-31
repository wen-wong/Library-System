package ca.mcgill.ecse321.projectgroup1.model;

// Import JPA annotation tag
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Movie - This class serves as a movie item in the online library system. This
 * inherits all attributes of the LibraryItem class.
 * 
 * @param director    - the movie's director
 * @param movieLength - the movie's length in minutes
 * 
 * @author Anika Kabir
 * @author Wen Hin Brandon Wong (Reviewer)
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Movie extends LibraryItem {

    // The director attribute of the Movie class as a String
    private String director;

    // The setter method for the director attribute that takes a String value
    // parameter
    public void setDirector(String value) {
        this.director = value;
    }

    // The getter method for the director attribtue that returns a String
    public String getDirector() {
        return this.director;
    }

    // The movieLength attribute of the Movie class as an integer
    private int movieLength;

    // The setter method for the movieLength attribute that takes an integer value
    // parameter
    public void setMovieLength(int minutes) {
        this.movieLength = minutes;
    }

    // The getter method for the movieLength attribute that returns an integer
    public int getMovieLength() {
        return this.movieLength;
    }
}