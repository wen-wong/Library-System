package ca.mcgill.ecse321.projectgroup1.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup1.dao.ClientRepository;
import ca.mcgill.ecse321.projectgroup1.dao.InUseSlotRepository;
import ca.mcgill.ecse321.projectgroup1.dao.LibraryItemRepository;
import ca.mcgill.ecse321.projectgroup1.model.Client;
import ca.mcgill.ecse321.projectgroup1.model.InUseSlot;
import ca.mcgill.ecse321.projectgroup1.model.LibraryItem;
import ca.mcgill.ecse321.projectgroup1.model.InUseSlot.Status;
@Service
public class ManageCartService {
    @Autowired
    private InUseSlotRepository inUseSlotRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private LibraryItemRepository libraryItemRepository;

   /**
    * @param clientId
    * @return all library items associated to a client
    */
    @Transactional
    public List<LibraryItem> getCartItems(int clientId) {
        String error = "";
        if(Boolean.FALSE.equals(clientRepository.existsById(clientId))) {
            error += "client does not exist";
        }
        // Verify if all parameters are correct
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        List<LibraryItem> cart = new ArrayList<LibraryItem>();
        for ( InUseSlot inUseSlot : inUseSlotRepository.findInUseSlotByClientId(clientId)) {
            cart.add(libraryItemRepository.findById(inUseSlot.getLibraryItem().getId()));// client -> inUseSlot -> libraryItem
        }
        return cart;
    }
    /** 
     * @param clientId
     * @return inUseSlots for a client
     */
    @Transactional
    public List<InUseSlot> getCartItemInUseSlot(int clientId) {
        String error = "";
        if(Boolean.FALSE.equals(clientRepository.existsById(clientId))) {
            error += "client does not exist";
        }
        // Verify if all parameters are correct
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        return toList(inUseSlotRepository.findInUseSlotByClientId(clientId));
    }
    /**
     * @param clientId
     * @param libraryItemId
     * @param currentDate
     * @return inUseSlot of reserved cart item
     */
    @Transactional
    public InUseSlot reserveCartItem(int clientId, int libraryItemId, Date currentDate) {
        String error = "";
        if(currentDate == null) {
            error += "Date is not set";
        }
        // Verify if all parameters are correct
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        Date predictedEndDate = Date.valueOf(currentDate.toLocalDate().plusDays(14));
        InUseSlot inUseSlot = findInUseSlot(clientId, libraryItemId);

        //need to check for overlap
        List<InUseSlot> inUseSlots = inUseSlotRepository.findInUseSlotByLibraryItemId(libraryItemId);
        if(inUseSlots.size() == 0) {
            error += "item is not in cart";
        }
        for(InUseSlot checkSlot : inUseSlots) {
            if(checkSlot.getStatus() != Status.InCart) {
                System.out.print(checkSlot.getStatus());
                if (inUseSlot.getStartDate().before(checkSlot.getEndDate()) && checkSlot.getStartDate().before(inUseSlot.getEndDate())) {
                    error += "overlap between attempted reservation and inUseSlots for libraryItem";
                }
            }
        }
        // Verify if all parameters are correct
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        inUseSlot.setStatus(Status.Reserved);
        inUseSlot.setStartDate(currentDate);
        inUseSlot.setEndDate(predictedEndDate);
        return inUseSlot;
    }
    /**
     * @param clientId
     * @param libraryItemId
     * @return renewed libraryItem inUseSlot
     * adds 14 days to the booked slot
     */
    @Transactional
    public InUseSlot renewCartItem(int clientId, int libraryItemId, Date currentDate) {
        String error = "";
        if(currentDate == null) {
            error += "Date is not set";
        }

        InUseSlot inUseSlot = findInUseSlot(clientId, libraryItemId);
        if (inUseSlot.getStatus() != Status.Booked) {
            error += "LibraryItem is currently not booked!";
        }
        // Verify if all parameters are correct
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        //need to check for overlap
        List<InUseSlot> inUseSlots = inUseSlotRepository.findInUseSlotByLibraryItemId(libraryItemId);

        for(InUseSlot checkSlot : inUseSlots) {
            if(inUseSlot.getClient().getId() != clientId && inUseSlot.getStatus() != Status.InCart &&
            Date.valueOf(inUseSlot.getStartDate().toLocalDate().plusDays(14)).before(checkSlot.getEndDate()) && 
            checkSlot.getStartDate().before(Date.valueOf(inUseSlot.getEndDate().toLocalDate().plusDays(14)))) {
                error += "overlap between attempted renewal and inUseSlots for libraryItem";
            }        
        }
        // Verify if all parameters are correct
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        inUseSlot.setEndDate(Date.valueOf(inUseSlot.getEndDate().toLocalDate().plusDays(14)));
        return inUseSlot;
    }
    /**
     * @param clientId
     * @param libraryItemId
     * @return removed inUseSlot
     */
    @Transactional
    public InUseSlot removeCartItem(int clientId, int libraryItemId) {
        InUseSlot inUseSlot = findInUseSlot(clientId, libraryItemId);
        inUseSlotRepository.deleteById(inUseSlot.getId());
        return inUseSlot;
    }
    /**
     * @param clientId
     * @param libraryItemId
     * @param currentDate
     * @return client whose book is returned
     */
    @Transactional
    public Client returnBorrowedItem(int libraryItemId, Date currentDate) {
        String error = "";
        if(currentDate == null) {
            error += "Date is not set";
        }
        if(Boolean.FALSE.equals(libraryItemRepository.existsById(libraryItemId))) {
            error += "libraryItem does not exist";
        }
        // Verify if all parameters are correct
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        InUseSlot inUseSlot = toList(inUseSlotRepository.findInUseSlotByLibraryItemId(libraryItemId)).get(0);//assume the most recent is the first in the list
        Client client = inUseSlot.getClient();
        if (inUseSlot.getStatus() != Status.InCart && currentDate.after(inUseSlot.getEndDate())) {
            client.setNumOfFlags(client.getNumOfFlag()); //incrementing flag count
        }
        inUseSlotRepository.deleteById(inUseSlot.getId());
        return client;
    }
    //checkoutCartItem, Librarian only
    @Transactional
    public InUseSlot checkoutCartItem(int clientId, int libraryItemId, Date currentDate) {
        String error = "";
        InUseSlot inUseSlot = findInUseSlot(clientId, libraryItemId);
        //if item is reserved by client, simply change its status, otherwise, check everything
        if (inUseSlot.getStatus() == Status.Reserved) {
            inUseSlot.setStatus(Status.Booked);
            inUseSlot.setStartDate(currentDate);
            inUseSlot.setEndDate(Date.valueOf(currentDate.toLocalDate().plusDays(14)));
            return inUseSlot;
        }
        //need to check for overlap
        List<InUseSlot> inUseSlots = inUseSlotRepository.findInUseSlotByLibraryItemId(libraryItemId);
        for(InUseSlot checkSlot : inUseSlots) {
            if(inUseSlot.getClient().getId() != clientId && inUseSlot.getStartDate().before(checkSlot.getEndDate()) && checkSlot.getStartDate().before(inUseSlot.getEndDate())) {
                error += "overlap between attempted booking and inUseSlots for libraryItem";
            }        
        }
        // Verify if all parameters are correct
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        inUseSlot.setStatus(Status.Booked);
        inUseSlot.setStartDate(currentDate);
        inUseSlot.setEndDate(Date.valueOf(currentDate.toLocalDate().plusDays(14)));
        return inUseSlot;
    }
    /**
     * @param clientId
     * @param libraryItemId
     * @return corresponding inUseSlot or null
     */
    private InUseSlot findInUseSlot(int clientId, int libraryItemId) {
        String error = "";
        if(Boolean.FALSE.equals(clientRepository.existsById(clientId))) {
            error += "client does not exist";
        }
        if(Boolean.FALSE.equals(libraryItemRepository.existsById(libraryItemId))) {
            error += "libraryItem does not exist";
        }
        // Verify if all parameters are correct
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        List<InUseSlot> inUseSlots = toList(inUseSlotRepository.findInUseSlotByClientId(clientId));
        for(InUseSlot inUseSlot: inUseSlots) {
            if(inUseSlot.getLibraryItem().getId() == libraryItemId) {
                return inUseSlot;
            }
        }
        return null;
    }
    //taken from tutorial code
    private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}