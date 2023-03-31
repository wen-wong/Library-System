package ca.mcgill.ecse321.projectgroup1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.projectgroup1.dao.LibraryRepository;
import ca.mcgill.ecse321.projectgroup1.dao.ClientRepository;
import ca.mcgill.ecse321.projectgroup1.dao.EmployeeRepository;
import ca.mcgill.ecse321.projectgroup1.model.Client;
import ca.mcgill.ecse321.projectgroup1.model.Employee;
import ca.mcgill.ecse321.projectgroup1.model.Library;

/**
 * TestManageAccountService - All tests that cover the use case of managing the
 * accounts (client, employees).
 * 
 * @author Brandon
 */
@ExtendWith(MockitoExtension.class)
public class TestManageAccountService {

	@Mock
	private LibraryRepository libraryDao;
    @Mock
    private ClientRepository clientDao;
    @Mock
    private EmployeeRepository employeeDao;

    @InjectMocks
    private ManageAccountService accService;


	private static final String LIBRARY_KEY = "TestLibrary";
	private static final String LIBRARY_ADDRESS = "Library Street";
	private static final String LIBRARY_PHONENUMBER = "514-123-1234";
	private static final String LIBRARY_EMAIL = "library@mail.ca";
    private static final int CLIENT_ID = 1233;
	private static final String CLIENT_NAME = "Roman";
	private static final String CLIENT_PASSWORD = "passy";
	private static final String CLIENT_ADDRESS = "555 roman str";
	private static final String CLIENT_PHONENUMBER = "1112223333";
	private static final String CLIENT_EMAIL = "roman@mail.ca";
	private static final Boolean CLIENT_ISRESIDENT = Boolean.FALSE;
	private static final int CLIENT_NUMOFFLAGS = 0;
    private static final int EMPLOYEE_ID = 123456;
	private static final String EMPLOYEE_NAME = "John";
	private static final String EMPLOYEE_PASSWORD = "passywordy";
	private static final String EMPLOYEE_ADDRESS = "78 smith str";
	private static final String EMPLOYEE_PHONENUMBER = "9118743435";
	private static final String EMPLOYEE_EMAIL = "john@mail.ca";
	private static final Employee.TypeOfEmployee EMPLOYEE_TYPE = Employee.TypeOfEmployee.Librarian;


    @BeforeEach
    public void setMockOutput() {

		lenient().when(libraryDao.findByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(LIBRARY_KEY)) {
				Library library = new Library();
				library.setName(LIBRARY_KEY);
				library.setAddress(LIBRARY_ADDRESS);
				library.setPhoneNumber(LIBRARY_PHONENUMBER);
				library.setEmail(LIBRARY_EMAIL);
				return library;
			} else {
				return null;
			}
		});

		lenient().when(libraryDao.existsByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(LIBRARY_KEY)) {
				return Boolean.TRUE;
			} else {
				return Boolean.FALSE;
			}
		});

        lenient().when(clientDao.findById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(CLIENT_ID)) {
                Client client = new Client();
				client.setLibrary(libraryDao.findByName(LIBRARY_KEY));
                client.setId(CLIENT_ID);
				client.setName(CLIENT_NAME);
				client.setPassword(CLIENT_PASSWORD);
				client.setAddress(CLIENT_ADDRESS);
				client.setPhoneNumber(CLIENT_PHONENUMBER);
				client.setEmail(CLIENT_EMAIL);
				client.setIsResident(CLIENT_ISRESIDENT);
				client.setNumOfFlags(CLIENT_NUMOFFLAGS);
                return client;
            } else {
                return null;
            }
        });

		lenient().when(clientDao.existsById(CLIENT_ID)).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(CLIENT_ID)) {
				return Boolean.TRUE;
			} else {
				return Boolean.FALSE;
			}
		});

        lenient().when(employeeDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(EMPLOYEE_ID)) {
				Employee employee = new Employee();
				employee.setId(EMPLOYEE_ID);
				employee.setLibrary(libraryDao.findByName(LIBRARY_KEY));
                employee.setId(EMPLOYEE_ID);
				employee.setName(EMPLOYEE_NAME);
				employee.setPassword(EMPLOYEE_PASSWORD);
				employee.setAddress(EMPLOYEE_ADDRESS);
				employee.setPhoneNumber(EMPLOYEE_PHONENUMBER);
				employee.setEmail(EMPLOYEE_EMAIL);
				employee.setTypeOfEmployee(EMPLOYEE_TYPE);
				return employee;
			} else {
				return null;
			}
		});

		lenient().when(employeeDao.existsById(EMPLOYEE_ID)).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(EMPLOYEE_ID)) {
				return Boolean.TRUE;
			} else {
				return Boolean.FALSE;
			}
		});

        // Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};

		lenient().when(libraryDao.save(any(Library.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(clientDao.save(any(Client.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(employeeDao.save(any(Employee.class))).thenAnswer(returnParameterAsAnswer);
    }

	//Create Client mockito tests start here
    
	//create client with valid inputs
	@Test
		public void testCreateClient() {

		//create library that client needs to be created	
		Library library = libraryDao.findByName(LIBRARY_KEY); //DO THIS INSTEAD?

		//params for my new client
		String name = "Roman";
        String password = "passy";
        String address = "555 roman str";
        String phoneNumber = "1112223333";
        String email = "roman@mail.ca";
        Boolean isResident = Boolean.FALSE;
		Client client = null;

		try {
			client = accService.createClient(library, name, password, address, phoneNumber, email, isResident);
		} catch (IllegalArgumentException error) {
			fail();
		}
        
		assertNotNull(client);
		assertEquals(name, client.getName());
		assertEquals(password, client.getPassword());
		assertEquals(address, client.getAddress());
		assertEquals(phoneNumber, client.getPhoneNumber());
		assertEquals(email, client.getEmail());
		assertEquals(isResident, client.getIsResident());
	}

	//create client with null library - throws error
	@Test
	public void testCreateClientLibraryNull() {

		//library attribute is null
		Library library = null;
		String name = "Roman";
        String password = "passy";
        String address = "555 roman str";
        String phoneNumber = "1112223333";
        String email = "roman@mail.ca";
        Boolean isResident = Boolean.FALSE;
		String error = null;
		Client client = null;

		try {
			client = accService.createClient(library, name, password, address, phoneNumber, email, isResident);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(client);
		// check error
		assertEquals("Library must be selected to create a client account!", error);
	}

	//create client with nonexistent library - throws error
	@Test
	public void testCreateClientLibraryNotExist() {

		//create valid library		
		Library libraryNot = libraryDao.findByName(LIBRARY_KEY);

		//change name to non-existing library
		libraryNot.setName("fake lib");

		//client attribute name null
		String name = "Roman";
        String password = "passy";
        String address = "555 roman str";
        String phoneNumber = "1112223333";
        String email = "roman@mail.ca";
        Boolean isResident = Boolean.FALSE;
		String error = null;
		Client client = null;

		try {
			client = accService.createClient(libraryNot, name, password, address, phoneNumber, email, isResident);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(client);
		// check error
		assertEquals("library does not exist", error);
	}

	//create client with null name - throws error
	@Test
	public void testCreateClientNameNull() {

		//create library that client needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//client attribute name null
		String name = null;
        String password = "passy";
        String address = "555 roman str";
        String phoneNumber = "1112223333";
        String email = "roman@mail.ca";
        Boolean isResident = Boolean.FALSE;
		String error = null;
		Client client = null;

		try {
			client = accService.createClient(library, name, password, address, phoneNumber, email, isResident);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(client);
		// check error
		assertEquals("client name cannot be empty!", error);
	}

	//create client with empty name - throws error
	@Test
	public void testCreateClientNameEmpty() {

		//create library that client needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//client attribute name empty
		String name = "";
        String password = "passy";
        String address = "555 roman str";
        String phoneNumber = "1112223333";
        String email = "roman@mail.ca";
        Boolean isResident = Boolean.FALSE;
		String error = null;
		Client client = null;

		try {
			client = accService.createClient(library, name, password, address, phoneNumber, email, isResident);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(client);
		// check error
		assertEquals("client name cannot be empty!", error);
	}

	//create client with space for name - throws error
	@Test
	public void testCreateClientNameSpaces() {

		//create library that client needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//client attribute name null
		String name = " ";
        String password = "passy";
        String address = "555 roman str";
        String phoneNumber = "1112223333";
        String email = "roman@mail.ca";
        Boolean isResident = Boolean.FALSE;
		String error = null;
		Client client = null;

		try {
			client = accService.createClient(library, name, password, address, phoneNumber, email, isResident);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(client);
		// check error
		assertEquals("client name cannot be empty!", error);
	}

	//create client with null password - throws error
	@Test
	public void testCreateClientPasswordNull() {

		//create library that client needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//client attribute name null
		String name = "Roman";
        String password = null;
        String address = "555 roman str";
        String phoneNumber = "1112223333";
        String email = "roman@mail.ca";
        Boolean isResident = Boolean.FALSE;
		String error = null;
		Client client = null;

		try {
			client = accService.createClient(library, name, password, address, phoneNumber, email, isResident);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(client);
		// check error
		assertEquals("Password cannot be empty!", error);
	}

	//create client with empty password - throws error
	@Test
	public void testCreateClientPasswordEmpty() {

		//create library that client needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//client attribute name null
		String name = "Roman";
        String password = "";
        String address = "555 roman str";
        String phoneNumber = "1112223333";
        String email = "roman@mail.ca";
        Boolean isResident = Boolean.FALSE;
		String error = null;
		Client client = null;

		try {
			client = accService.createClient(library, name, password, address, phoneNumber, email, isResident);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(client);
		// check error
		assertEquals("Password cannot be empty!", error);
	}

	//create client with space for password - throws error
	@Test
	public void testCreateClientPasswordSpaces() {

		//create library that client needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//client attribute name null
		String name = "Roman";
        String password = " ";
        String address = "555 roman str";
        String phoneNumber = "1112223333";
        String email = "roman@mail.ca";
        Boolean isResident = Boolean.FALSE;
		String error = null;
		Client client = null;

		try {
			client = accService.createClient(library, name, password, address, phoneNumber, email, isResident);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(client);
		// check error
		assertEquals("Password cannot be empty!", error);
	}

	//create client with null address - throws error
	@Test
	public void testCreateClientAddressNull() {

		//create library that client needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//client attribute name null
		String name = "Roman";
        String password = "passy";
        String address = null;
        String phoneNumber = "1112223333";
        String email = "roman@mail.ca";
        Boolean isResident = Boolean.FALSE;
		String error = null;
		Client client = null;

		try {
			client = accService.createClient(library, name, password, address, phoneNumber, email, isResident);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(client);
		// check error
		assertEquals("Address cannot be empty!", error);
	}

	//create client with empty address - throws error
	@Test
	public void testCreateClientAddressEmpty() {

		//create library that client needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//client attribute name null
		String name = "Roman";
        String password = "passy";
        String address = "";
        String phoneNumber = "1112223333";
        String email = "roman@mail.ca";
        Boolean isResident = Boolean.FALSE;
		String error = null;
		Client client = null;

		try {
			client = accService.createClient(library, name, password, address, phoneNumber, email, isResident);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(client);
		// check error
		assertEquals("Address cannot be empty!", error);
	}

	//create client with space for address - throws error
	@Test
	public void testCreateClientAddressSpaces() {

		//create library that client needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//client attribute name null
		String name = "Roman";
        String password = "passy";
        String address = " ";
        String phoneNumber = "1112223333";
        String email = "roman@mail.ca";
        Boolean isResident = Boolean.FALSE;
		String error = null;
		Client client = null;

		try {
			client = accService.createClient(library, name, password, address, phoneNumber, email, isResident);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(client);
		// check error
		assertEquals("Address cannot be empty!", error);
	}

	//create client with null phonenumber - throws error
	@Test
	public void testCreateClientPhoneNumberNull() {

		//create library that client needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//client attribute name null
		String name = "Roman";
        String password = "passy";
        String address = "555 roman str";
        String phoneNumber = null;
        String email = "roman@mail.ca";
        Boolean isResident = Boolean.FALSE;
		String error = null;
		Client client = null;

		try {
			client = accService.createClient(library, name, password, address, phoneNumber, email, isResident);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(client);
		// check error
		assertEquals("Phonenumber cannot be empty!", error);
	}

	//create client with empty phonenumber - throws error
	@Test
	public void testCreateClientPhoneNumberEmpty() {

		//create library that client needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//client attribute name null
		String name = "Roman";
        String password = "passy";
        String address = "555 roman str";
        String phoneNumber = "";
        String email = "roman@mail.ca";
        Boolean isResident = Boolean.FALSE;
		String error = null;
		Client client = null;

		try {
			client = accService.createClient(library, name, password, address, phoneNumber, email, isResident);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(client);
		// check error
		assertEquals("Phonenumber cannot be empty!", error);
	}

	//create client with space for phonenumber - throws error
	@Test
	public void testCreateClientPhoneNumberSpaces() {

		//create library that client needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//client attribute name null
		String name = "Roman";
        String password = "passy";
        String address = "555 roman str";
        String phoneNumber = " ";
        String email = "roman@mail.ca";
        Boolean isResident = Boolean.FALSE;
		String error = null;
		Client client = null;

		try {
			client = accService.createClient(library, name, password, address, phoneNumber, email, isResident);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(client);
		// check error
		assertEquals("Phonenumber cannot be empty!", error);
	}

	//create client with null email - throws error
	@Test
	public void testCreateClientEmailNull() {

		//create library that client needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//client attribute name null
		String name = "Roman";
        String password = "passy";
        String address = "555 roman str";
        String phoneNumber = "1112223333";
        String email = null;
        Boolean isResident = Boolean.FALSE;
		String error = null;
		Client client = null;

		try {
			client = accService.createClient(library, name, password, address, phoneNumber, email, isResident);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(client);
		// check error
		assertEquals("Email cannot be empty!", error);
	}

	//create client with empty email - throws error
	@Test
	public void testCreateClientEmailEmpty() {

		//create library that client needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//client attribute name null
		String name = "Roman";
        String password = "passy";
        String address = "555 roman str";
        String phoneNumber = "1112223333";
        String email = "";
        Boolean isResident = Boolean.FALSE;
		String error = null;
		Client client = null;

		try {
			client = accService.createClient(library, name, password, address, phoneNumber, email, isResident);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(client);
		// check error
		assertEquals("Email cannot be empty!", error);
	}

	//create client with space for email - throws error
	@Test
	public void testCreateClientEmailSpaces() {

		//create library that client needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//client attribute name null
		String name = "Roman";
        String password = "passy";
        String address = "555 roman str";
        String phoneNumber = "1112223333";
        String email = " ";
        Boolean isResident = Boolean.FALSE;
		String error = null;
		Client client = null;

		try {
			client = accService.createClient(library, name, password, address, phoneNumber, email, isResident);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(client);
		// check error
		assertEquals("Email cannot be empty!", error);
	}

	//create client with null isResident - throws error
	@Test
	public void testCreateClientIsResidentNull() {

		//create library that client needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//client attribute name null
		String name = "Roman";
        String password = "passy";
        String address = "555 roman str";
        String phoneNumber = "1112223333";
        String email = "roman@mail.ca";
        Boolean isResident = null;
		Client client = null;

		try {
			client = accService.createClient(library, name, password, address, phoneNumber, email, isResident);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(client);
		//check attributes
		assertEquals(name, client.getName());
		assertEquals(password, client.getPassword());
		assertEquals(address, client.getAddress());
		assertEquals(phoneNumber, client.getPhoneNumber());
		assertEquals(email, client.getEmail());
		assertEquals(Boolean.FALSE, client.getIsResident()); //isResident should've been set to false
	}

	//Create Employee mockito tests
	
	//create employee with valid inputs
	@Test
	public void testCreateEmployee() {
		//assertEquals(0, accService.getAllPersons().size()); //SHOULD I ADD A GET ALL IN SERVICE...DONT WANNA

		//create library that employee needs to be created	
		Library library = libraryDao.findByName(LIBRARY_KEY); //DO THIS INSTEAD?

		//params for my new employee
		String name = "John";
        String password = "passywordy";
        String address = "78 smith str";
        String phoneNumber = "9118743435";
        String email = "john@mail.ca";
        Employee.TypeOfEmployee typeOfEmployee = Employee.TypeOfEmployee.Librarian;
		Employee employee = null;

		try {
			employee = accService.createEmployee(library, name, password, address, phoneNumber, email, typeOfEmployee);
		} catch (IllegalArgumentException error) {
			fail();
		}
        
		assertNotNull(employee);
		//assertEquals(id, client.getId()); //HOW DO I CHECK THE ID??? DO I SET ONE?
		assertEquals(name, employee.getName());
		assertEquals(password, employee.getPassword());
		assertEquals(address, employee.getAddress());
		assertEquals(phoneNumber, employee.getPhoneNumber());
		assertEquals(email, employee.getEmail());
		assertEquals(typeOfEmployee, employee.getTypeOfEmployee());
	}

	//create employee with null library - throws error
	@Test
	public void testCreateEmployeeLibraryNull() {

		//library attribute is null
		Library library = null;
		String name = "John";
        String password = "passywordy";
        String address = "78 smith str";
        String phoneNumber = "9118743435";
        String email = "john@mail.ca";
        Employee.TypeOfEmployee typeOfEmployee = Employee.TypeOfEmployee.Librarian;
		String error = null;
		Employee employee = null;

		try {
			employee = accService.createEmployee(library, name, password, address, phoneNumber, email, typeOfEmployee);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(employee);
		// check error
		assertEquals("Library must be selected to create an employee account!", error);
	}

	//create employee with nonexistent library - throws error
	@Test
	public void testCreateEmployeeLibraryNotExist() {

		//create valid library		
		Library libraryNot = libraryDao.findByName(LIBRARY_KEY);

		//change name to non-existing library
		libraryNot.setName("fake lib");

		//employee attribute name null
		String name = "John";
        String password = "passywordy";
        String address = "78 smith str";
        String phoneNumber = "9118743435";
        String email = "john@mail.ca";
        Employee.TypeOfEmployee typeOfEmployee = Employee.TypeOfEmployee.Librarian;
		String error = null;
		Employee employee = null;

		try {
			employee = accService.createEmployee(libraryNot, name, password, address, phoneNumber, email, typeOfEmployee);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(employee);
		// check error
		assertEquals("library does not exist", error);
	}

	//create employee with null name - throws error
	@Test
	public void testCreateEmployeeNameNull() {

		//create library that employee needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//employee attribute name null
		String name = null;
        String password = "passywordy";
        String address = "78 smith str";
        String phoneNumber = "9118743435";
        String email = "john@mail.ca";
        Employee.TypeOfEmployee typeOfEmployee = Employee.TypeOfEmployee.Librarian;
		String error = null;
		Employee employee = null;

		try {
			employee = accService.createEmployee(library, name, password, address, phoneNumber, email, typeOfEmployee);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(employee);
		// check error
		assertEquals("Employee name cannot be empty!", error);
	}

	//create employee with empty name - throws error
	@Test
	public void testCreateEmployeeNameEmpty() {

		//create library that employee needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//employee attribute name empty
		String name = "";
        String password = "passywordy";
        String address = "78 smith str";
        String phoneNumber = "9118743435";
        String email = "john@mail.ca";
        Employee.TypeOfEmployee typeOfEmployee = Employee.TypeOfEmployee.Librarian;
		String error = null;
		Employee employee = null;

		try {
			employee = accService.createEmployee(library, name, password, address, phoneNumber, email, typeOfEmployee);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(employee);
		// check error
		assertEquals("Employee name cannot be empty!", error);
	}

	//create employee with space for name - throws error
	@Test
	public void testCreateEmployeeNameSpaces() {

		//create library that emplpoyee needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//employee attribute name space
		String name = " ";
        String password = "passywordy";
        String address = "78 smith str";
        String phoneNumber = "9118743435";
        String email = "john@mail.ca";
        Employee.TypeOfEmployee typeOfEmployee = Employee.TypeOfEmployee.Librarian;
		String error = null;
		Employee employee = null;

		try {
			employee = accService.createEmployee(library, name, password, address, phoneNumber, email, typeOfEmployee);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(employee);
		// check error
		assertEquals("Employee name cannot be empty!", error);
	}

	//create employee with null password - throws error
	@Test
	public void testCreateEmployeePasswordNull() {

		//create library that employee needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//employee attribute password null
		String name = "John";
        String password = null;
        String address = "78 smith str";
        String phoneNumber = "9118743435";
        String email = "john@mail.ca";
        Employee.TypeOfEmployee typeOfEmployee = Employee.TypeOfEmployee.Librarian;
		String error = null;
		Employee employee = null;

		try {
			employee = accService.createEmployee(library, name, password, address, phoneNumber, email, typeOfEmployee);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(employee);
		// check error
		assertEquals("Password cannot be empty!", error);
	}

	//create employee with empty password - throws error
	@Test
	public void testCreateEmployeePasswordEmpty() {

		//create library that employee needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//employee attribute password empty
		String name = "John";
        String password = "";
        String address = "78 smith str";
        String phoneNumber = "9118743435";
        String email = "john@mail.ca";
        Employee.TypeOfEmployee typeOfEmployee = Employee.TypeOfEmployee.Librarian;
		String error = null;
		Employee employee = null;

		try {
			employee = accService.createEmployee(library, name, password, address, phoneNumber, email, typeOfEmployee);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(employee);
		// check error
		assertEquals("Password cannot be empty!", error);
	}

	//create employee with space for password - throws error
	@Test
	public void testCreateEmployeePasswordSpaces() {

		//create library that employee needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//employee attribute password space
		String name = "John";
        String password = " ";
        String address = "78 smith str";
        String phoneNumber = "9118743435";
        String email = "john@mail.ca";
        Employee.TypeOfEmployee typeOfEmployee = Employee.TypeOfEmployee.Librarian;
		String error = null;
		Employee employee = null;

		try {
			employee = accService.createEmployee(library, name, password, address, phoneNumber, email, typeOfEmployee);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(employee);
		// check error
		assertEquals("Password cannot be empty!", error);
	}

	//create employee with null address - throws error
	@Test
	public void testCreateEmployeeAddressNull() {

		//create library that employee needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//employee attribute address null
		String name = "John";
        String password = "passywordy";
        String address = null;
        String phoneNumber = "9118743435";
        String email = "john@mail.ca";
		Employee.TypeOfEmployee typeOfEmployee = Employee.TypeOfEmployee.Librarian;
		String error = null;
		Employee employee = null;

		try {
			employee = accService.createEmployee(library, name, password, address, phoneNumber, email, typeOfEmployee);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(employee);
		// check error
		assertEquals("Address cannot be empty!", error);
	}

	//create employee with empty address - throws error
	@Test
	public void testCreateEmployeeAddressEmpty() {

		//create library that employee needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//employee attribute address empty
		String name = "John";
        String password = "passywordy";
        String address = "";
        String phoneNumber = "9118743435";
        String email = "john@mail.ca";
		Employee.TypeOfEmployee typeOfEmployee = Employee.TypeOfEmployee.Librarian;
		String error = null;
		Employee employee = null;

		try {
			employee = accService.createEmployee(library, name, password, address, phoneNumber, email, typeOfEmployee);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(employee);
		// check error
		assertEquals("Address cannot be empty!", error);
	}

	//create employee with space for address - throws error
	@Test
	public void testCreateEmployeeAddressSpaces() {

		//create library that employee needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//employee attribute address space
		String name = "John";
        String password = "passywordy";
        String address = " ";
        String phoneNumber = "9118743435";
        String email = "john@mail.ca";
		Employee.TypeOfEmployee typeOfEmployee = Employee.TypeOfEmployee.Librarian;
		String error = null;
		Employee employee = null;

		try {
			employee = accService.createEmployee(library, name, password, address, phoneNumber, email, typeOfEmployee);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(employee);
		// check error
		assertEquals("Address cannot be empty!", error);
	}

	//create employee with null phonenumber - throws error
	@Test
	public void testCreateEmployeePhoneNumberNull() {

		//create library that employee needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//employee attribute phonenumber null
		String name = "John";
        String password = "passywordy";
        String address = "78 smith str";
        String phoneNumber = null;
        String email = "john@mail.ca";
		Employee.TypeOfEmployee typeOfEmployee = Employee.TypeOfEmployee.Librarian;
		String error = null;
		Employee employee = null;

		try {
			employee = accService.createEmployee(library, name, password, address, phoneNumber, email, typeOfEmployee);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(employee);
		// check error
		assertEquals("Phonenumber cannot be empty!", error);
	}

	//create employee with empty phonenumber - throws error
	@Test
	public void testCreateEmployeePhoneNumberEmpty() {

		//create library that employee needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//employee attribute phonenumber empty
		String name = "John";
        String password = "passywordy";
        String address = "78 smith str";
        String phoneNumber = "";
        String email = "john@mail.ca";
		Employee.TypeOfEmployee typeOfEmployee = Employee.TypeOfEmployee.Librarian;
		String error = null;
		Employee employee = null;

		try {
			employee = accService.createEmployee(library, name, password, address, phoneNumber, email, typeOfEmployee);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(employee);
		// check error
		assertEquals("Phonenumber cannot be empty!", error);
	}

	//create employee with space for phonenumber - throws error
	@Test
	public void testCreateEmployeePhoneNumberSpaces() {

		//create library that employee needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//employee attribute phonenumber space
		String name = "John";
        String password = "passywordy";
        String address = "78 smith str";
        String phoneNumber = " ";
        String email = "john@mail.ca";
		Employee.TypeOfEmployee typeOfEmployee = Employee.TypeOfEmployee.Librarian;
		String error = null;
		Employee employee = null;

		try {
			employee = accService.createEmployee(library, name, password, address, phoneNumber, email, typeOfEmployee);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(employee);
		// check error
		assertEquals("Phonenumber cannot be empty!", error);
	}

	//create employee with null email - throws error
	@Test
	public void testCreateEmployeeEmailNull() {

		//create library that employee needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//employee attribute email null
		String name = "John";
        String password = "passywordy";
        String address = "78 smith str";
        String phoneNumber = "9118743435";
        String email = null;
		Employee.TypeOfEmployee typeOfEmployee = Employee.TypeOfEmployee.Librarian;
		String error = null;
		Employee employee = null;

		try {
			employee = accService.createEmployee(library, name, password, address, phoneNumber, email, typeOfEmployee);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(employee);
		// check error
		assertEquals("Email cannot be empty!", error);
	}

	//create employee with empty email - throws error
	@Test
	public void testCreateEmployeeEmailEmpty() {

		//create library that employee needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//employee attribute email empty
		String name = "John";
        String password = "passywordy";
        String address = "78 smith str";
        String phoneNumber = "9118743435";
        String email = "";
		Employee.TypeOfEmployee typeOfEmployee = Employee.TypeOfEmployee.Librarian;
		String error = null;
		Employee employee = null;

		try {
			employee = accService.createEmployee(library, name, password, address, phoneNumber, email, typeOfEmployee);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(employee);
		// check error
		assertEquals("Email cannot be empty!", error);
	}

	//create employee with space for email - throws error
	@Test
	public void testCreateEmployeeEmailSpaces() {

		//create library that employee needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//employee attribute email space
		String name = "John";
        String password = "passywordy";
        String address = "78 smith str";
        String phoneNumber = "9118743435";
        String email = " ";
		Employee.TypeOfEmployee typeOfEmployee = Employee.TypeOfEmployee.Librarian;
		String error = null;
		Employee employee = null;

		try {
			employee = accService.createEmployee(library, name, password, address, phoneNumber, email, typeOfEmployee);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(employee);
		// check error
		assertEquals("Email cannot be empty!", error);
	}

	//create employee with null isResident - throws error
	@Test
	public void testCreateEmployeeIsResidentNull() {

		//create library that employee needs to be created
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//employee attribute name null
		String name = "John";
        String password = "passywordy";
        String address = "78 smith str";
        String phoneNumber = "9118743435";
        String email = "john@mail.ca";
		Employee.TypeOfEmployee typeOfEmployee = null;
		String error = null;
		Employee employee = null;

		try {
			employee = accService.createEmployee(library, name, password, address, phoneNumber, email, typeOfEmployee);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(employee);
		// check error
		assertEquals("Must select an employee type!", error);
	}

	//Edit employee mockito tests start here

	//Edit employee with the same attributes as before
	@Test
	public void testEditClientAllSame() {
	
		//create necessary library
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//create necessary client
		Client client = clientDao.findById(CLIENT_ID);
		client.setLibrary(library);

		try {
			client = accService.editClient(library, CLIENT_ID, CLIENT_NAME, CLIENT_PASSWORD, CLIENT_ADDRESS, CLIENT_PHONENUMBER, CLIENT_EMAIL, CLIENT_NUMOFFLAGS, CLIENT_ISRESIDENT);
		} catch (IllegalArgumentException e) {
			fail();
		}		

		assertNotNull(client);
		assertEquals(library, client.getLibrary());
		assertEquals(CLIENT_ID, client.getId());
		assertEquals(CLIENT_NAME, client.getName());
		assertEquals(CLIENT_PASSWORD, client.getPassword());
		assertEquals(CLIENT_ADDRESS, client.getAddress());
		assertEquals(CLIENT_PHONENUMBER, client.getPhoneNumber());
		assertEquals(CLIENT_EMAIL, client.getEmail());
		assertEquals(CLIENT_NUMOFFLAGS, client.getNumOfFlag());
		assertEquals(CLIENT_ISRESIDENT, client.getIsResident());
	}

	//Edit employee with the same attributes as before
	@Test
	public void testEditClientAllChange() {
	
		//create necessary library
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//create necessary client
		Client client = clientDao.findById(CLIENT_ID);
		client.setLibrary(library);

		try {
			client = accService.editClient(library, CLIENT_ID, "Frank", "word", "75 smthg str", "8885551111", "frank@mail.ca", 1, Boolean.TRUE);
		} catch (IllegalArgumentException e) {
			fail();
		}		

		assertNotNull(client);
		assertEquals(library, client.getLibrary());
		assertEquals(CLIENT_ID, client.getId());
		assertEquals("Frank", client.getName());
		assertEquals("word", client.getPassword());
		assertEquals("75 smthg str", client.getAddress());
		assertEquals("8885551111", client.getPhoneNumber());
		assertEquals("frank@mail.ca", client.getEmail());
		assertEquals(1, client.getNumOfFlag());
		assertEquals(Boolean.TRUE, client.getIsResident());
	}

	//Edit client with all null attributes
	@Test
	public void testEditClientAllNull() {

		//create necessary client
		Client client = clientDao.findById(CLIENT_ID);

		try {
			client = accService.editClient(null, CLIENT_ID, null, null, null, null, null, 0, null);
		} catch (IllegalArgumentException e) {
			fail();
		}		

		assertNotNull(client);
		assertEquals(LIBRARY_KEY, client.getLibrary().getName());
		assertEquals(CLIENT_ID, client.getId());
		assertEquals(CLIENT_NAME, client.getName());
		assertEquals(CLIENT_PASSWORD, client.getPassword());
		assertEquals(CLIENT_ADDRESS, client.getAddress());
		assertEquals(CLIENT_PHONENUMBER, client.getPhoneNumber());
		assertEquals(CLIENT_EMAIL, client.getEmail());
		assertEquals(0, client.getNumOfFlag());
		assertEquals(CLIENT_ISRESIDENT, client.getIsResident());
	}

	//Edit employee with nonexistent client - throws error
	@Test
	public void testEditClientNotExist() {

		//create necessary library
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//create necessary client
		Client client = clientDao.findById(CLIENT_ID);
		client.setLibrary(library);

		String error = null;

		try {
			client = accService.editClient(null, 3333, null, null, null, null, null, 0, null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}	

		assertEquals("Client does not exist!", error);
	}

	//Edit employee with invalid numOfFlags - throws error
	@Test
	public void testEditClientWrongNumOfFlags() {
		
		//create necessary library
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//create necessary client
		Client client = clientDao.findById(CLIENT_ID);
		client.setLibrary(library);

		String error = null;

		try {
			client = accService.editClient(null, CLIENT_ID, null, null, null, null, null, 4, null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}	

		assertEquals("Non-valid number of flags: it should be a number from 0 to 3", error);
	}

	//Edit employee with empty attributes except for library, id, numofflags, typeOfClient - throws error
	@Test
	public void testEditClientAllEmpty() {

		//create necessary library
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//create necessary client
		Client client = clientDao.findById(CLIENT_ID);
		client.setLibrary(library);

		try {
			client = accService.editClient(library, CLIENT_ID, "", "", "", "", "", 0, null);
		} catch (IllegalArgumentException e) {
			fail();
		}		

		assertNotNull(client);
		assertEquals(library, client.getLibrary());
		assertEquals(CLIENT_ID, client.getId());
		assertEquals(CLIENT_NAME, client.getName());
		assertEquals(CLIENT_PASSWORD, client.getPassword());
		assertEquals(CLIENT_ADDRESS, client.getAddress());
		assertEquals(CLIENT_PHONENUMBER, client.getPhoneNumber());
		assertEquals(CLIENT_EMAIL, client.getEmail());
		assertEquals(0, client.getNumOfFlag());
		assertEquals(CLIENT_ISRESIDENT, client.getIsResident());
	}

	//Edit employee with space for attributes except for library, id, numofflags, typeOfClient - throws error
	@Test
	public void testEditClientAllSpace() {

		//create necessary library
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//create necessary client
		Client client = clientDao.findById(CLIENT_ID);
		client.setLibrary(library);

		try {
			client = accService.editClient(library, CLIENT_ID, " ", " ", " ", " ", " ", 0, null);
		} catch (IllegalArgumentException e) {
			fail();
		}		

		assertNotNull(client);
		assertEquals(library, client.getLibrary());
		assertEquals(CLIENT_ID, client.getId());
		assertEquals(CLIENT_NAME, client.getName());
		assertEquals(CLIENT_PASSWORD, client.getPassword());
		assertEquals(CLIENT_ADDRESS, client.getAddress());
		assertEquals(CLIENT_PHONENUMBER, client.getPhoneNumber());
		assertEquals(CLIENT_EMAIL, client.getEmail());
		assertEquals(0, client.getNumOfFlag());
		assertEquals(CLIENT_ISRESIDENT, client.getIsResident());
	}

	//Edit employee with all same attributes as before
	@Test
	public void testEditEmployeeAllSame() {
	
		//create necessary library
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//create necessary employee
		Employee employee = employeeDao.findById(EMPLOYEE_ID);
		employee.setLibrary(library);

		try {
			employee = accService.editEmployee(library, EMPLOYEE_ID, EMPLOYEE_NAME, EMPLOYEE_PASSWORD, EMPLOYEE_ADDRESS, EMPLOYEE_PHONENUMBER, EMPLOYEE_EMAIL, EMPLOYEE_TYPE);
		} catch (IllegalArgumentException e) {
			fail();
		}		

		assertNotNull(employee);
		assertEquals(EMPLOYEE_ID, employee.getId());
		assertEquals(EMPLOYEE_NAME, employee.getName());
		assertEquals(EMPLOYEE_PASSWORD, employee.getPassword());
		assertEquals(EMPLOYEE_ADDRESS, employee.getAddress());
		assertEquals(EMPLOYEE_PHONENUMBER, employee.getPhoneNumber());
		assertEquals(EMPLOYEE_EMAIL, employee.getEmail());
		assertEquals(EMPLOYEE_TYPE, employee.getTypeOfEmployee());
	}

	//Edit employee with all attributes different from before (except library and id)
	@Test
	public void testEditEmployeeAllChange() {
	
		//create necessary library
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//create necessary employee
		Employee employee = employeeDao.findById(EMPLOYEE_ID);
		employee.setLibrary(library);

		try {
			employee = accService.editEmployee(library, EMPLOYEE_ID, "Sarah", "pw", "44 weird str", "5256569999", "sarah@mail.ca", Employee.TypeOfEmployee.HeadLibrarian);
		} catch (IllegalArgumentException e) {
			fail();
		}		

		assertNotNull(employee);
		assertEquals(EMPLOYEE_ID, employee.getId());
		assertEquals("Sarah", employee.getName());
		assertEquals("pw", employee.getPassword());
		assertEquals("44 weird str", employee.getAddress());
		assertEquals("5256569999", employee.getPhoneNumber());
		assertEquals("sarah@mail.ca", employee.getEmail());
		assertEquals(Employee.TypeOfEmployee.HeadLibrarian, employee.getTypeOfEmployee());
	}

	//Edit employee with all null attributes except for id
	@Test
	public void testEditEmployeeAllNull() {

		//create necessary library
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//create necessary employee
		Employee employee = employeeDao.findById(EMPLOYEE_ID);
		employee.setLibrary(library);

		try {
			employee = accService.editEmployee(null, EMPLOYEE_ID, null, null, null, null, null, null);
		} catch (IllegalArgumentException e) {
			fail();
		}		

		assertNotNull(employee);
		assertEquals(LIBRARY_KEY, employee.getLibrary().getName());
		assertEquals(EMPLOYEE_ID, employee.getId());
		assertEquals(EMPLOYEE_NAME, employee.getName());
		assertEquals(EMPLOYEE_PASSWORD, employee.getPassword());
		assertEquals(EMPLOYEE_ADDRESS, employee.getAddress());
		assertEquals(EMPLOYEE_PHONENUMBER, employee.getPhoneNumber());
		assertEquals(EMPLOYEE_EMAIL, employee.getEmail());
		assertEquals(EMPLOYEE_TYPE, employee.getTypeOfEmployee());
	}

	//Edit employee with nonexistent employee - throws error
	@Test
	public void testEditEmployeeNotExist() {

		//create necessary library
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//create necessary employee
		Employee employee = employeeDao.findById(EMPLOYEE_ID);
		employee.setLibrary(library);

		String error = null;

		try {
			employee = accService.editEmployee(null, 3333, null, null, null, null, null, null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}	

		assertEquals("Employee does not exist!", error);
	}

	//Edit employee with all empty attributes except for id
	@Test
	public void testEditEmployeeAllEmpty() {

		//create necessary library
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//create necessary employee
		Employee employee = employeeDao.findById(EMPLOYEE_ID);
		employee.setLibrary(library);

		try {
			employee = accService.editEmployee(library, EMPLOYEE_ID, "", "", "", "", "", null);
		} catch (IllegalArgumentException e) {
			fail();
		}		

		assertNotNull(employee);
		assertEquals(library, employee.getLibrary());
		assertEquals(EMPLOYEE_ID, employee.getId());
		assertEquals(EMPLOYEE_NAME, employee.getName());
		assertEquals(EMPLOYEE_PASSWORD, employee.getPassword());
		assertEquals(EMPLOYEE_ADDRESS, employee.getAddress());
		assertEquals(EMPLOYEE_PHONENUMBER, employee.getPhoneNumber());
		assertEquals(EMPLOYEE_EMAIL, employee.getEmail());
		assertEquals(EMPLOYEE_TYPE, employee.getTypeOfEmployee());
	}

	//Edit employee with all spaces attributes except for id
	@Test
	public void testEditEmployeeAllSpace() {

		//create necessary library
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//create necessary employee
		Employee employee = employeeDao.findById(EMPLOYEE_ID);
		employee.setLibrary(library);

		try {
			employee = accService.editEmployee(library, EMPLOYEE_ID, " ", " ", " ", " ", " ", null);
		} catch (IllegalArgumentException e) {
			fail();
		}		

		assertNotNull(employee);
		assertEquals(library, employee.getLibrary());
		assertEquals(EMPLOYEE_ID, employee.getId());
		assertEquals(EMPLOYEE_NAME, employee.getName());
		assertEquals(EMPLOYEE_PASSWORD, employee.getPassword());
		assertEquals(EMPLOYEE_ADDRESS, employee.getAddress());
		assertEquals(EMPLOYEE_PHONENUMBER, employee.getPhoneNumber());
		assertEquals(EMPLOYEE_EMAIL, employee.getEmail());
		assertEquals(EMPLOYEE_TYPE, employee.getTypeOfEmployee());
	}

	//Get tests start here

	//Get client with client id
	@Test
	public void testGetClient() {
		//create necessary library
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//create necessary client
		Client client = clientDao.findById(CLIENT_ID);
		client.setLibrary(library);
		client = null;

		try {
			client = accService.getClient(CLIENT_ID);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(client);
		assertEquals(CLIENT_ID, client.getId());
	}

	//Get client that doesn't exist - throws error
	@Test
	public void testGetClientNotExist() {

		//create null client
		Client client = null;

		String error = null;

		try {
			client = accService.getClient(222);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(client);
		assertEquals("Client does not exist!", error);
	}

	//Get employee with employee id
	@Test
	public void testGetEmployee() {
		//create necessary library
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//create necessary employee
		Employee employee = employeeDao.findById(EMPLOYEE_ID);
		employee.setLibrary(library);
		employee = null;

		try {
			employee = accService.getEmployee(EMPLOYEE_ID);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(employee);
		assertEquals(EMPLOYEE_ID, employee.getId());
	}

	//Get employee that doesn't exist - throws error
	@Test
	public void testGetEmployeeNotExist() {
		//create necessary library
		//Library library = libraryDao.findByName(LIBRARY_KEY);

		//create null employee
		Employee employee = null;

		String error = null;

		try {
			employee = accService.getEmployee(555);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(employee);
		assertEquals("Employee does not exist!", error);
	}

	//LogIn tests start here

	//LogIn client with client id and password
	@Test
	public void testLogInClient() {
		//create necessary library
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//create necessary client
		Client client = clientDao.findById(CLIENT_ID);
		client.setLibrary(library);
		client = null;

		try {
			client = accService.logInClient(CLIENT_ID, CLIENT_PASSWORD);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(client);
		assertEquals(CLIENT_ID, client.getId());
		assertEquals(CLIENT_PASSWORD, client.getPassword());
	}

	//LogIn client with nonexistent client
	@Test
	public void testLogInClientNotExist() {
		
		//create null client
		Client client = null;

		String error = null;

		try {
			client = accService.logInClient(454, CLIENT_PASSWORD);
		}  catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(client);
		assertEquals("Client does not exist!", error);
	}

	//LogIn client with client id and wrong password
	@Test
	public void testLogInClientInvalidPassword() {
		
		//create necessary library
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//create necessary client
		Client client = clientDao.findById(CLIENT_ID);
		client.setLibrary(library);
		client = null;

		String error = null;

		try {
			client = accService.logInClient(CLIENT_ID, "wrongpass");
		}  catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(client);
		assertEquals("Incorrect password", error);
	}

	//LogIn employee with employee id and password
	@Test
	public void testLogInEmployee() {
		//create necessary library
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//create necessary client
		Employee employee = employeeDao.findById(EMPLOYEE_ID);
		employee.setLibrary(library);
		employee = null;

		try {
			employee = accService.logInEmployee(EMPLOYEE_ID, EMPLOYEE_PASSWORD);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(employee);
		assertEquals(EMPLOYEE_ID, employee.getId());
		assertEquals(EMPLOYEE_PASSWORD, employee.getPassword());
	}

	//LogIn employee with employee id and password
	@Test
	public void testLogInEmployeeNotExist() {
		
		//create null employee
		Employee employee = null;

		String error = null;

		try {
			employee = accService.logInEmployee(454, EMPLOYEE_PASSWORD);
		}  catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(employee);
		assertEquals("Employee does not exist!", error);
	}

	//LogIn employee with employee id and wrong password
	@Test
	public void testLogInEmployeeInvalidPassword() {
		
		//create necessary library
		Library library = libraryDao.findByName(LIBRARY_KEY);

		//create necessary client
		Employee employee = employeeDao.findById(EMPLOYEE_ID);
		employee.setLibrary(library);
		employee = null;

		String error = null;

		try {
			employee = accService.logInEmployee(EMPLOYEE_ID, "wrongpass");
		}  catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(employee);
		assertEquals("Incorrect password", error);
	}
}