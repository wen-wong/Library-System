package ca.mcgill.ecse321.projectgroup1.service;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import ca.mcgill.ecse321.projectgroup1.dao.ClientRepository;
import ca.mcgill.ecse321.projectgroup1.dao.InUseSlotRepository;
import ca.mcgill.ecse321.projectgroup1.dao.LibraryItemRepository;
import ca.mcgill.ecse321.projectgroup1.dao.LibraryRepository;
import ca.mcgill.ecse321.projectgroup1.model.Book;
import ca.mcgill.ecse321.projectgroup1.model.Client;
import ca.mcgill.ecse321.projectgroup1.model.InUseSlot;
import ca.mcgill.ecse321.projectgroup1.model.LibraryItem;
import ca.mcgill.ecse321.projectgroup1.model.Library;
import ca.mcgill.ecse321.projectgroup1.model.InUseSlot.Status;

@ExtendWith(MockitoExtension.class)
public class TestManageCartService {
    @Mock
    private InUseSlotRepository inUseSlotDao;
    @Mock
    private ClientRepository clientDao;
    @Mock
    private LibraryItemRepository libraryItemDao;
	@Mock
	private LibraryRepository libraryDao;

	@InjectMocks
	private ManageCartService service;

	
	private static final String LIBRARY_KEY = "TestLibrary";
    private static final int INUSESLOT_KEY = 1;
	private static final int LIBRARYITEM_KEY = 11;
	private static final int NONEXISTING_LIBRARYITEM_KEY = 00;

	private static final int CLIENT_KEY = 111;
	private static final int EMPTYCART_CLIENT_KEY = 112;
	private static final int NONEXISTING_CLIENT_KEY = 000;

	private static final int OVERLAPPING_LIBRARYITEM_KEY = 101;
	private static final int OVERLAPPING_CLIENT_KEY = 102;
	private static final int OVERLAPPING_INUSESLOT_KEY = 103;

	private static final int OVERLAPPING_RENEW_LIBRARYITEM_KEY = 104;
	private static final int OVERLAPPING_RENEW_CLIENT_KEY = 105;
	private static final int OVERLAPPING_RENEW_INUSESLOT_KEY = 106;

	@BeforeEach
	public void setMockOutput() {
		lenient().when(libraryDao.findByName(anyString())).thenAnswer(
			(InvocationOnMock invocation) -> {
				if (invocation.getArgument(0).equals(LIBRARY_KEY)) {
					Library library = new Library();
					library.setName(LIBRARY_KEY);
					return library;
				} else {
					return null;
				}
			});
        lenient().when(libraryItemDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(LIBRARYITEM_KEY)) {
				LibraryItem libraryItem = new Book();
                libraryItem.setId(LIBRARYITEM_KEY);
				return libraryItem;
			} 
			else if (invocation.getArgument(0).equals(OVERLAPPING_LIBRARYITEM_KEY)) {
				LibraryItem libraryItem = new Book();
                libraryItem.setId(OVERLAPPING_LIBRARYITEM_KEY);
				return libraryItem;
			} 
			else if (invocation.getArgument(0).equals(OVERLAPPING_RENEW_LIBRARYITEM_KEY)) {
				LibraryItem libraryItem = new Book();
                libraryItem.setId(OVERLAPPING_RENEW_LIBRARYITEM_KEY);
				return libraryItem;
			} 
			else {
				return null;
			}
		});
		lenient().when(clientDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(CLIENT_KEY) || invocation.getArgument(0).equals(EMPTYCART_CLIENT_KEY) || invocation.getArgument(0).equals(OVERLAPPING_CLIENT_KEY) || invocation.getArgument(0).equals(OVERLAPPING_RENEW_CLIENT_KEY)) {
				Client client = new Client();
				Library library = libraryDao.findByName(LIBRARY_KEY);
				client.setLibrary(library);
    			client.setId(invocation.getArgument(0));
				return client;
			}
			else {
				return null;
			}
		});
		lenient().when(inUseSlotDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(INUSESLOT_KEY)) {
				InUseSlot inUseSlot = new InUseSlot();
				inUseSlot.setClient(clientDao.findById(CLIENT_KEY));
				inUseSlot.setLibraryItem(libraryItemDao.findById(LIBRARYITEM_KEY));
				inUseSlot.setStatus(Status.InCart);
                inUseSlot.setId(INUSESLOT_KEY);
				return inUseSlot;
			} 
			else if (invocation.getArgument(0).equals(OVERLAPPING_INUSESLOT_KEY) ) {
				InUseSlot inUseSlot = new InUseSlot();
				inUseSlot.setStatus(Status.Booked);
				inUseSlot.setStartDate(Date.valueOf("2021-11-01"));
				inUseSlot.setEndDate(Date.valueOf("2021-11-14")); 
				inUseSlot.setClient(clientDao.findById(OVERLAPPING_CLIENT_KEY));
				inUseSlot.setLibraryItem(libraryItemDao.findById(OVERLAPPING_LIBRARYITEM_KEY));
                inUseSlot.setId(OVERLAPPING_INUSESLOT_KEY);
				return inUseSlot;
			}
			else if (invocation.getArgument(0).equals(OVERLAPPING_RENEW_INUSESLOT_KEY) ) {
				InUseSlot inUseSlot = new InUseSlot();
				inUseSlot.setStatus(Status.Booked);
				inUseSlot.setStartDate(Date.valueOf("2021-11-01"));
				inUseSlot.setEndDate(Date.valueOf("2021-11-14")); 
				inUseSlot.setClient(clientDao.findById(OVERLAPPING_RENEW_CLIENT_KEY));
				inUseSlot.setLibraryItem(libraryItemDao.findById(OVERLAPPING_RENEW_LIBRARYITEM_KEY));
                inUseSlot.setId(OVERLAPPING_RENEW_INUSESLOT_KEY);
				return inUseSlot;
			}
			else {
				return null;
			}
		});
		lenient().when(inUseSlotDao.findInUseSlotByClientId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(CLIENT_KEY)) {
				List<InUseSlot> inUseSlots = new ArrayList<InUseSlot>();
				inUseSlots.add(inUseSlotDao.findById(INUSESLOT_KEY));
				return inUseSlots;
			} 
			else if (invocation.getArgument(0).equals(OVERLAPPING_CLIENT_KEY)) {
				List<InUseSlot> inUseSlots = new ArrayList<InUseSlot>();
				inUseSlots.add(inUseSlotDao.findById(OVERLAPPING_INUSESLOT_KEY));
				return inUseSlots;
			} 
			else if (invocation.getArgument(0).equals(OVERLAPPING_RENEW_CLIENT_KEY)) {
				List<InUseSlot> inUseSlots = new ArrayList<InUseSlot>();
				inUseSlots.add(inUseSlotDao.findById(OVERLAPPING_RENEW_INUSESLOT_KEY));
				return inUseSlots;
			} 
			else if (invocation.getArgument(0).equals(EMPTYCART_CLIENT_KEY)) {
				List<InUseSlot> inUseSlots = new ArrayList<InUseSlot>();
				return inUseSlots;
			}
			else {
				return null;
			}
		});
		lenient().when(inUseSlotDao.findInUseSlotByLibraryItemId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(LIBRARYITEM_KEY)) {
				List<InUseSlot> inUseSlots = new ArrayList<InUseSlot>();
				inUseSlots.add(inUseSlotDao.findById(INUSESLOT_KEY));
				return inUseSlots;
			} 
			else if (invocation.getArgument(0).equals(OVERLAPPING_LIBRARYITEM_KEY)) {
				List<InUseSlot> inUseSlots = new ArrayList<InUseSlot>();
				inUseSlots.add(inUseSlotDao.findById(OVERLAPPING_INUSESLOT_KEY));
				return inUseSlots;
			}
			else if (invocation.getArgument(0).equals(OVERLAPPING_RENEW_LIBRARYITEM_KEY)) {
				List<InUseSlot> inUseSlots = new ArrayList<InUseSlot>();
				InUseSlot inUseSlot = new InUseSlot();
				inUseSlot.setStatus(Status.Booked);
				inUseSlot.setStartDate(Date.valueOf("2021-11-14"));
				inUseSlot.setEndDate(Date.valueOf("2021-11-28")); 
				inUseSlot.setClient(clientDao.findById(OVERLAPPING_CLIENT_KEY));
				inUseSlot.setLibraryItem(libraryItemDao.findById(OVERLAPPING_LIBRARYITEM_KEY));
                inUseSlot.setId(OVERLAPPING_INUSESLOT_KEY);
				inUseSlots.add(inUseSlotDao.findById(OVERLAPPING_INUSESLOT_KEY));
				inUseSlots.add(inUseSlot);
				return inUseSlots;
			}
			else {
				return null;
			}
		});
		lenient().when(clientDao.existsById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(CLIENT_KEY) || invocation.getArgument(0).equals(EMPTYCART_CLIENT_KEY) || invocation.getArgument(0).equals(OVERLAPPING_CLIENT_KEY) || invocation.getArgument(0).equals(OVERLAPPING_RENEW_CLIENT_KEY)) {
				return Boolean.TRUE;
			} else {
				return Boolean.FALSE;
			}
		});
		lenient().when(libraryItemDao.existsById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(LIBRARYITEM_KEY) || invocation.getArgument(0).equals(OVERLAPPING_LIBRARYITEM_KEY) || invocation.getArgument(0).equals(OVERLAPPING_RENEW_LIBRARYITEM_KEY)) {
				return Boolean.TRUE;
			} else {
				return Boolean.FALSE;
			}
		});
	}
	/**
	 * testing libraryItems for client
	 */
    @Test
	public void testGetCartItems() {
		assertEquals(LIBRARYITEM_KEY, service.getCartItems(CLIENT_KEY).get(0).getId());
	}
	/**
	 * testing libraryItems for null client
	 */
	@Test
	public void testGetCartItemsNullClient() {
		String error = null;
		LibraryItem cartItem = null;
		try {
			cartItem = service.getCartItems(NONEXISTING_CLIENT_KEY).get(0);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(cartItem);
		// check error
		assertEquals("client does not exist", error);
	}
	/**
	 * testing empty inUseSlots for client 
	 */
	@Test
	public void testGetCartItemsEmptyCart() {
		assertEquals(0, service.getCartItems(EMPTYCART_CLIENT_KEY).size());
	}
	/**
	 * testing inUseSlots for client
	 */
    @Test
	public void testGetCartItemInUseSlot() {
		assertEquals(INUSESLOT_KEY, service.getCartItemInUseSlot(CLIENT_KEY).get(0).getId());
	}
	/**
	 * testing inUseSlots for null client
	 */
	@Test
	public void testGetCartItemInUseSlotNullClient() {
		String error = null;
		InUseSlot inUseSlot = null;
		try {
			inUseSlot = service.getCartItemInUseSlot(NONEXISTING_CLIENT_KEY).get(0);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(inUseSlot);
		// check error
		assertEquals("client does not exist", error);
	}
	/**
	 * testing empty inUseSlots for client 
	 */
	@Test
	public void testGetCartItemInUseSlotEmptyCart() {
		assertEquals(0, service.getCartItemInUseSlot(EMPTYCART_CLIENT_KEY).size());
	}
	/**
	 * test succesful reserve cart Item
	 */
	@Test 
	public void testReserveCartItem() {
		Date currentDate = Date.valueOf("2021-11-12");
		assertEquals(Status.Reserved, service.reserveCartItem(CLIENT_KEY, LIBRARYITEM_KEY, currentDate).getStatus());
	}
	/**
	 * test unsuccesful reserve cart null client
	 */
	@Test 
	public void testReserveCartItemNullClient() {
		String error = null;
		InUseSlot inUseSlot = null;
		Date currentDate = Date.valueOf("2021-11-12");

		try {
			inUseSlot = service.reserveCartItem(NONEXISTING_CLIENT_KEY, LIBRARYITEM_KEY, currentDate);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(inUseSlot);
		// check error
		assertEquals("client does not exist", error);
	}
	/**
	 * test unsuccesful reserve cart null item
	 */
	@Test 
	public void testReserveCartItemNullItem() {
		String error = null;
		InUseSlot inUseSlot = null;
		Date currentDate = Date.valueOf("2021-11-12");

		try {
			inUseSlot = service.reserveCartItem(CLIENT_KEY, NONEXISTING_LIBRARYITEM_KEY, currentDate);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(inUseSlot);
		// check error
		assertEquals("libraryItem does not exist", error);
	}
	/**
	 * test unsuccesful reserve cart null date
	 */
	@Test 
	public void testReserveCartItemNullDate() {
		String error = null;
		InUseSlot inUseSlot = null;
		Date currentDate = null;

		try {
			inUseSlot = service.reserveCartItem(CLIENT_KEY, LIBRARYITEM_KEY, currentDate);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(inUseSlot);
		// check error
		assertEquals("Date is not set", error);
	}
	/**
	 * test unsuccesful reserve cart overlapping inUseSlot
	 */
	@Test 
	public void testReserveCartItemOverlappingInUseSlot() {
		String error = null;
		InUseSlot inUseSlot = null;
		Date currentDate = Date.valueOf("2021-11-12");
		try {
			inUseSlot = service.reserveCartItem(OVERLAPPING_CLIENT_KEY, OVERLAPPING_LIBRARYITEM_KEY, currentDate);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(inUseSlot);
		// check error
		assertEquals("overlap between attempted reservation and inUseSlots for libraryItem", error);
	}
	/**
	 * test succesful renew booked Item
	 */
	@Test 
	public void testRenewCartItem() {
		Date currentDate = Date.valueOf("2021-11-14");
		assertEquals(Date.valueOf("2021-11-28"), service.renewCartItem(OVERLAPPING_CLIENT_KEY, OVERLAPPING_LIBRARYITEM_KEY, currentDate).getEndDate());
	}
	/**
	 * test unsuccesful renew cart not booked item
	 */
	@Test 
	public void testRenewCartItemNotBooked() {
		String error = null;
		InUseSlot inUseSlot = null;
		Date currentDate = Date.valueOf("2021-11-12");

		try {
			inUseSlot = service.renewCartItem(CLIENT_KEY, LIBRARYITEM_KEY, currentDate);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(inUseSlot);
		// check error
		assertEquals("LibraryItem is currently not booked!", error);
	}
	/**
	 * test unsuccesful renew cart null client
	 */
	@Test 
	public void testRenewCartItemNullClient() {
		String error = null;
		InUseSlot inUseSlot = null;
		Date currentDate = Date.valueOf("2021-11-12");

		try {
			inUseSlot = service.renewCartItem(NONEXISTING_CLIENT_KEY, LIBRARYITEM_KEY, currentDate);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(inUseSlot);
		// check error
		assertEquals("client does not exist", error);
	}
	/**
	 * test unsuccesful renew cart null item
	 */
	@Test 
	public void testRenewCartItemNullItem() {
		String error = null;
		InUseSlot inUseSlot = null;
		Date currentDate = Date.valueOf("2021-11-12");

		try {
			inUseSlot = service.renewCartItem(CLIENT_KEY, NONEXISTING_LIBRARYITEM_KEY, currentDate);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(inUseSlot);
		// check error
		assertEquals("libraryItem does not exist", error);
	}
	/**
	 * test unsuccesful renew cart null date
	 */
	@Test 
	public void testRenewCartItemNullDate() {
		String error = null;
		InUseSlot inUseSlot = null;
		Date currentDate = null;

		try {
			inUseSlot = service.renewCartItem(OVERLAPPING_CLIENT_KEY, OVERLAPPING_LIBRARYITEM_KEY, currentDate);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(inUseSlot);
		// check error
		assertEquals("Date is not set", error);
	}

	@Test 
	public void testRemoveCartItem() {
		assertEquals(INUSESLOT_KEY, service.removeCartItem(CLIENT_KEY, LIBRARYITEM_KEY).getId());
	}
	/**
	 * test unsuccesful reserve cart null client
	 */
	@Test 
	public void testRemoveCartItemNullClient() {
		String error = null;
		InUseSlot inUseSlot = null;

		try {
			inUseSlot = service.removeCartItem(NONEXISTING_CLIENT_KEY, LIBRARYITEM_KEY);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(inUseSlot);
		// check error
		assertEquals("client does not exist", error);
	}
	/**
	 * test unsuccesful remove cart null item
	 */
	@Test 
	public void testRemoveCartItemNullItem() {
		String error = null;
		InUseSlot inUseSlot = null;
		try {
			inUseSlot = service.removeCartItem(CLIENT_KEY, NONEXISTING_LIBRARYITEM_KEY);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(inUseSlot);
		// check error
		assertEquals("libraryItem does not exist", error);
	}

	/**
	 * test succesful borrow Item
	 */
	@Test 
	public void testReturnBorrowedItem() {
		Date currentDate = Date.valueOf("2021-11-14");
		assertEquals(OVERLAPPING_CLIENT_KEY, service.returnBorrowedItem(OVERLAPPING_LIBRARYITEM_KEY, currentDate).getId());
	}
	/**
	 * test succesful checkout Item
	 */
	@Test 
	public void testCheckoutCartItem() {
		Date currentDate = Date.valueOf("2021-11-14");
		assertEquals(INUSESLOT_KEY, service.checkoutCartItem(CLIENT_KEY, LIBRARYITEM_KEY, currentDate).getId());
	}
}
