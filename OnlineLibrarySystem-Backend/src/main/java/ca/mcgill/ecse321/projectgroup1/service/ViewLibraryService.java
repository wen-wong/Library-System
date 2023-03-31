package ca.mcgill.ecse321.projectgroup1.service;

import java.util.List;

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
 * ViewLibraryService - This class contains all methods that will let all
 * accounts to view the information of the Library: (1) the information; (2) the
 * business hours; (3) the holiday slots.
 * 
 * @author Brandon
 */

@Service
public class ViewLibraryService {
    @Autowired
    LibraryRepository libraryRepository;

    @Autowired
    BusinessHoursRepository businessHoursRepository;

    @Autowired
    HolidaySlotRepository holidaySlotRepository;

    // Returns the library using the name of the library
    @Transactional
    public Library getLibrary(String name) {
        String error = "";

        if (name == null || name.trim().length() == 0) {
            error += "Library name cannot be empty.";
        }

        // Verify if all parameters are correct
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }

        Library library = libraryRepository.findByName(name);

        return library;
    }

    // Returns a list of all Business Hours of the library
    @Transactional
    public List<BusinessHours> getAllBusinessHours(Library library) {
        String error = "";

        if (library == null) {
            error += "Library does not exist.";
        } else if (!libraryRepository.existsByName(library.getName())) {
            error += "Library does not exist.";
        } else if (library.getName().trim().length() == 0) {
            error += "Library does not exist.";
        }

        // Verify if all parameters are correct
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }

        return businessHoursRepository.findBusinessHoursByLibraryName(library.getName());
    }

    // Returns a list of all Holiday Slots of the library
    @Transactional
    public List<HolidaySlot> getAllHolidaySlots(Library library) {
        String error = "";

        if (library == null) {
            error += "Library does not exist.";
        } else if (!libraryRepository.existsByName(library.getName())) {
            error += "Library does not exist.";
        } else if (library.getName().trim().length() == 0) {
            error += "Library does not exist.";
        }

        // Verify if all parameters are correct
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }

        return holidaySlotRepository.findByLibraryName(library.getName());
    }
}
