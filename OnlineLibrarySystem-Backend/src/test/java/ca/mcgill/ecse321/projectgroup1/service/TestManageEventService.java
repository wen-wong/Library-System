package ca.mcgill.ecse321.projectgroup1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.projectgroup1.dao.ClientRepository;
import ca.mcgill.ecse321.projectgroup1.dao.EventSlotRepository;
import ca.mcgill.ecse321.projectgroup1.dao.LibraryRepository;
import ca.mcgill.ecse321.projectgroup1.model.Client;
import ca.mcgill.ecse321.projectgroup1.model.EventSlot;
import ca.mcgill.ecse321.projectgroup1.model.Library;


@ExtendWith(MockitoExtension.class)
public class TestManageEventService {

    @Mock
    private LibraryRepository libraryDao;
    @Mock
    private ClientRepository clientDao;
    @Mock
    private EventSlotRepository eventSlotDao;

    @InjectMocks
    private ManageLibraryService libraryService;
    @InjectMocks
    private ManageAccountService accountService;
    @InjectMocks
    private ManageEventService eventService;

    private static final int CLIENT_ID_EXISTS = 1;
    private static final int CLIENT_ID_DOES_NOT_EXIST = -1;
    private static final int EVENT_ID = 2;
    private static final int EVENT_ID_OVERLAP= 3;
    private static final int EVENT_ID_DOES_NOT_EXIST = -2;

    private static final String DESCRIPTION = "This is an event";
    private static final Date START_DATE = Date.valueOf(("2021-11-10"));
    private static final Date END_DATE = Date.valueOf(("2021-11-15"));
    private static final Time START_TIME = Time.valueOf("12:00:00");
    private static final Time END_TIME = Time.valueOf("18:00:00");

    private static final Date START_DATE_INVALID = Date.valueOf(("2021-11-15"));
    private static final Date END_DATE_INVALID = Date.valueOf(("2021-11-16"));  
    private static final Time START_TIME_INVALID = Time.valueOf("01:00:00");
    private static final Time END_TIME_INVALID = Time.valueOf("00:00:00");

    
    @BeforeEach
    public void setMockOutput() {

        // MOCK DATABASE
        
        // Library needed to create a client
        Library library = new Library();
        library.setName("library");

        // Client needed to create an event slot
        Client client = new Client();
        client.setId(CLIENT_ID_EXISTS);
        client.setLibrary(library);

        // Creating a valid event slot
        EventSlot eventSlot = new EventSlot();
        eventSlot.setClient(client);
        eventSlot.setId(EVENT_ID);
        eventSlot.setStartDate(START_DATE);
        eventSlot.setEndDate(END_DATE);
        eventSlot.setStartTime(START_TIME);
        eventSlot.setEndTime(END_TIME);
        eventSlot.setDescription(DESCRIPTION);

        // Dummy event slot that will be used to attempt the creation of invalid events slots
        EventSlot eventSlotOverlap = new EventSlot();
        eventSlotOverlap.setClient(client);
        eventSlotOverlap.setId(EVENT_ID_OVERLAP);

        // List of all the events
        List<EventSlot> allEvents = new ArrayList<EventSlot>();
        allEvents.add(eventSlot);

        // Simulating repository methods
        lenient().when(clientDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(CLIENT_ID_EXISTS)) {
                return client;
            } else {
                return null;
            }
        });

        lenient().when(clientDao.existsById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(CLIENT_ID_EXISTS)) {
                return true;
            } else {
                return false;
            }
        });

        lenient().when(eventSlotDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(EVENT_ID)) {
                return eventSlot;
            } 
            else if (invocation.getArgument(0).equals(EVENT_ID_OVERLAP)) {
                return eventSlotOverlap;
            }
            else {
                return null;
            }
        });    

        lenient().when(eventSlotDao.findEventSlotByClientId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(CLIENT_ID_EXISTS)) {
                return allEvents;
            } else {
                return null;
            }
        });

        lenient().when(eventSlotDao.existsById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(EVENT_ID) || invocation.getArgument(0).equals(EVENT_ID_OVERLAP)) {
                return true;
            } else {
                return false;
            }
        });
        
        lenient().when(eventSlotDao.findAll()).thenReturn(allEvents);

        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };

        // Return an object instance whenever it is saved
        lenient().when(libraryDao.save(any(Library.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(clientDao.save(any(Client.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(eventSlotDao.save(any(EventSlot.class))).thenAnswer(returnParameterAsAnswer);
    }

    /**
     * The test successfully creates an event slot.
     * 
     * @author Philippe
     */
    @Test
    public void testCreateEventSlot() {

        Client client = clientDao.findById(CLIENT_ID_EXISTS);
        EventSlot eventSlot = null;
        Date startDate = Date.valueOf(("2022-11-11"));
        Date endDate = Date.valueOf(("2022-11-14"));  
        Time startTime = Time.valueOf("12:00:00");
        Time endTime = Time.valueOf("18:00:00");
        String description = "Cool event!";

        try {
            eventSlot = eventService.createEventSlot(client, startDate, endDate, 
            startTime, endTime, description);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(eventSlot);
        assertEquals(startDate, eventSlot.getStartDate());
        assertEquals(endDate, eventSlot.getEndDate());
        assertEquals(startTime, eventSlot.getStartTime());
        assertEquals(endTime, eventSlot.getEndTime());
        assertEquals(description, eventSlot.getDescription());
    }

    /**
     * The test fails to create an event slot with all the attributes set to null.
     * 
     * @author Philippe
     */
    @Test
    public void testCreateEventSlotNull() {
        Client client = null;
        EventSlot eventSlot = null;
        Date startDate = null;
        Date endDate = null;
        Time startTime = null;
        Time endTime = null;
        String error = null;
        String description = null;

        try {
            eventSlot = eventService.createEventSlot(client, startDate, endDate, startTime, endTime, description);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(eventSlot);
        assertEquals(
        "Client must exist to create an event!Description must not be empty!Event needs a start date!"
        .concat("Event needs a start time!Event needs an end date!Event needs an end time!") , error);
    }

    /**
     * The test fails to create an event slot because it will overlap with an existing one.
     * 
     * @author Philippe
     */
    @Test
    public void testCreateEventSlotOverlap() {

        Client client = clientDao.findById(CLIENT_ID_EXISTS);
        String error = null;

        try {
            eventService.createEventSlot(client, START_DATE_INVALID, END_DATE_INVALID, 
            START_TIME_INVALID, END_TIME_INVALID, DESCRIPTION);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        
        assertEquals("Event slot must not overlap with an existing event slot!", error);
    }

    /**
     * The test fails to create an event slot because of the start date is set after the end date.
     * 
     * @author Philippe
     */
    @Test
    public void testCreateEventSlotInvalidDates() {
        String error = null;
        Date newStartDate = Date.valueOf("2022-11-12");
        Date newEndDate = Date.valueOf(("2022-11-11"));
        Time newStartTime = Time.valueOf("00:00:00");
        Time newEndTime = Time.valueOf("18:00:00");
        Client client = clientDao.findById(CLIENT_ID_EXISTS);

        try {
            eventService.createEventSlot(client, newStartDate, newEndDate, newStartTime, newEndTime, DESCRIPTION);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals("Start date must be before end date!",error);
    }

    /**
     * The test fails to edit an event slot to a one-day event because of start time is
     * set after the end time.
     * 
     * @author Philippe
     */
    @Test
    public void testCreateEventSlotInvalidTimes() {
        String error = null;
        Date newStartDate = Date.valueOf("2022-11-11");
        Date newEndDate = Date.valueOf(("2022-11-11"));
        Time newStartTime = Time.valueOf("18:00:00");
        Time newEndTime = Time.valueOf("00:00:00");

        try {
            eventService.editEventSlot(EVENT_ID, newStartDate, newEndDate, newStartTime, newEndTime, DESCRIPTION);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals("Start time must be before end time!",error);
    }

    /**
     * The test successfully retrieves an existing event slot by its id.
     * 
     * @author Philippe
     */
    @Test
    public void testGetEventSlotById() {
        EventSlot eventSlot = null;

        try {
            eventSlot = eventService.getEventSlot(EVENT_ID);
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        }

        assertNotNull(eventSlot);
        assertEquals(EVENT_ID, eventSlot.getId());
    }

    /**
     * The test fails to retrieve an event with an id that does not exist.
     * 
     * @author Philippe
     */
    @Test
    public void testGetEventSlotByIdDoesNotExist() {
        EventSlot eventSlot = null;
        String error = null;

        try {
            eventSlot = eventService.getEventSlot(EVENT_ID_DOES_NOT_EXIST);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(eventSlot);
        assertEquals("Event cannot be found!", error);
    }

    /**
     * The test successfully returns a list of all the existing events.
     * 
     * @author Philippe
     */
    @Test
    public void testGetAllEvents() {
        List<EventSlot> allEvents = null;
        
        try {
            allEvents = eventService.getAllEventSlots();
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertEquals(1, allEvents.size());
    }

    /**
     * The test successfully retrieves a list of all the event slots created by a client.
     * 
     * @author Philippe
     */
    @Test
    public void testGetEventSlotsByClientId() {
        List<EventSlot> events = null;

        try {
            events = eventService.getEventSlotsByClientId(CLIENT_ID_EXISTS);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertEquals(1, events.size());
    }

    /**
     * The test fails to retrieve a list of event slots of 
     * a non-existing client.
     * 
     * @autor Philippe
     */
    @Test
    public void testGetEventSlotsByClientIdFail() {
        List<EventSlot> events = null;
        String error = null;
        try {
            events = eventService.getEventSlotsByClientId(CLIENT_ID_DOES_NOT_EXIST);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        
        assertNull(events);
        assertEquals("Client does not exist!", error);
    }

    /**
     * The test successfully modifies the attributes of an event slot.
     * 
     * @author Philippe
     */
    @Test
    public void testEditEventSlot() {
        EventSlot event = null;
        Date newStartDate = Date.valueOf("2021-11-11");
        Date newEndDate = Date.valueOf(("2021-11-12"));
        Time newStartTime = Time.valueOf("00:00:00");
        Time newEndTime = Time.valueOf("18:00:00");
        String newDescription = "This is a new description";

        try {
            eventService.editEventSlot(EVENT_ID, newStartDate, newEndDate, newStartTime, newEndTime, newDescription);
           
        } catch (IllegalArgumentException e) {
            fail();
        }
        event = eventService.getEventSlot(EVENT_ID);
        assertEquals(newStartDate, event.getStartDate());
        assertEquals(newEndDate, event.getEndDate());
        assertEquals(newStartTime, event.getStartTime());
        assertEquals(newEndTime, event.getEndTime());
        assertEquals(newDescription, event.getDescription());
    }

    /**
     * The test fails to edit an event slot with new attributes that are set to null.
     *
     * @author Philippe
     */
    @Test
    public void testEditEventSlotNull() {
        Date startDate = null;
        Date endDate = null;
        Time startTime = null;
        Time endTime = null;
        String description = null;
        String error = null;
        try {
            eventService.editEventSlot(EVENT_ID, startDate, endDate, startTime, endTime, description);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals(
        "Description must not be empty!Event needs a start date!Event needs a start time!"
        .concat("Event needs an end date!Event needs an end time!"), error);
    }

     /**
     * The test fails to edit an event slot because it will overlaps with another existing one.
     * 
     * @author Philippe
     */
    @Test
    public void testeditEventSlotOverlap() {

        Client client = clientDao.findById(CLIENT_ID_EXISTS);
        EventSlot eventSlot = new EventSlot();
        
        eventSlot.setClient(client);
        eventSlot.setStartDate(START_DATE_INVALID);
        eventSlot.setEndDate(END_DATE_INVALID);
        eventSlot.setStartTime(START_TIME_INVALID);
        eventSlot.setEndTime(END_TIME_INVALID);
        String error = null;

        try {
            eventService.editEventSlot(EVENT_ID_OVERLAP, START_DATE_INVALID, END_DATE_INVALID, 
            START_TIME_INVALID, END_TIME_INVALID, DESCRIPTION);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        
        assertEquals("Event slot must not overlap with an existing event slot!", error);
    }
    /**
     * The test fails to edit an event slot because of a new start date set after the new end date.
     * 
     * @author Philippe
     */
    @Test
    public void testEditEventSlotInvalidDates() {
        String error = null;
        Date newStartDate = Date.valueOf("2021-11-12");
        Date newEndDate = Date.valueOf(("2021-11-11"));
        Time newStartTime = Time.valueOf("00:00:00");
        Time newEndTime = Time.valueOf("18:00:00");

        try {
            eventService.editEventSlot(EVENT_ID, newStartDate, newEndDate, newStartTime, newEndTime, DESCRIPTION);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals("Start date must be before end date!",error);
    }

    /**
     * The test fails to edit an event slot to a one-day event because of new start time 
     * set after the new end time.
     * 
     * @author Philippe
     */
    @Test
    public void testEditEventSlotInvalidTimes() {
        String error = null;
        Date newStartDate = Date.valueOf("2021-11-11");
        Date newEndDate = Date.valueOf(("2021-11-11"));
        Time newStartTime = Time.valueOf("18:00:00");
        Time newEndTime = Time.valueOf("00:00:00");

        try {
            eventService.editEventSlot(EVENT_ID, newStartDate, newEndDate, newStartTime, newEndTime, DESCRIPTION);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals("Start time must be before end time!",error);
    }

    /**
     * The test successfully gets all events that are within a given date
     * 
     * @autor Philippe
     */
    @Test
    public void testGetEventSlotsByDate() {
        Date date = Date.valueOf("2021-11-14");
        List<EventSlot> events = null;
        try {
            events = eventService.getEventSlotsByDate(date);
        } catch (IllegalArgumentException e) {
            fail();
        }
        assertEquals(1, events.size());
    }

    /**
     * The test fails gets all events that are within a given null date
     * 
     * @autor Philippe
     */
    @Test
    public void testGetEventSlotsByDateNull() {
        Date date = null;
        String error = null;
        try {
            eventService.getEventSlotsByDate(date);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals("Date must not be empty!", error);
    }
}
