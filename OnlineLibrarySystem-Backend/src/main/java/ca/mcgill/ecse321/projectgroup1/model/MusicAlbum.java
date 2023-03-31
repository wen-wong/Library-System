package ca.mcgill.ecse321.projectgroup1.model;

// Import JPA annotation tag
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * MusicAlbum - This class serves as a music album item in the online library
 * system. This inherits all attributes of the LibraryItem class.
 * 
 * @param artist - the music album's artist
 * @param genre  - the music album's genre
 * 
 * @author Anika Kabir
 * @author Wen Hin Brandon Wong (Reviewer)
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class MusicAlbum extends LibraryItem {

    // The artist attribute of the MusicAlbum class as a String
    private String artist;

    // The setter method for the artist attribute that takes a String value
    // parameter
    public void setArtist(String value) {
        this.artist = value;
    }

    // The getter method for the artist attribute that returns a String
    public String getArtist() {
        return this.artist;
    }

    // The genre attribute of the MusicAlbum class as a String
    private String genre;

    // The setter method for the genre attribute that takes a String value parameter
    public void setGenre(String value) {
        this.genre = value;
    }

    // The getter method for the genre attribute that takes a String
    public String getGenre() {
        return this.genre;
    }

}