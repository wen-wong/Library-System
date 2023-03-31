package ca.mcgill.ecse321.projectgroup1.dto;

// Import Java packages
import java.sql.Date;
import java.sql.Time;

/**
 * HolidaySlotsDto - Data Transfer Objects for the HolidaySlot class
 * 
 * @param library   - library that has this holiday
 * @param id        - id of the holiday
 * @param startDate - start date of the holiday
 * @param endDate   - end date of the holiday
 * @param startTime - start time of the holiday
 * @param endTime   - end time of the holiday
 * 
 * @author Simon Wang
 */

public class HolidaySlotDto {

    private LibraryDto library;
    private int id;
    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;

    public HolidaySlotDto(LibraryDto library, int id, Date startDate, Date endDate, Time startTime, Time endTime) {
        this.library = library;
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LibraryDto getLibrary() {
        return library;
    }

    public void setLibrary(LibraryDto library) {
        this.library = library;
    }

    // The getters
    public int getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }
}
