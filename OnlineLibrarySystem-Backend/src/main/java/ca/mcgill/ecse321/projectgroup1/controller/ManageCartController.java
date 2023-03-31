package ca.mcgill.ecse321.projectgroup1.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup1.dao.BusinessHoursRepository;
import ca.mcgill.ecse321.projectgroup1.dao.HolidaySlotRepository;
import ca.mcgill.ecse321.projectgroup1.dao.LibraryRepository;
import ca.mcgill.ecse321.projectgroup1.dto.ClientDto;
import ca.mcgill.ecse321.projectgroup1.dto.InUseSlotDto;
import ca.mcgill.ecse321.projectgroup1.dto.LibraryItemDto;
import ca.mcgill.ecse321.projectgroup1.model.InUseSlot;
import ca.mcgill.ecse321.projectgroup1.service.ManageCartService;
import ca.mcgill.ecse321.projectgroup1.model.Client;

@CrossOrigin(origins = "*")
@RestController
public class ManageCartController {
    @Autowired
    LibraryRepository libraryRepository;
    @Autowired
    BusinessHoursRepository businessHoursRepository;
    @Autowired
    HolidaySlotRepository holidaySlotRepository;
    @Autowired
    private ManageCartService service;

    @GetMapping(value = { "/client/cart/{id}", "/client/cart/{id}/" })
	public List<LibraryItemDto> getCartItems(
        @PathVariable("id") int clientId) {
		return service.getCartItems(clientId).stream().map(ControllerConvert::convertToDto).toList();
	}
    @GetMapping(value = { "/client/cartInUseSlot/{id}", "/client/cartInUseSlot/{id}/" })
    public Object getCartItemInUseSlot(
            @PathVariable("id") int clientId) {
        try{
            return service.getCartItemInUseSlot(clientId).stream().map(ControllerConvert::convertToDto).toList();
        }catch(IllegalArgumentException e) {
            return e.getMessage();
        }
    }
    @PostMapping(value = { "/client/cart/reserve", "/client/cart/reserve/" })
    public InUseSlotDto reserveCartItem(
            @RequestParam(name = "clientid") int clientId,
            @RequestParam(name = "itemid") int itemId,
            @RequestParam Date currentDate) throws IllegalArgumentException {
            InUseSlot inUseSlot = service.reserveCartItem(clientId, itemId, currentDate);
            return ControllerConvert.convertToDto(inUseSlot);
    }

    @PostMapping(value = { "/renew", "/renew/" })
	public InUseSlotDto renewCartItem(
        @RequestParam(name = "clientid") int clientId,
		@RequestParam(name = "itemid") int itemId,
        @RequestParam(name = "currentDate") Date currentDate
        ) throws IllegalArgumentException {
		// int clientId = cDto.getId();
        // int itemId = iDto.getId();
        InUseSlot inUseSlot = service.renewCartItem(clientId, itemId, currentDate);
        return ControllerConvert.convertToDto(inUseSlot);
	}

    @DeleteMapping(value = { "/remove", "/remove/" })
	public InUseSlotDto removeCartItem(
        @RequestParam(name = "clientid") int clientId,
		@RequestParam(name = "itemid") int itemId) throws IllegalArgumentException {
		// int clientId = cDto.getId();
        // int itemId = iDto.getId();
        InUseSlot inUseSlot = service.removeCartItem(clientId, itemId);
        return ControllerConvert.convertToDto(inUseSlot);
	}

    @PostMapping(value = { "/client/cart/return", "/client/cart/return/" })
    public ClientDto returnBorrowedItem(
            @RequestParam(name = "itemid") int itemId,
            @RequestParam(name = "currentDate") Date currentDate
    ) throws IllegalArgumentException {
            Client client = service.returnBorrowedItem(itemId, currentDate);
            return ControllerConvert.convertToDto(client);
    }

    @PostMapping(value = { "/client/cart/checkout", "/client/cart/checkout/" })
    public InUseSlotDto checkoutCartItem(
            @RequestParam(name = "clientid") int clientId,
            @RequestParam(name = "itemid") int itemId,
            @RequestParam(name = "currentDate") Date currentDate
    ) throws IllegalArgumentException {
            InUseSlot inUseSlot = service.checkoutCartItem(clientId, itemId, currentDate);
            return ControllerConvert.convertToDto(inUseSlot);
    }
}