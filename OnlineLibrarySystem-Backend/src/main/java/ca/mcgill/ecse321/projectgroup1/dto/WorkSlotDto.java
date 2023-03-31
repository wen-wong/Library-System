package ca.mcgill.ecse321.projectgroup1.dto;

// Import Java packages
import java.sql.Date;
import java.sql.Time;

/**
 * EventSlotsDto - Data Transfer Objects for the WorkSlot class
 * 
 * @param library   - library that has this event
 * @param startDate - start date of the event
 * @param endDate   - end date of the event
 * @param startTime - start time of the event
 * @param endTime   - end time of the event
 * 
 * @author Simon Wang
 */

public class WorkSlotDto {

    private EmployeeDto employee;
    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;

    public WorkSlotDto(EmployeeDto employee, Date startDate, Date endDate, Time startTime, Time endTime) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // setters
    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    // The getters
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

    public EmployeeDto getEmployee() {
        return employee;
    }

}
