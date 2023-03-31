package ca.mcgill.ecse321.projectgroup1.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup1.dto.EmployeeDto;
import ca.mcgill.ecse321.projectgroup1.dto.WorkSlotDto;
import ca.mcgill.ecse321.projectgroup1.model.Employee;
import ca.mcgill.ecse321.projectgroup1.model.WorkSlot;
import ca.mcgill.ecse321.projectgroup1.service.ManageAccountService;
import ca.mcgill.ecse321.projectgroup1.service.ManageScheduleService;

@CrossOrigin(origins = "*")
@RestController
public class ManageScheduleController {

    @Autowired
    private ManageScheduleService scheduleService;
    
    @Autowired
    private ManageAccountService accountService;


    // Creates a WorkSlot instance given the appropriate arguments and returns the WorkSlot instance
   @PostMapping(value = { "/workslots/employee/{id}", "/workslots/employee/{id}/" })
   public Object createWorkSlot(
		   @PathVariable("id") int employeeid,
           @RequestParam("startdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") LocalDate startDate,
           @RequestParam("enddate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") LocalDate endDate,
           @RequestParam("starttime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm:ss") LocalTime startTime,
           @RequestParam("endtime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm:ss") LocalTime endTime,
           @RequestParam String name)
           throws IllegalArgumentException {

         Employee employee = accountService.getEmployee(employeeid);

        WorkSlot workSlot = scheduleService.createSchedule(employee, Time.valueOf(startTime), Time.valueOf(endTime),
                Date.valueOf(startDate), Date.valueOf(endDate));
        
        return ControllerConvert.convertToDto(workSlot);
    }
   
    // Deletes a workSlot instance given the appropriate arguments and returns the workSlot instance
    @DeleteMapping(value = { "/deleteworkslot/{id}", "/deleteworkslot/{id}/"  }) 
    public Object deleteWorkSlot(@PathVariable("id") int id) throws IllegalArgumentException {
        try {
           WorkSlot workSlot = scheduleService.deleteWorkSlot(id);
            return ControllerConvert.convertToDto(workSlot);
        } catch (IllegalArgumentException error) {
            return error.getMessage();
        }
    }
    
 // Returns the requested WorkSlot instances using the associated WorkSlot id
 @GetMapping(value = { "/workslot/{id}", "/workslot/{id}/" })
 public WorkSlotDto getWorkSlots(@PathVariable("id") int id) 
		 throws IllegalArgumentException {

     WorkSlot workSlot = scheduleService.getSchedule(id); 

     return ControllerConvert.convertToDto(workSlot);
 }
 
 /**
  * Method returns a list of all the work slots from the library
  * 
  * @return List of work slots
  * 
  * @author Chloe
  */
 @GetMapping(value = { "/workSlots", "/workSlots/" })
 public List<WorkSlotDto> getAllWorkSlots() {
     List<WorkSlotDto> slots = new ArrayList<WorkSlotDto>();
     for (WorkSlot e : scheduleService.getAllWorkSlots())
         slots.add(ControllerConvert.convertToDto(e));

     return slots;
 }


    // Fire an employee by their id
    @PostMapping(value = { "/accounts/employees/{id}", "/accounts/employees/{id}/" })
    public EmployeeDto fireEmployee(@PathVariable("id") int id)

            throws IllegalArgumentException {

        Employee employee = scheduleService.fireEmployee(id);
        return ControllerConvert.convertToDto(employee);
    }
    

    
    
}


