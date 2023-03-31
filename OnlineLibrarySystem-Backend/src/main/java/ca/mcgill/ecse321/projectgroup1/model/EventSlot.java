package ca.mcgill.ecse321.projectgroup1.model;

// Import SQL packages
import java.sql.Date;
import java.sql.Time;

// Import JPA annotation tags
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * EventSlot - This class represents time slots in the LibrarySystem for the
 * Client as events.
 * 
 * @param startDate     - the event slot's start date
 * @param id            - the event slot's id
 * @param endDate       - the event slot's end date
 * @param startTime     - the event slot's start time
 * @param endTime       - the event slot's end time
 * @param description   - the event slot's description
 * 
 * @author Wen Hin Brandon Wong
 */

@Entity
public class EventSlot {

    /**
     * Unidirectional association between the EventSlot and Client class with a
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

    // The id attribute of the TimeSlot class as an integer
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

    // The startDate attribute of the TimeSlot class as a Date
    private Date startDate;

    // The setter method for the startDate that takes a Date value parameter
    public void setStartDate(Date value) {
        this.startDate = value;
    }

    // The getter method for the startDate that returns a Date
    public Date getStartDate() {
        return this.startDate;
    }

    // The endDate attribute of the EventSlot class a Date
    private Date endDate;

    // The setter method for the endDate that takes a Date value parameter
    public void setEndDate(Date value) {
        this.endDate = value;
    }

    // The getter method for the endDate that returns a Date
    public Date getEndDate() {
        return this.endDate;
    }

    // The startTime attribute of the EventSlot class as a Time
    private Time startTime;

    // The setter method for the startTime attribute that takes a Time value
    // parameter
    public void setStartTime(Time value) {
        this.startTime = value;
    }

    // The getter method for the startTime attribute that returns a Time
    public Time getStartTime() {
        return this.startTime;
    }

    // The endTime attribute of the EventSlot class as a Time
    private Time endTime;

    // The setter method for the endTime attribute that takes a Time value parameter
    public void setEndTime(Time value) {
        this.endTime = value;
    }

    // The getter method for the endTime attribute that returns a Time
    public Time getEndTime() {
        return this.endTime;
    }

    // The descriptuon attribute of the of the EventSlot class as a 
    private String description;

    // The setter method for the description attribute
    public void setDescription(String description) {
        this.description = description;
    }

    // The getter method for the description attribute that returns a String
    public String getDescription() {
        return this.description;
    }
}