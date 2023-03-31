package ca.mcgill.ecse321.projectgroup1.model;

// Import JPA annotation tag
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Book - This class serves as a book item in the online library system. This
 * inherits all attributes of the LibraryItem class.
 * 
 * @param author     - the book's author
 * @param pageNumber - the book's page numbers
 * 
 * @author Anika Kabir
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Book extends LibraryItem {

    // The author attribute of the Book class as a String
    private String author;

    // The setter method for the author attribute that takes a String value
    // parameter
    public void setAuthor(String value) {
        this.author = value;
    }

    // The getter method for the author attribute that returns a String
    public String getAuthor() {
        return this.author;
    }

    // The pageNumber attribute of the Book class as an integer
    private int pageNumber;

    // The setter method for the pageNumber attribute that takes an integer value
    // parameter
    public void setPageNumber(int pages) {
        this.pageNumber = pages;
    }

    // The getter method for the pageNumber attribute that returns an integer
    public int getPageNumber() {
        return this.pageNumber;
    }
}