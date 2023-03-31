package ca.mcgill.ecse321.projectgroup1.model;

// Import SQL packages
import java.sql.Date;

// Import JPA annotation tags
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

/**
 * LibraryItem - This class serves as a library item in the online library
 * system. This is an abstract class that represents the generalization of the
 * different type of items in the library. Each LibraryItem instance is
 * associated with InUseSlots instances.
 * 
 * @param id          - the library item's id
 * @param title       - the library item's title
 * @param description - the library item's description
 * @param releaseDate - the library item's release date
 * @param isArchive   - verifies if the library item is an archive
 * 
 * @author Anika Kabir
 * @author Wen Hin Brandon Wong (Reviewer)
 */

/**
 * OnlineLibrarySystem - This class serves as the Library Item of the model. It
 * represents each librery item created in the system. This is an abstract class
 * and superclass of Book, Movie, Music Album and Newspaper.
 * 
 * @author Anika Kabir
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class LibraryItem {

    /**
     * Unidirectional association between the Account and Library class with a
     * multiplicity of many to one with its getter and setter methods.
     */
    @ManyToOne(optional = false)
    private Library library;

    public Library getLibrary() {
        return this.library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    // The id attribute for the LibraryItem class as an integer that serves as the
    // primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // The setter method for the id attribute that takes an integer value parameter
    public void setId(int value) {
        this.id = value;
    }

    // The getter method for the id attribute that returns an integer
    public int getId() {
        return this.id;
    }

    // The title attribute for the LibraryItem class as a String
    private String title;

    // The setter method for the title attribute that takes a String value parameter
    public void setTitle(String value) {
        this.title = value;
    }

    // The getter method for the title attribute that returns a String
    public String getTitle() {
        return this.title;
    }

    // The description attribute for the LibraryItem class as a String
    private String description;

    // The setter method for the description attribute that takes a String value
    // parameter
    public void setDescription(String value) {
        this.description = value;
    }

    // The getter method for the description attribute that returns a String
    public String getDescription() {
        return this.description;
    }

    // The releaseDate attribute for the LibraryItem class as a Date
    private Date releaseDate;

    // The setter method for the releaseDate attribute that takes a Date value
    // parameter
    public void setReleaseDate(Date released) {
        this.releaseDate = released;
    }

    // The getter method for the releaseDate attribute that returns a Date
    public Date getReleaseDate() {
        return this.releaseDate;
    }

    // The isArchive attribute for the LibraryItem class as a Boolean
    private Boolean isArchive;

    // The setter method for the isArchive attribute that takes a Boolean value
    // parameter
    public void setIsArchive(Boolean archived) {
        this.isArchive = archived;
    }

    // The getter method for the isArchive attribute that returns a Boolean
    public Boolean getIsArchive() {
        return this.isArchive;
    }
}