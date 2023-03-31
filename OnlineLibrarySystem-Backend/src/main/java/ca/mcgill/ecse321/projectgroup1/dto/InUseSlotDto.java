package ca.mcgill.ecse321.projectgroup1.dto;

// Import SQL packages
import java.sql.Date;

/**
 * InUseSlotDto - Data Transfer Object for the EventSlot class
 * 
 * @param id          - the in use slot's id
 * @param startDate   - the in use slot's start date
 * @param endDate     - the in use slot's end date
 * @param statusDto   - the in use slot's status
 * @param client      - the client which is holder of this in use slot
 * @param libraryItem - the library item which has this in use slot
 * 
 * @author Anika Kabir
 */

public class InUseSlotDto {

    // The enumeration class of Status
    public enum StatusDto {
        Booked, Reserved, InCart
    }

    // Attributes
    private int id;
    private ClientDto client;
    private LibraryItemDto libraryItem;
    private Date startDate;
    private Date endDate;
    private StatusDto statusDto;

    // Constructor for InUseSlotDto

    public InUseSlotDto(LibraryItemDto libraryItem, ClientDto client, int id, Date startDate, Date endDate,
            StatusDto statusDto) {
        this.libraryItem = libraryItem;
        this.client = client;
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.statusDto = statusDto;
    }

    // Getter and setter methods for associations

    public LibraryItemDto getLibraryItem() {
        return this.libraryItem;
    }

    public void setLibraryItem(LibraryItemDto libraryItem) {
        this.libraryItem = libraryItem;
    }

    public ClientDto getClient() {
        return this.client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    // Getter methods
    public int getId() {
        return this.id;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public StatusDto getStatus() {
        return this.statusDto;
    }

}
