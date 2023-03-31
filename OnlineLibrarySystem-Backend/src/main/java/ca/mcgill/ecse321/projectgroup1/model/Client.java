package ca.mcgill.ecse321.projectgroup1.model;

// Import JPA annotation tags
import javax.persistence.Entity;

/**
 * Client - This class serves as a client's account in the online library
 * system. It inherits all the attributes of the Account abstract class that
 * have access to all items of the library as LibraryItem instances and the
 * events as TimeSlot instances.
 * 
 * @param isResident - verifies if the client is a resident of the town
 * @param numOfFlags - the number of warning flags of the client
 * 
 * @author Emilia Solaberrieta
 * @author Wen Hin Brandon Wong (Reviewer)
 */

@Entity
public class Client extends Account {

    // The isResident attribute of the Client class as a Boolean
    private Boolean isResident;

    // The setter method for the isResident attribute that takes a Boolean as a
    // parameter
    public void setIsResident(Boolean verify) {
        this.isResident = verify;
    }

    // The getter method for the isResident attribute that returns a Boolean
    public Boolean getIsResident() {
        return this.isResident;
    }

    // The numOfFlags attribute of the Client class as an integer
    private int numOfFlags;

    // The setter method for the numOfFlags attribute that takes an integer as a
    // parameter
    public void setNumOfFlags(int num) {
        this.numOfFlags = num;
    }

    // The getter method for the numOfFlags attribute that returns an integer
    public int getNumOfFlag() {
        return this.numOfFlags;
    }
}
