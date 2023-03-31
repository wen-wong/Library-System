package ca.mcgill.ecse321.projectgroup1.dto;

// Import Java packages
import java.sql.Time;
import java.time.DayOfWeek;

/**
 * BusinessHoursDto - Data Transfer Objects for the BusinessHours class
 * 
 * @param library   - library that has this business hours
 * @param dayOfWeek - day of the week of the business hours
 * @param startTime - start time of the business hours
 * @param endTime   - end time of the business hours
 * 
 * @author Wen Hin Brandon Wong
 */

public class BusinessHoursDto {

    private LibraryDto library;
    private DayOfWeek dayOfWeek;
    private Time startTime;
    private Time endTime;

    // The contructor of the BusinessHoursDto
    public BusinessHoursDto(LibraryDto library, DayOfWeek dayOfWeek) {
        this(library, dayOfWeek, Time.valueOf("09:00:00"), Time.valueOf("16:59:59"));
    }

    public BusinessHoursDto(LibraryDto library, DayOfWeek dayOfWeek, Time startTime, Time endTime) {
        this.library = library;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // The getter and setter methods of the BusinessHoursDto
    public LibraryDto getLibrary() {
        return library;
    }

    public void setLibrary(LibraryDto library) {
        this.library = library;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }
}