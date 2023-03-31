package ca.mcgill.ecse321.projectgroup1.model;

// Import SQL packages
import java.sql.Date;

// Import JPA annotation tags
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * InUseSlot - This class serves as a InUse slot of a library item in the online
 * library system. It represents the status and the length of the status of the
 * library item.
 * 
 * @param id        - the InUse slot's id as the primary key
 * @param startDate - the InUse slot's start date
 * @param endDate   - the InUse slot's end date
 * @param status    - the InUse slot's status
 * 
 * @author Wen Hin Brandon Wong
 * @author Wen Hin Brandon Wong (Reviewer)
 */

@Entity
public class InUseSlot {
    /**
     * Unidirectional association between the InUseSlot and LibraryItem class with a
     * multiplicity of many to one with its getter and setter methods.
     */
    @ManyToOne(optional = false)
    private LibraryItem libraryItem;

    public LibraryItem getLibraryItem() {
        return this.libraryItem;
    }

    public void setLibraryItem(LibraryItem libraryItem) {
        this.libraryItem = libraryItem;
    }

    /**
     * Unidirectional association between the LibraryItem and Client class with a
     * multiplicity of many to one with its getter and setter methods.
     */
    @ManyToOne(optional = false)
    private Client client;

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    // The id attribute of the InUseSlot as an integer
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // The setter method for the id attribute that takes an integer value
    public void setId(int id) {
        this.id = id;
    }

    // The getter method for the id attribtue that returns an integer
    public int getId() {
        return this.id;
    }

    // The startDate attribute that represents the starting date of the status as a
    // Date
    private Date startDate;

    // The getter method for the startDate attribute that returns the Date object
    public Date getStartDate() {
        return this.startDate;
    }

    // The setter method for the startDate attribute that takes an object Date
    public void setStartDate(Date date) {
        this.startDate = date;
    }

    // The endDate attribute that represents the ending date of the status as a Date
    private Date endDate;

    // The getter method for the endDate attribute that returns the Date object
    public Date getEndDate() {
        return this.endDate;
    }

    // The setter method for the endDate attribute that takes an object Date
    public void setEndDate(Date date) {
        this.endDate = date;
    }

    // The enumeration class of Status
    public enum Status {
        Booked, Reserved, InCart
    }

    // The status attribute of the InUseSlot class as a Status
    private Status status;

    // The getter method for the status attribute that returns the Status object
    public Status getStatus() {
        return this.status;
    }

    // The setter method for the status attribute that takes a Status object
    public void setStatus(Status statuss) {
        this.status = statuss;
    }
}