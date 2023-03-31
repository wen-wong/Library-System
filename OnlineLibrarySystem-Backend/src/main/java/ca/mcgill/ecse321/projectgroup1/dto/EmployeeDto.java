package ca.mcgill.ecse321.projectgroup1.dto;

/**
 * EmployeeDto - Data Transfer Objects for the Employee class that extends
 * AccountDto
 * 
 * @param library        - library that has this account
 * @param id             - the account id
 * @param name           - the account name
 * @param password       - the account's password
 * @param address        - the account's address
 * @param phoneNumber    - the account's phone number
 * @param email          - the account's email
 * @param typeOfEmployee - the employee's role
 * 
 * @author Philippe
 */

public class EmployeeDto extends AccountDto {

    private TypeOfEmployee typeOfEmployee;

    // The enumeration class of TypeOfEmployee
    public enum TypeOfEmployee {
        Librarian, HeadLibrarian
    }

    // The constructor for the EmployeeDto
    public EmployeeDto(LibraryDto library, int id, String name, String password, String address, String phoneNumber,
            String email, TypeOfEmployee typeOfEmployee) {

        super(library, id, name, password, address, phoneNumber, email);
        this.typeOfEmployee = typeOfEmployee;
    }

    // The getter and setter methods of the EmployeeDto
    public TypeOfEmployee getTypeOfEmployee() {
        return this.typeOfEmployee;
    }

}
