package ca.mcgill.ecse321.projectgroup1.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup1.dto.BusinessHoursDto;
import ca.mcgill.ecse321.projectgroup1.dto.HolidaySlotDto;
import ca.mcgill.ecse321.projectgroup1.dto.LibraryDto;
import ca.mcgill.ecse321.projectgroup1.model.BusinessHours;
import ca.mcgill.ecse321.projectgroup1.model.HolidaySlot;
import ca.mcgill.ecse321.projectgroup1.model.Library;
import ca.mcgill.ecse321.projectgroup1.service.ManageLibraryService;
import ca.mcgill.ecse321.projectgroup1.service.ViewLibraryService;

/**
 * ManageLibraryController - API calls to manage the library information,
 * business hours and holiday slots
 * 
 * @author Brandon
 */
@CrossOrigin(origins = "*")
@RestController
public class ManageLibraryController {

    @Autowired
    private ManageLibraryService manageService;

    @Autowired
    private ViewLibraryService viewService;

    // Creates a Library instance given the appropriate arguments and returns the
    // Library instance
    @PostMapping(value = { "/library/{name}", "/library/{name}/" })
    public LibraryDto createLibrary(@PathVariable("name") String name, @RequestParam("address") String address,
            @RequestParam("phonenumber") String phoneNumber, @RequestParam("email") String email)
            throws IllegalArgumentException {
        Library library = manageService.createLibrary(name, address, phoneNumber, email);
        return ControllerConvert.convertToDto(library);
    }

    // Edits a Library instance given the appropriate arguments and returns the
    // Library instance
    @PutMapping(value = { "/editlibrary/{name}", "/editlibrary/{name}/" })
    public LibraryDto editLibrary(@PathVariable("name") String libraryName, @RequestParam("address") String address,
            @RequestParam("phonenumber") String phoneNumber, @RequestParam("email") String email)
            throws IllegalArgumentException {

        Library library = manageService.editLibrary(libraryName, address, phoneNumber, email);

        return ControllerConvert.convertToDto(library);
    }

    // Creates a BusinessHours instance given the appropriate arguments and returns
    // the BusinessHours instance
    @PostMapping(value = { "/businesshours/library/{name}", "/businesshours/library/{name}/" })
    public BusinessHoursDto createBusinessHours(@PathVariable("name") String libraryName,
            @RequestParam("dayofweek") DayOfWeek dayOfWeek,
            @RequestParam("starttime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm:ss") LocalTime startTime,
            @RequestParam("endtime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm:ss") LocalTime endTime)
            throws IllegalArgumentException {

        Library library = viewService.getLibrary(libraryName);

        BusinessHours businessHours = manageService.createBusinessHours(library, dayOfWeek, Time.valueOf(startTime),
                Time.valueOf(endTime));
        return ControllerConvert.convertToDto(businessHours);
    }

    // Edits a BusinessHours instance given the appropriate arguments and returns
    // the BusinessHours instance
    @PutMapping(value = { "/businesshours/{dayOfWeek}", "/businesshours/{dayOfWeek}/" })
    public BusinessHoursDto editBusinessHours(@PathVariable("dayOfWeek") DayOfWeek dayOfWeek,
            @RequestParam("starttime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm:ss") LocalTime startTime,
            @RequestParam("endtime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm:ss") LocalTime endTime)
            throws IllegalArgumentException {

        BusinessHours businessHours = manageService.editBusinessHours(dayOfWeek, Time.valueOf(startTime),
                Time.valueOf(endTime));

        return ControllerConvert.convertToDto(businessHours);
    }

    // Returns the requested BusinessHour instances using the associated
    // day of the week
    @GetMapping(value = { "/businesshour/{dayOfWeek}", "/businesshour/{dayOfWeek}/" })
    public BusinessHoursDto getBusinessHours(@PathVariable("dayOfWeek") DayOfWeek dayOfWeek)
            throws IllegalArgumentException {

        BusinessHours businessHours = manageService.getBusinessHours(dayOfWeek);

        return ControllerConvert.convertToDto(businessHours);
    }

    // Creates a HolidaySlot instance given the appropriate arguments and returns
    // the HolidaySlot instance
    @PostMapping(value = { "/holidayslot/library/{name}", "/holidayslot/library/{name}/" })
    public HolidaySlotDto createHolidaySlot(@PathVariable("name") String libraryName,
            @RequestParam("startdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam("enddate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") LocalDate endDate,
            @RequestParam("starttime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm:ss") LocalTime startTime,
            @RequestParam("endtime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm:ss") LocalTime endTime)
            throws IllegalArgumentException {

        Library library = viewService.getLibrary(libraryName);

        HolidaySlot holidaySlot = manageService.createHolidaySlot(library, Date.valueOf(startDate),
                Date.valueOf(endDate), Time.valueOf(startTime), Time.valueOf(endTime));

        return ControllerConvert.convertToDto(holidaySlot);
    }

    // Returns the requested HolidaySlot instances using the associated
    // id
    @PutMapping(value = { "/editholidayslot/{id}", "/editholidayslot/{id}/" })
    public HolidaySlotDto editHolidaySlot(@PathVariable("id") int id,
            @RequestParam("startdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam("enddate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") LocalDate endDate,
            @RequestParam("starttime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm:ss") LocalTime startTime,
            @RequestParam("endtime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm:ss") LocalTime endTime)
            throws IllegalArgumentException {

        HolidaySlot holidaySlot = manageService.editHolidaySlot(id, Date.valueOf(startDate), Date.valueOf(endDate),
                Time.valueOf(startTime), Time.valueOf(endTime));

        return ControllerConvert.convertToDto(holidaySlot);
    }

    // Deletes a BusinessHours instance given the appropriate arguments and returns
    // the BusinessHours instance
    @DeleteMapping(value = { "/deleteholidayslot/{id}", "/deleteholidayslot/{id}/" })
    public Object deleteHolidaySlot(@PathVariable("id") int id) throws IllegalArgumentException {
        try {
            HolidaySlot holidaySlot = manageService.deleteHolidaySlot(id);
            return ControllerConvert.convertToDto(holidaySlot);
        } catch (IllegalArgumentException error) {
            return error.getMessage();
        }
    }

    // Returns the requested HolidaySlot instances using the associated
    // HolidaySlot id
    @GetMapping(value = { "/holidayslot/{id}", "/holidayslot/{id}/" })
    public HolidaySlotDto getHolidaySlots(@PathVariable("id") int id) throws IllegalArgumentException {

        HolidaySlot holidaySlot = manageService.getHolidaySlot(id);

        return ControllerConvert.convertToDto(holidaySlot);
    }
}
