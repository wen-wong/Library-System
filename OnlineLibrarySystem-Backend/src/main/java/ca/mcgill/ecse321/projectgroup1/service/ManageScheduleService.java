package ca.mcgill.ecse321.projectgroup1.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup1.dao.EmployeeRepository;
import ca.mcgill.ecse321.projectgroup1.dao.LibraryRepository;
import ca.mcgill.ecse321.projectgroup1.dao.WorkSlotRepository;
import ca.mcgill.ecse321.projectgroup1.model.Employee;
import ca.mcgill.ecse321.projectgroup1.model.WorkSlot;

@Service
public class ManageScheduleService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    WorkSlotRepository workSlotRepository;

    @Autowired
    LibraryRepository libraryRepository;

    /**
     * This service methods creates a new schedule
     * 
     * @param employee  - The employee instance
     * @param startDate - schedule's start date
     * @param endDate   - schedule's end date
     * @param startTime - schedule's start time
     * @param endTime   - schedules's end time
     * @return - The new schedule
     * 
     * @author Chloe
     */

    @Transactional
    public WorkSlot createSchedule(Employee employee, Time startTime, Time endTime, Date startDate, Date endDate) {
        String error = "";

        if (employee == null || !employeeRepository.existsById(employee.getId())) {
            error += "Employee must exist to create a schedule!";
        }

        if (startTime == null) {
            error += "Schedule needs a start time!";
        }
        if (endTime == null) {
            error += "Schedule needs an end time!";
        }

        if (startTime != null && endTime != null && endTime.before(startTime)) {
            error += "schedule end time cannot be before schedule start time.";
        }
        if (startDate == null) {
            error += "Schedule needs a start date!";
        }
        if (endDate == null) {
            error += "Schedule needs an end date!";
        }
        if (startDate != null && endDate != null && endDate.before(startDate)) {
            error += "schedule end date cannot be before schedule start date.";
        }
       
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }

        WorkSlot schedule = new WorkSlot();
        schedule.setEmployee(employee);
        schedule.setStartTime(startTime);
        schedule.setEndTime(endTime);
        schedule.setStartDate(startDate);
        schedule.setEndDate(endDate);
        workSlotRepository.save(schedule);
        return schedule;
    }
    
      
    	   
    // Delete an existing WorkSlot
    @Transactional
    public WorkSlot deleteWorkSlot(int id) {
    	
    	WorkSlot workSlot = workSlotRepository.findById(id);
    	
    	String error = "";

    	
    	if (workSlot == null) {
    		error += "WorkSlot does not exist.";
    	}
    	
    	// Verify if all parameters are correct
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
    	
    	workSlotRepository.delete(workSlot);
    	
    	return workSlot;
    }
    
    /**
     * Service method that returns a list of schedules from the Repository *
     * 
     * @param id - The schedule's id
     * 
     * @author Chloe
     */

    @Transactional
    public WorkSlot getSchedule(int id) {
    	for (var sched : workSlotRepository.findAll()) {
    		if(sched.getEmployee().getId() == id) {
    			return sched;
    		}
    	}
    	return null;
   
    }
    
    /**
     * Service method that finds all the work slots
     * @return  - All the work slots as a list
     * 
     * @author Chloe
     */
    @Transactional
    public List<WorkSlot> getAllWorkSlots() {
        
        List<WorkSlot> allWorkSlots = new ArrayList<WorkSlot>();
        for (WorkSlot e : workSlotRepository.findAll()){
            allWorkSlots.add(e);
        }

        return allWorkSlots;
    }


    /**
     * Service method that fires an employee by their id
     * 
     * @param id - The employee's id
     * @return - The employee instance
     * 
     * @author Chloe
     */
    @Transactional
    public Employee fireEmployee(int id) {
        employeeRepository.deleteById(id);
        return null;
    }
}




