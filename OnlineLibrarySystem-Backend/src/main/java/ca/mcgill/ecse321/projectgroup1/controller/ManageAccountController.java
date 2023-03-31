package ca.mcgill.ecse321.projectgroup1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup1.dto.ClientDto;
import ca.mcgill.ecse321.projectgroup1.dto.EmployeeDto;
import ca.mcgill.ecse321.projectgroup1.model.Client;
import ca.mcgill.ecse321.projectgroup1.model.Employee;
import ca.mcgill.ecse321.projectgroup1.model.Library;
import ca.mcgill.ecse321.projectgroup1.service.ManageAccountService;
import ca.mcgill.ecse321.projectgroup1.service.ViewLibraryService;

@CrossOrigin(origins = "*")
@RestController
public class ManageAccountController {

    @Autowired
    private ManageAccountService accService;

    @Autowired
    private ViewLibraryService viewLibService;

    /**
     * Controller method to create a client account
     * 
     * @return - Client Dto
     * 
     * @author Emilia
     */
    @PostMapping(value = { "/accounts/clients/", "/accounts/clients" })
    public ClientDto createClient(@RequestParam(name = "name") String name,
            @RequestParam(name = "address") String address, @RequestParam(name = "libraryName") String libraryName,
            @RequestParam(name = "password") String password, @RequestParam(name = "phoneNumber") String phoneNumber,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "isResidentString") String isResidentString) throws IllegalArgumentException {

        Library library = viewLibService.getLibrary(libraryName);

        Boolean isResident;
        if (isResidentString.replace(" ", "").equalsIgnoreCase("True")) {
            isResident = Boolean.TRUE;
        } else if (isResidentString.replace(" ", "").equalsIgnoreCase("False")) {
            isResident = Boolean.FALSE;
        } else {
            throw new IllegalArgumentException(
                    "isResident has to be string 'True' or 'False', if null assumed to be false");
        }

        Client client = accService.createClient(library, name, password, address, phoneNumber, email, isResident);
        return ControllerConvert.convertToDto(client);
    }

    /**
     * Controller method to create an employee account
     * 
     * @return - Employee Dto
     * 
     * @author Emilia
     */
    @PostMapping(value = { "/accounts/employees", "/accounts/employees/" })
    public EmployeeDto createEmployee(@RequestParam(name = "name") String name,
            @RequestParam(name = "address") String address, @RequestParam(name = "libraryName") String libraryName,
            @RequestParam(name = "password") String password, @RequestParam(name = "phoneNumber") String phoneNumber,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "typeOfEmployeeString") String employeeTypeStr) throws IllegalArgumentException {

        Library library = viewLibService.getLibrary(libraryName);

        Employee.TypeOfEmployee typeOfEmployee = Employee.TypeOfEmployee.Librarian;
        if (employeeTypeStr.replace(" ", "").equalsIgnoreCase("HeadLibrarian")) {
            typeOfEmployee = Employee.TypeOfEmployee.HeadLibrarian;
        } else if (employeeTypeStr.replace(" ", "").equalsIgnoreCase("Librarian")) {
            typeOfEmployee = Employee.TypeOfEmployee.Librarian;
        } else {
            throw new IllegalArgumentException("typeOfEmployee has to be 'HeadLibrarian' or 'Librarian'");
        }

        Employee employee = accService.createEmployee(library, name, password, address, phoneNumber, email,
                typeOfEmployee);
        return ControllerConvert.convertToDto(employee);
    }

    /**
     * Controller method to get a client account by id
     * 
     * @return - Client Dto
     * 
     * @author Emilia
     */
    @GetMapping(value = { "/accounts/clients/{id}", "/accounts/clients/{id}/" })
    public ClientDto getClient(@PathVariable("id") int idString) throws IllegalArgumentException {

        int id = Integer.valueOf(idString);
        return ControllerConvert.convertToDto(accService.getClient(id));
    }

    /**
     * Controller method to get an employee account by id
     * 
     * @return - Employee Dto
     * 
     * @author Emilia
     */
    @GetMapping(value = { "/accounts/employees/{id}", "/accounts/employees/{id}/" })
    public EmployeeDto getEmployee(@PathVariable("id") int idString) throws IllegalArgumentException {

        int id = Integer.valueOf(idString);
        return ControllerConvert.convertToDto(accService.getEmployee(id));
    }

    /**
     * Controller method to edit a client account by id
     * 
     * @return - Client Dto
     * 
     * @author Emilia
     */
    @PutMapping(value = { "/accounts/clients/{id}", "/accounts/clients/{id}/" }) // is the url part for edit good?
    public ClientDto editClient(@PathVariable("id") int id, @RequestParam(name = "name") String name,
            @RequestParam(name = "address") String address, @RequestParam(name = "libraryName") String libraryName,
            @RequestParam(name = "password") String password, @RequestParam(name = "phoneNumber") String phoneNumber,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "numOfFlagsString") String numOfFlagsString,
            @RequestParam(name = "isResidentString") String isResidentString) throws IllegalArgumentException {

        Library library = viewLibService.getLibrary(libraryName);

        Boolean isResident;
        if (isResidentString.replace(" ", "").equalsIgnoreCase("True")) {
            isResident = Boolean.TRUE;
        } else if (isResidentString.replace(" ", "").equalsIgnoreCase("False")) {
            isResident = Boolean.FALSE;
        } else {
            throw new IllegalArgumentException(
                    "isResident has to be string 'True' or 'False', if null assumed to be false");
        }

        Client currClient = accService.getClient(id);

        int numOfFlags = currClient.getNumOfFlag();
        if (numOfFlagsString.replace(" ", "").equalsIgnoreCase("0")) {
            numOfFlags = 0;
        } else if (numOfFlagsString.replace(" ", "").equalsIgnoreCase("1")) {
            numOfFlags = 1;
        } else if (numOfFlagsString.replace(" ", "").equalsIgnoreCase("2")) {
            numOfFlags = 2;
        } else if (numOfFlagsString.replace(" ", "").equalsIgnoreCase("3")) {
            numOfFlags = 3;
        } else if (numOfFlagsString.replace(" ", "").equalsIgnoreCase("")) {
            numOfFlags = currClient.getNumOfFlag();
        } else {
            throw new IllegalArgumentException("numOfFlags has to be '0', '1', '2' or '3'");
        }

        // int and bool cannot be null so on frontend current values will be displayed

        Client client = accService.editClient(library, id, name, password, address, phoneNumber, email, numOfFlags,
                isResident);
        return ControllerConvert.convertToDto(client);

    }

    /**
     * Controller method to edit an employee account by id
     * 
     * @return - Employee Dto
     * 
     * @author Emilia
     */
    @PutMapping(value = { "/accounts/employees/{id}", "/accounts/employees/{id}/" }) // is the url part for edit good?
    public EmployeeDto editEmployee(@PathVariable("id") int id, @RequestParam(name = "name") String name,
            @RequestParam(name = "address") String address, @RequestParam(name = "libraryName") String libraryName,
            @RequestParam(name = "password") String password, @RequestParam(name = "phoneNumber") String phoneNumber,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "typeOfEmployeeString") String employeeTypeStr) throws IllegalArgumentException {

        Library library = viewLibService.getLibrary(libraryName);

        Employee.TypeOfEmployee typeOfEmployee = Employee.TypeOfEmployee.Librarian;

        Employee currEmployee = accService.getEmployee(id);

        if (employeeTypeStr.replace(" ", "").equalsIgnoreCase("HeadLibrarian")) {
            typeOfEmployee = Employee.TypeOfEmployee.HeadLibrarian;
        } else if (employeeTypeStr.replace(" ", "").equalsIgnoreCase("Librarian")) {
            typeOfEmployee = Employee.TypeOfEmployee.Librarian;
        } else if (employeeTypeStr.replace(" ", "").equalsIgnoreCase("")) {
            typeOfEmployee = currEmployee.getTypeOfEmployee();
        } else {
            throw new IllegalArgumentException("typeOfEmployee has to be 'HeadLibrarian' or 'Librarian'");
        }

        // if fields are null set to previous value linked with the client

        Employee employee = accService.editEmployee(library, id, name, password, address, phoneNumber, email,
                typeOfEmployee);
        return ControllerConvert.convertToDto(employee);
    }

    /**
     * Controller method to login to a client account
     * 
     * @param id - the id of the client account
     * @param password - the password of the client account
     * @return - Client Dto
     * 
     * @author Emilia
     */
    @PostMapping(value = { "/accounts/clients/login/", "/accounts/clients/login" })
    public ClientDto logInClient(@RequestParam(name = "id") int id,
            @RequestParam(name = "password") String password
            ) throws IllegalArgumentException {        

        Client client = accService.logInClient(id, password);
        return ControllerConvert.convertToDto(client);
    }

    /**
     * Controller method to login to an employee account
     * 
     * @param id - the id of the employee account
     * @param password - the password of the employee account
     * @return - Employee Dto
     * 
     * @author Emilia
     */
    @PostMapping(value = { "/accounts/employees/login/", "/accounts/employees/login" })
    public EmployeeDto logInEmployee(@RequestParam(name = "id") int id,
            @RequestParam(name = "password") String password
            ) throws IllegalArgumentException {        

        Employee employee = accService.logInEmployee(id, password);
        return ControllerConvert.convertToDto(employee);
    }

}
