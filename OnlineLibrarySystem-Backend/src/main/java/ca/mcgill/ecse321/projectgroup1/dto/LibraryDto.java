package ca.mcgill.ecse321.projectgroup1.dto;

/**
 * LibraryDto - Data Transfer Objects for the Library class
 * 
 * @param name        - name of the library
 * @param address     - address of the library
 * @param phoneNumber - phone number of the library
 * @param email       - email of the library
 * 
 * @author Wen Hin Brandon Wong
 */

public class LibraryDto {

    private String name;
    private String address;
    private String phoneNumber;
    private String email;

    // The constructors of the LibraryDto
    public LibraryDto(String name, String address, String phoneNumber, String email) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // The getter and setter methods of the LibraryDto
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
