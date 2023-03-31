package ca.mcgill.ecse321.projectgroup1.model;

// Import JPA annotation tags
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

/**
 * Account - This class serves as an user's account in the online library
 * system. This is an abstract class that represents the generalization of
 * Client and Employee (the subclasses).
 * 
 * @param id          - the account id as the primary key
 * @param name        - the account name
 * @param password    - the account's password
 * @param address     - the account's address
 * @param phoneNumber - the account's phone number
 * @param email       - the account's email
 * 
 * @author Wen Hin Brandon Wong
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {

    /**
     * Unidirectional association between the Account and Library class with a
     * multiplicity of many to one with its getter and setter methods.
     */
    @ManyToOne(optional = false)
    private Library library;

    public Library getLibrary() {
        return this.library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    // The id attribute of the Account class as an integer that serves as the
    // primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // The setter method for the id attribute that takes an integer value parameter
    public void setId(int id) {
        this.id = id;
    }

    // The getter method for the id attribute that returns an integer
    public int getId() {
        return this.id;
    }

    // The name attribute of the Account class as a String
    private String name;

    // The setter method for the name attribute that takes a String as a parameter
    public void setName(String name) {
        this.name = name;
    }

    // The getter method for the name attribute that returns a String
    public String getName() {
        return this.name;
    }

    // The password attribute of the Account class as a String
    private String password;

    // The setter method for the password attribute that takes a String as a
    // parameter
    public void setPassword(String password) {
        this.password = password;
    }

    // The getter method for the password attribute that returns a String
    public String getPassword() {
        return this.password;
    }

    // The address attribute for the Account class as a String
    private String address;

    // The setter method for the address attribute that takes a String parameter
    public void setAddress(String address) {
        this.address = address;
    }

    // The getter method for the address attribute that returns a String
    public String getAddress() {
        return this.address;
    }

    // The phoneNumber attribute for the Account class as a String
    private String phoneNumber;

    // The setter method for the phoneNumber attribute that takes a String
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // The getter method for the phoneNumber attribute that returns a String
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    // The email attribute for the Account class as a String
    private String email;

    // The setter method for the email attribute that takes a String parameter
    public void setEmail(String email) {
        this.email = email;
    }

    // The getter method for the email attribute that returns a String
    public String getEmail() {
        return this.email;
    }
}
