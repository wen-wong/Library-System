package ca.mcgill.ecse321.projectgroup1.dto;

// Import SQL packages
import java.sql.Date;
import java.sql.Time;

/**
 * EventSlotDto - Data Transfer Object for the EventSlot class
 * 
 * @param client        - the client that created this event
 * @param id            - the event slot's id
 * @param startDate     - the event slot's start date
 * @param endDate       - the event slot's end date
 * @param startTime     - the event slot's start time
 * @param endTime       - the event slot's end time
 * @param description   - the event slot's description
 *
 * 
 * @author Philippe
 */

public class EventSlotDto {

    private ClientDto client;
    private int id;
    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;
    private String description;

    // The contructors of the EventSlotDto

    // If endDate is not given, then assume it is a one-day event
    public EventSlotDto(ClientDto client, int id, Date startDate, Time startTime, Time endTime, String description) {
        this.client = client;
        this.id = id;
        this.startDate = startDate;
        this.endDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    public EventSlotDto(ClientDto client, int id, Date startDate, Date endDate, Time startTime, Time endTime, String description) {
        this.client = client;
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;

    }

    // The getter and setter methods of the EvenSlotDto
    public ClientDto getClient() {
        return this.client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public int getId() {
        return this.id;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public Time getStartTime() {
        return this.startTime;
    }

    public Time getEndTime() {
        return this.endTime;
    }

    public String getDescription() {
        return this.description;
    }

}
