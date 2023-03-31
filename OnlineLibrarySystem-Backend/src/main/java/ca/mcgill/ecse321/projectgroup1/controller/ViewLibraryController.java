package ca.mcgill.ecse321.projectgroup1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup1.dto.BusinessHoursDto;
import ca.mcgill.ecse321.projectgroup1.dto.HolidaySlotDto;
import ca.mcgill.ecse321.projectgroup1.dto.LibraryDto;
import ca.mcgill.ecse321.projectgroup1.model.BusinessHours;
import ca.mcgill.ecse321.projectgroup1.model.HolidaySlot;
import ca.mcgill.ecse321.projectgroup1.model.Library;
import ca.mcgill.ecse321.projectgroup1.service.ViewLibraryService;

@CrossOrigin(origins = "*")
@RestController
public class ViewLibraryController {

    @Autowired
    private ViewLibraryService service;

    // Returns the requested Library instance
    @GetMapping(value = { "/library/{name}", "/library/{name}/" })
    public LibraryDto getLibrary(@PathVariable("name") String name) throws IllegalArgumentException {
        return ControllerConvert.convertToDto(service.getLibrary(name));
    }

    // Returns the list of requested BusinessHours instances using the associated
    // library name
    @GetMapping(value = { "/allBusinesshours/library/{name}", "/allBusinesshours/library/{name}/" })
    public List<BusinessHoursDto> getAllBusinessHours(@PathVariable("name") String libraryName)
            throws IllegalArgumentException {

        List<BusinessHoursDto> businessHoursDtos = new ArrayList<>();
        Library library = service.getLibrary(libraryName);

        for (BusinessHours businessHours : service.getAllBusinessHours(library)) {
            businessHoursDtos.add(ControllerConvert.convertToDto(businessHours));
        }

        return businessHoursDtos;
    }

    // Returns the list of requested HolidaySlot instances using the associated
    // library name
    @GetMapping(value = { "/allHolidayslots/library/{name}", "/allHolidayslots/library/{name}/" })
    public List<HolidaySlotDto> getAllHolidaySlots(@PathVariable("name") String libraryName)
            throws IllegalArgumentException {

        List<HolidaySlotDto> holidaySlotDtos = new ArrayList<>();
        Library library = service.getLibrary(libraryName);

        for (HolidaySlot holidaySlots : service.getAllHolidaySlots(library)) {
            holidaySlotDtos.add(ControllerConvert.convertToDto(holidaySlots));
        }

        return holidaySlotDtos;
    }
}
