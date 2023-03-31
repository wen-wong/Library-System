package ca.mcgill.ecse321.projectgroup1.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup1.dto.EventSlotDto;
import ca.mcgill.ecse321.projectgroup1.model.Client;
import ca.mcgill.ecse321.projectgroup1.model.EventSlot;
import ca.mcgill.ecse321.projectgroup1.service.ManageAccountService;
import ca.mcgill.ecse321.projectgroup1.service.ManageEventService;

@CrossOrigin(origins = "*")
@RestController
public class ManageEventController {
    @Autowired
    private ManageEventService service;

    @Autowired
    private ManageAccountService accountService;

    /**
     * Method creates an event slot, saves it to the repository, and returns it as a
     * DTO.
     * 
     * @param clientDto Client that is creating the event
     * @param startDate Event's start date
     * @param endDate   Event's end date
     * @param startTime Event's start time
     * @param endTime   Event's start time
     * @return Event Slot DTO
     * @throws IllegalArgumentException
     * 
     * @author Philippe
     */
    @PostMapping(value = { "/events", "/events" })
    public EventSlotDto createEventSlot(@RequestParam(name = "client") int clientId,
            @RequestParam(name = "startDate") Date startDate, @RequestParam(name = "endDate") Date endDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime,
            @RequestParam(name = "description") String description)
            throws IllegalArgumentException {

        Client client = accountService.getClient(clientId);
        EventSlot eventSlot = service.createEventSlot(client, startDate, endDate, Time.valueOf(startTime),
                Time.valueOf(endTime), description);

        return ControllerConvert.convertToDto(eventSlot);
    }

    /**
     * Method modifies the dates and times of a given event slot and returns the
     * edited event slot as a DTO.
     * 
     * @param id        Event slot's id
     * @param startDate New start date
     * @param endDate   New end date
     * @param startTime New start time
     * @param endTime   New end time
     * @return Event slot
     * @throws IllegalArgumentException
     * 
     * @author Philippe
     */
    @PutMapping(value = { "/events/edit/{name}/", "/events/edit/{name}" })
    public EventSlotDto editEventSlot(@PathVariable("name") int id, @RequestParam(name = "startDate") Date startDate,
            @RequestParam(name = "endDate") Date endDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime,
            @RequestParam(name = "description") String description)
            throws IllegalArgumentException {

        service.editEventSlot(id, startDate, endDate, Time.valueOf(startTime), Time.valueOf(endTime), description);

        return ControllerConvert.convertToDto(service.getEventSlot(id));
    }

    /**
     * Method returns an event slot as a DTO given its id.
     * 
     * @param id Event's id
     * @return Event DTO
     * @throws IllegalArgumentException
     * 
     * @author Philippe
     */
    @GetMapping(value = { "/events/{name}", "/events/{name}/" })
    public EventSlotDto getEventSlot(@PathVariable("name") int id) throws IllegalArgumentException {
        return ControllerConvert.convertToDto(service.getEventSlot(id));

    }

    /**
     * Method deletes an event slot
     * @param id
     * @return deleted event slot
     * @throws IllegalArgumentException
     */
    @GetMapping(value = { "/events/remove/{name}", "/events/remove/{name}/" })
    public EventSlotDto deleteEventSlot(@PathVariable("name") int id) throws IllegalArgumentException {
        return ControllerConvert.convertToDto(service.deleteEventSlot(id));
    }

    /**
     * Method returns a list of events that were created by a client.
     * 
     * @param id Client's id
     * @return List of events
     * @throws IllegalArgumentException
     * 
     * @author Philippe
     */
    @GetMapping(value = { "/events/client/{name}", "/events/client/{name}/" })
    public List<EventSlotDto> getEventSlotsByClientId(@PathVariable("name") int id) throws IllegalArgumentException {
        List<EventSlotDto> events = new ArrayList<EventSlotDto>();
        for (EventSlot e : service.getEventSlotsByClientId(id))
            events.add(ControllerConvert.convertToDto(e));

        return events;

    }

    /**
     * Method returns a list of all the event slots from the library
     * 
     * @return List of events
     * 
     * @author Philippe
     */
    @GetMapping(value = { "/events", "/events/" })
    public List<EventSlotDto> getAllEventSlots() {
        List<EventSlotDto> events = new ArrayList<EventSlotDto>();
        for (EventSlot e : service.getAllEventSlots())
            events.add(ControllerConvert.convertToDto(e));

        return events;
    }

    /**
     * Method returns a list of events given a Date
     * @param  date Date to be compared with
     * @return      List of events
     * @throws IllegalArgumentException
     * 
     * @author Philippe
     */
    @GetMapping(value = {"/events/availabilities", "/events/availabilities/"})
    public List<EventSlotDto> getEventSlotsByDate(@RequestParam("date") String date) throws IllegalArgumentException {
        List<EventSlotDto> events = new ArrayList<EventSlotDto>();
        for (EventSlot e : service.getEventSlotsByDate(Date.valueOf(date))) {
            events.add(ControllerConvert.convertToDto(e));
        }

        return events;
    }

}
