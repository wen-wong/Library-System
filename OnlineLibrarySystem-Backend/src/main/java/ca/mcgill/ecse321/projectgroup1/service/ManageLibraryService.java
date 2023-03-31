package ca.mcgill.ecse321.projectgroup1.service;

import java.sql.Date;
import java.sql.Time;
import java.time.DayOfWeek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup1.dao.BusinessHoursRepository;
import ca.mcgill.ecse321.projectgroup1.dao.HolidaySlotRepository;
import ca.mcgill.ecse321.projectgroup1.dao.LibraryRepository;
import ca.mcgill.ecse321.projectgroup1.model.BusinessHours;
import ca.mcgill.ecse321.projectgroup1.model.HolidaySlot;
import ca.mcgill.ecse321.projectgroup1.model.Library;

/**
 * ManageLibraryService - This class contains all methods that will let the Head
 * Librarian to edit the information of the Library: (1) the information; (2)
 * the business hours; (3) the holiday slots.
 * 
 * @author Brandon
 */

@Service
public class ManageLibraryService {
    @Autowired
    LibraryRepository libraryRepository;

    @Autowired
    BusinessHoursRepository businessHoursRepository;

    @Autowired
    HolidaySlotRepository holidaySlotRepository;
    
    // Create an instance of Library
    @Transactional
    public Library createLibrary(String name, String address, String phoneNumber, String email) {
        String error = "";

        if (name == null || name.trim().length() == 0) {
            error += "Library name cannot be empty.";
        }

        if (address == null || address.trim().length() == 0) {
            error += "Library address cannot be empty.";
        }

        if (phoneNumber == null || phoneNumber.trim().length() == 0) {
            error += "Library phone number cannot be empty.";
        }

        if (email == null || email.trim().length() == 0) {
            error += "Library email cannot be empty.";
        }

        // Verify if all parameters are correct
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }

        // Create the Library instance
        Library library = new Library();
        library.setName(name);
        library.setAddress(address);
        library.setPhoneNumber(phoneNumber);
        library.setEmail(email);
        libraryRepository.save(library);

        return library;
    }

    // Edit the information of the library
    @Transactional
    public Library editLibrary(String name, String address, String phoneNumber, String email) {
        Library library = libraryRepository.findByName(name);

        String error = "";

        if (library == null) {
            error += "Library does not exist.";
        }

        if (name == null || name.trim().length() == 0) {
            error += "Library name cannot be empty.";
        }

        if (address == null || address.trim().length() == 0) {
            error += "Library address cannot be empty.";
        }

        if (phoneNumber == null || phoneNumber.trim().length() == 0) {
            error += "Library phone number cannot be empty.";
        }

        if (email == null || email.trim().length() == 0) {
            error += "Library email cannot be empty.";
        }

        // Verify if all parameters are correct
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }

        // Edit the Library instance
        library.setName(name);
        library.setAddress(address);
        library.setPhoneNumber(phoneNumber);
        library.setEmail(email);
        libraryRepository.save(library);

        return library;
    }

    // Create a new Business Hours
    @Transactional
    public BusinessHours createBusinessHours(Library library, DayOfWeek dayOfWeek, Time startTime, Time endTime) {
        String error = "";

        if (library == null) {
            error += "Library needs to be selected to create the business hours.";
        } else if (!libraryRepository.existsByName(library.getName())) {
            error += "Library does not exist.";
        }

        if (dayOfWeek == null) {
            error += "Day of the week needs to be selected to create the business hours.";
        }

        if (startTime == null) {
            error += "Business hour's start time cannot be empty.";
        }

        if (endTime == null) {
            error += "Business hour's end time cannot be empty.";
        }

        // Verify that the startTime is before the endTime of the Business Hours
        if (startTime != null && endTime != null && endTime.before(startTime)) {
            error += "Business hour end time cannot be before business hour start time.";
        }

        // Verify if all parameters are correct
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }

        // Create the Business Hours instance
        BusinessHours businessHours = new BusinessHours();
        businessHours.setLibrary(library);
        businessHours.setDayOfWeek(dayOfWeek);
        businessHours.setStartTime(startTime);
        businessHours.setEndTime(endTime);
        businessHoursRepository.save(businessHours);

        return businessHours;
    }

    // Edit an existing Business Hours
    @Transactional
    public BusinessHours editBusinessHours(DayOfWeek dayOfWeek, Time startTime, Time endTime) {
        BusinessHours businessHours = businessHoursRepository.findByDayOfWeek(dayOfWeek);

        String error = "";

        if (businessHours == null) {
            error += "Business hour does not exist.";
        }

        if (startTime == null) {
            error += "Business hour's start time cannot be empty.";
        }

        if (endTime == null) {
            error += "Business hour's end time cannot be empty.";
        }

        // Verify that the startTime is before the endTime of the Business Hours
        if (startTime != null && endTime != null && endTime.before(startTime)) {
            error += "Business hour end time cannot be before business hour start time.";
        }

        // Verify if all parameters are correct
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }

        // Create the Business Hours instance
        businessHours.setDayOfWeek(dayOfWeek);
        businessHours.setStartTime(startTime);
        businessHours.setEndTime(endTime);
        businessHoursRepository.save(businessHours);

        return businessHours;
    }

    // Returns a specific Business Hours using the day of the week
    @Transactional
    public BusinessHours getBusinessHours(DayOfWeek dayOfWeek) {
    	String error = "";
    	
    	if (dayOfWeek == null) {
    		error += "Day of the week needs to be selected to get the business hours.";
    	}
    	
    	// Verify if all parameters are correct
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
    	
        return businessHoursRepository.findByDayOfWeek(dayOfWeek);
    }

    // Create a new Holiday Slot
    @Transactional
    public HolidaySlot createHolidaySlot(Library library, Date startDate, Date endDate, Time startTime, Time endTime) {
        String error = "";

        if (library == null) {
            error += "Library needs to be selected to create the holiday slot.";
        } else if (!libraryRepository.existsByName(library.getName())) {
            error += "Library does not exist.";
        }

        if (startDate == null) {
            error += "Holiday slot's start date cannot be empty.";
        }

        if (endDate == null) {
            error += "Holiday slot's end date cannot  be empty.";
        }

        if (startTime == null) {
            error += "Holiday slot's start time cannot be empty.";
        }

        if (endTime == null) {
            error += "Holiday slot's end time cannot be empty.";
        }
        
        // Verify that the startDate is before the endDate of the Holiday Slot
        if (startDate != null && endDate != null && endDate.before(startDate)) {
            error += "Holiday slot end date cannot be before holiday slot start date.";
        }

        // Verify that the startTime is before the endTime of the Holiday Slot
        if (startTime != null && endTime != null && endTime.before(startTime)) {
            error += "Holiday slot end time cannot be before holiday slot start time.";
        }

        // Verify if all parameters are correct
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }

        // Creates the Holiday Slot instance
        HolidaySlot holidaySlot = new HolidaySlot();
        holidaySlot.setLibrary(library);
        holidaySlot.setStartDate(startDate);
        holidaySlot.setEndDate(endDate);
        holidaySlot.setStartTime(startTime);
        holidaySlot.setEndTime(endTime);
        holidaySlotRepository.save(holidaySlot);

        return holidaySlot;
    }

    // Edit an existing Holiday Slot
    @Transactional
    public HolidaySlot editHolidaySlot(int id, Date startDate, Date endDate, Time startTime, Time endTime) {
        HolidaySlot holidaySlot = holidaySlotRepository.findById(id);

        String error = "";

        if (holidaySlot == null) {
            error += "Holiday slot does not exist.";
        }

        if (startDate == null) {
            error += "Holiday slot's start date cannot be empty.";
        }

        if (endDate == null) {
            error += "Holiday slot's end date cannot  be empty.";
        }

        if (startTime == null) {
            error += "Holiday slot's start time cannot be empty.";
        }

        if (endTime == null) {
            error += "Holiday slot's end time cannot be empty.";
        }
        
        // Verify that the startDate is before the endDate of the Holiday Slot
        if (startDate != null && endDate != null && endDate.before(startDate)) {
            error += "Holiday slot end date cannot be before holiday slot start date.";
        }

        // Verify that the startTime is before the endTime of the Holiday Slot
        if (startTime != null && endTime != null && endTime.before(startTime)) {
            error += "Holiday slot end time cannot be before holiday slot start time.";
        }

        // Verify if all parameters are correct
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }

        // Creates the Holiday Slot instance
        holidaySlot.setStartDate(startDate);
        holidaySlot.setEndDate(endDate);
        holidaySlot.setStartTime(startTime);
        holidaySlot.setEndTime(endTime);
        holidaySlotRepository.save(holidaySlot);

        return holidaySlot;
    }
    
    // Delete an existing HolidaySlot
    @Transactional
    public HolidaySlot deleteHolidaySlot(int id) {
    	
    	HolidaySlot holidaySlot = holidaySlotRepository.findById(id);
    	
    	String error = "";

    	
    	if (holidaySlot == null) {
    		error += "HolidaySlot does not exist.";
    	}
    	
    	// Verify if all parameters are correct
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
    	
    	holidaySlotRepository.delete(holidaySlot);
    	
    	return holidaySlot;
    }

    // Returns a specific Holiday Slot of using the id of the Holiday Slot
    @Transactional
    public HolidaySlot getHolidaySlot(int id) {
        return holidaySlotRepository.findById(id);
    }
}
