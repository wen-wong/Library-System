package ca.mcgill.ecse321.projectgroup1.dto;

/**
 * AccountDto - Data Transfer Objects for the AccountDto class
 * 
 * @param library           - library that has this account
 * @param id                - the account id
 * @param name              - the account name
 * @param password          - the account's password
 * @param address           - the account's address
 * @param phoneNumber       - the account's phone number
 * @param email             - the account's email
 * 
 * @author Philippe
 */

public abstract class AccountDto {

    private LibraryDto library;
    private int id;
    private String name;
    private String password;
    private String address;
    private String phoneNumber;
    private String email;


    // The constructor for the AccountDto
    public AccountDto(LibraryDto library, int id, String name, String password,
    String address, String phoneNumber, String email) {

        this.library = library;
        this.id = id;
        this.name = name;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // The getter and setter methods of the AccountDto
    
    public LibraryDto getLibrary() {
        return this.library;
    }

    public void setLibrary(LibraryDto library) {
        this.library = library;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

}
