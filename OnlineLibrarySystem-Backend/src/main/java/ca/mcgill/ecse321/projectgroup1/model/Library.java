package ca.mcgill.ecse321.projectgroup1.model;

// Import JPA annotation tags
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Library - This class is used to represent the library of the online library
 * system.
 * 
 * @param name        - the library's name as the primary key
 * @param address     - the library's address
 * @param phoneNumber - the library's phone number
 * @param email       - the library's email
 * 
 * @author Philippe Shi
 * @author Wen Hin Brandon Wong (Reviewer)
 */

@Entity
public class Library {

    // The name attribute of the Library class as a String that serves as
    // the primary key
    @Id
    private String name;

    // The setter method of the name attribute that takes a String value parameter
    public void setName(String name) {
        this.name = name;
    }

    // The getter method for the name attribute that returns a String
    public String getName() {
        return this.name;
    }

    // The address attribute of the Library class as a String
    private String address;

    // The setter method for the address attribute that takes a String value
    // parameter
    public void setAddress(String address) {
        this.address = address;
    }

    // The getter method for the address attribute that returns a String
    public String getAddress() {
        return this.address;
    }

    // The phone number attribute of the class Library as a String
    private String phoneNumber;

    // The setter method for the phoneNumber attribute that takes a String value
    // parameter
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // The getter method for the phoneNumber attribute that returns a String
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    // The email attribute of the Library class as a String
    private String email;

    // The setter method for the email attribute that takes a String value parameter
    public void setEmail(String email) {
        this.email = email;
    }

    // The getter method for the email attribute that returns a String
    public String getEmail() {
        return this.email;
    }
}
