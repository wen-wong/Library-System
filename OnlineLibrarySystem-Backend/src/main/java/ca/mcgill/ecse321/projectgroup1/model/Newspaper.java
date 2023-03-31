package ca.mcgill.ecse321.projectgroup1.model;

// Import JPA annotation tag
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Newspaper - This class serves as a newspaper item in the online library
 * system. This inherits all attributes of the LibraryItem class.
 * 
 * @param publisher - the newspaper's publisher
 * 
 * @author Anika Kabir
 * @author Wen Hin Brandon Wong (Reviewer)
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Newspaper extends LibraryItem {

    // The publisher attribute of the Newspaper class as a String
    private String publisher;

    // The setter method for the publisher attribute that takes a String value
    // parameter
    public void setPublisher(String value) {
        this.publisher = value;
    }

    // The getter method for the publisher attribute that returns a String
    public String getPublisher() {
        return this.publisher;
    }
}