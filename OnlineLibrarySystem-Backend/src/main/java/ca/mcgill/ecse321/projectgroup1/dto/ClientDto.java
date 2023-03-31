package ca.mcgill.ecse321.projectgroup1.dto;

/**
 * ClientDto - Data Transfer Objects for the Client class that extends AccountDto
 *
 * @param library       - library that has this account
 * @param id            - the account id
 * @param name          - the account name
 * @param password      - the account's password
 * @param address       - the account's address
 * @param phoneNumber   - the account's phone number
 * @param email         - the account's email
 * @param isResident    - verifies if the client is a resident of the town
 * @param numOfFlags    - the number of warning flags of the client

 * @author Philippe
 */

public class ClientDto extends AccountDto {

    private boolean isResident;
    private int numOfFlags;
    

    // The contructors of the ClientDto

    // The attribute numOfFlags is initialized to 0 when a client account is created
    public ClientDto(LibraryDto library, int id, String name, String password, 
    String address, String phoneNumber, String email, boolean isResident) {

        super(library, id, name, password, address, phoneNumber, email);
        this.isResident = isResident;
        this.numOfFlags = 0;
    }

    public ClientDto(LibraryDto library, int id, String name, String password, 
    String address, String phoneNumber, String email, boolean isResident, int numOfFlags) {

        super(library, id, name, password, address, phoneNumber, email);
        this.isResident = isResident;
        this.numOfFlags = numOfFlags;
    }


    public boolean getIsResident() {
        return this.isResident;
    }

    public int getNumOfFlags() {
        return this.numOfFlags;
    }
    
}
