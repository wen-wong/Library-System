package ca.mcgill.ecse321.projectgroup1.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup1.dao.ClientRepository;
import ca.mcgill.ecse321.projectgroup1.dao.EventSlotRepository;
import ca.mcgill.ecse321.projectgroup1.model.Client;
import ca.mcgill.ecse321.projectgroup1.model.EventSlot;

@Service
public class ManageEventService {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    EventSlotRepository eventSlotRepository;


    /**
     * This service methods creates a new event slot
     * @param client    - The client instance
     * @param startDate - Event's start date
     * @param endDate   - Event's end date
     * @param startTime - Event's start time
     * @param endTime   - Event's end time
     * @param description - Event slot's description
     * @return          - The new event slot
     * 
     * @author Philippe
     */
    @Transactional
    public EventSlot createEventSlot(Client client, Date startDate, 
    Date endDate, Time startTime, Time endTime, String description) {
        // Input Sanitization
        String error = "";
        if (client == null || !clientRepository.existsById(client.getId())) error += "Client must exist to create an event!";
        if (description == null) error += "Description must not be empty!";

        boolean nullDate = false, nullTime = false;
        if (startDate == null) {error += "Event needs a start date!"; nullDate = true;}
        if (startTime == null) {error += "Event needs a start time!"; nullTime = true;}
        if (endDate == null) {error += "Event needs an end date!"; nullDate = true;}
        if (endTime == null) {error += "Event needs an end time!"; nullTime = true;}
        
        if (!nullDate && startDate.after(endDate)) error += "Start date must be before end date!";
        if (!nullDate && !nullTime && startDate.equals(endDate) && startTime.after(endTime)) 
            error += "Start time must be before end time!";
        
        if (!nullDate && !nullTime && eventSlotOverlap(startDate, endDate, startTime, endTime))
            error += "Event slot must not overlap with an existing event slot!";
        error = error.trim();
        if (error.length() > 0) throw new IllegalArgumentException(error);
        
        EventSlot eventSlot = new EventSlot();
        eventSlot.setClient(client);
        eventSlot.setStartDate(startDate);
        eventSlot.setStartTime(startTime);
        eventSlot.setEndDate(endDate);
        eventSlot.setEndTime(endTime);
        eventSlot.setDescription(description);
        eventSlotRepository.save(eventSlot);
        return eventSlot;
    }
   
    /**
     * Helper method for createEventSlot that checks if an event slot 
     * overlaps with an existing one.
     * 
     * @param startDate     new eventslot's start date
     * @param endDate       new eventslot's end date
     * @param startTime     new eventslot's start time
     * @param endTime       new eventslot's start time
     * @return true if there is an overlap, false otherwise
     * 
     * @author Philippe
     */
    private boolean eventSlotOverlap(Date startDate, Date endDate, Time startTime, Time endTime) {
		
		for (EventSlot e: eventSlotRepository.findAll()) {
            
            //              |----eventSlot----|  
            //      |-------e-----|                                 
            if (startDate.before(e.getEndDate()) && endDate.after(e.getStartDate()))
                return true;
                
            // same start date and end date
            if (startDate.equals(e.getStartDate()) && endDate.equals(e.getEndDate())){
                if (startTime.before(e.getEndTime()) && endTime.after(e.getStartTime())) 
                return true;
            }
            
            //  |------eventSlot-----|
            //                       |------e----|
            if (endDate.equals(e.getStartDate()) && endTime.after(e.getStartTime()))
                return true;
            
            //  |------e-----|
            //               |------eventSlot----|
            if (e.getEndDate().equals(startDate) && e.getEndTime().after(startTime))
                return true;
        }
		return false;
    }

     /**
     * Helper method for editEventSlot that checks if the edited event slot 
     * overlaps with an existing event slot.
     * 
     * @param id            - eventslot's id  
     * @param startDate     - eventslot's updated start date
     * @param endDate       - eventslot's updated end date
     * @param startTime     - eventslot's updated start time
     * @param endTime       - eventslot's updated start time
     * @return true if there is an overlap, false otherwise
     * 
     * @author Philippe
     */
    private boolean eventSlotOverlap(int id, Date startDate, Date endDate, Time startTime, Time endTime) {
		
		for (EventSlot e: eventSlotRepository.findAll()) {
            // Skip the event slot that is being edited
            if (id == e.getId()) continue;

            //              |----eventSlot----|  
            //      |-------e-----|                                 
            else if (startDate.before(e.getEndDate()) && endDate.after(e.getStartDate()))
                return true;
            
            //  |------eventSlot-----|
            //                       |------e----|
            else if (endDate.equals(e.getStartDate()) && endTime.after(e.getStartTime()))
                return true;

            //  |------e-----|
            //               |------eventSlot----|
            else if (e.getEndDate().equals(startDate) && e.getEndTime().after(startTime))
                return true;
        }
		return false;
    }

    /**
     * Method modifies the attributes of a given even slot.
     * @param id        - Event slot's id
     * @param startDate - New start date
     * @param endDate   - New end date
     * @param startTime - New start time
     * @param endTime   - New end time
     * @return          - Edited event slot as a DTO
     */
    @Transactional
    public EventSlot editEventSlot(int id, Date startDate, 
    Date endDate, Time startTime, Time endTime, String description) {

        // Input Sanitization
        String error = "";
        if (!eventSlotRepository.existsById(id)) error += "Event does not exist!";
        if (description == null) error += "Description must not be empty!";

        boolean nullDate = false, nullTime = false;
        if (startDate == null) {error += "Event needs a start date!"; nullDate = true;}
        if (startTime == null) {error += "Event needs a start time!"; nullTime = true;}
        if (endDate == null) {error += "Event needs an end date!"; nullDate = true;}
        if (endTime == null) {error += "Event needs an end time!"; nullTime = true;}

        if (!nullDate && startDate.after(endDate)) error += "Start date must be before end date!";
        if (!nullDate && !nullTime && startDate.equals(endDate) && startTime.after(endTime)) 
            error += "Start time must be before end time! ";
        else if (eventSlotOverlap(id, startDate, endDate, startTime, endTime))
            error += "Event slot must not overlap with an existing event slot!";

        error = error.trim();
        if (error.length() > 0) throw new IllegalArgumentException(error);
        
        EventSlot eventSlot = eventSlotRepository.findById(id);
        eventSlot.setStartDate(startDate);
        eventSlot.setStartTime(startTime);
        eventSlot.setEndDate(endDate);
        eventSlot.setEndTime(endTime);
        eventSlot.setDescription(description);
        eventSlotRepository.save(eventSlot);
        return eventSlot;

    }

    /**
     * Service method that finds an event slot by its id
     * @param id    - The event slot's id
     * @return      - The event slot instance
     * 
     * @author Philippe
     */
    @Transactional
    public EventSlot getEventSlot(int id) {
        if (!eventSlotRepository.existsById(id)) 
            throw new IllegalArgumentException("Event cannot be found!");
        EventSlot eventSlot = eventSlotRepository.findById(id);
        return eventSlot;
    }

    /**
     * @param id
     * @return remove eventSlot
     */
    @Transactional
    public EventSlot deleteEventSlot(int id) {
        EventSlot event = getEventSlot(id);
        eventSlotRepository.deleteById(event.getId());
        return event;
    }

    /**
     * Service method that finds all the event slots
     * @return  - All the events as a list
     * 
     * @author Philippe
     */
    @Transactional
    public List<EventSlot> getAllEventSlots() {
        
        List<EventSlot> allEventSlots = new ArrayList<EventSlot>();
        for (EventSlot e : eventSlotRepository.findAll()){
            allEventSlots.add(e);
        }

        return allEventSlots;
    }

    /**
     * Service method that finds all event slots created by a client
     * @param id    - Client's id
     * @return      - List of all the events created by the client
     * 
     * @author Philippe
     */
    @Transactional
    public List<EventSlot> getEventSlotsByClientId(int id) {
        if (!clientRepository.existsById(id)) 
            throw new IllegalArgumentException("Client does not exist!");
        List<EventSlot> eventSlots = eventSlotRepository.findEventSlotByClientId(id);
        return eventSlots;
    }

    /**
     * Service method that finds all the event slots given a Date
     * @param date  - Date to be comapred with
     * @return      - List of events
     * 
     * @author Philippe
     */
    @Transactional
    public List<EventSlot> getEventSlotsByDate(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Date must not be empty!");
        }
        List<EventSlot> eventSlots = new ArrayList<EventSlot>();
        for (EventSlot event : eventSlotRepository.findAll()){
            
            if (date.equals(event.getStartDate()) || date.equals(event.getEndDate())) {
                eventSlots.add(event);
            }
            else if (date.after(event.getStartDate()) && (date.before(event.getEndDate()))) {
                eventSlots.add(event);
            }

            
        }
        return eventSlots;
    }


}
