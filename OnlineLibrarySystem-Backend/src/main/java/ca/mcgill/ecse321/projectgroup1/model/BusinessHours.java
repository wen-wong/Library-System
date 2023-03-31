package ca.mcgill.ecse321.projectgroup1.model;

// Import Java packages
import java.time.DayOfWeek;

// Import SQL packages
import java.sql.Time;

// Import JPA annotation tags
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * ScheduleHours - This class serves as a daily schedule of the Library in the
 * online library system. It represents the start and end time on the specific
 * day of the week.
 * 
 * @param dayOfWeek - the schedule's day of the week
 * @param startTime - the schedule's start time
 * @param endTime   - the schedule's end time
 * 
 * @author Simon Wang
 */

@Entity
public class BusinessHours {

    /**
     * Unidirectional association between the BusinessHours and Library class with a
     * mupliticity of many to one with its getter and setter methods
     */
    @ManyToOne(optional = false)
    private Library library;

    public Library getLibrary() {
        return this.library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    // The dayOfWeek attribute of the ScheduleHours class as a DayOfWeek
    @Id
    private DayOfWeek dayOfWeek;

    // The setter method for the dayOfWeek attribute that takes a DayOfWeek value
    // parameter
    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    // The getter method for the dayOfWeek attribute that returns a DayOfWeek
    public DayOfWeek getDayOfWeek() {
        return this.dayOfWeek;
    }

    // The startTime attribute of the ScheduleHours class as a Time
    private Time startTime;

    // The setter method for the startTime attribute that takes a Time value
    // parameter
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    // The getter method for the startTime attribute that returns a Time
    public Time getStartTime() {
        return this.startTime;
    }

    // The endTime attribute of the ScheduleHours class as a Time
    private Time endTime;

    // The setter method for the endTime attribute that takes a Time value parameter
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    // The getter method for the endTime attribtue that returns a Time
    public Time getEndTime() {
        return this.endTime;
    }
}