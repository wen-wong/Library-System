package ca.mcgill.ecse321.projectgroup1.controller;

import ca.mcgill.ecse321.projectgroup1.dto.BookDto;
import ca.mcgill.ecse321.projectgroup1.dto.BusinessHoursDto;
import ca.mcgill.ecse321.projectgroup1.dto.ClientDto;
import ca.mcgill.ecse321.projectgroup1.dto.EmployeeDto;
import ca.mcgill.ecse321.projectgroup1.dto.EventSlotDto;
import ca.mcgill.ecse321.projectgroup1.dto.HolidaySlotDto;
import ca.mcgill.ecse321.projectgroup1.dto.InUseSlotDto;
import ca.mcgill.ecse321.projectgroup1.dto.LibraryDto;
import ca.mcgill.ecse321.projectgroup1.dto.LibraryItemDto;
import ca.mcgill.ecse321.projectgroup1.dto.MovieDto;
import ca.mcgill.ecse321.projectgroup1.dto.MusicAlbumDto;
import ca.mcgill.ecse321.projectgroup1.dto.NewspaperDto;
import ca.mcgill.ecse321.projectgroup1.dto.WorkSlotDto;
import ca.mcgill.ecse321.projectgroup1.dto.InUseSlotDto.StatusDto;
import ca.mcgill.ecse321.projectgroup1.model.Book;
import ca.mcgill.ecse321.projectgroup1.model.BusinessHours;
import ca.mcgill.ecse321.projectgroup1.model.Client;
import ca.mcgill.ecse321.projectgroup1.model.Employee;
import ca.mcgill.ecse321.projectgroup1.model.EventSlot;
import ca.mcgill.ecse321.projectgroup1.model.HolidaySlot;
import ca.mcgill.ecse321.projectgroup1.model.InUseSlot;
import ca.mcgill.ecse321.projectgroup1.model.Library;
import ca.mcgill.ecse321.projectgroup1.model.LibraryItem;
import ca.mcgill.ecse321.projectgroup1.model.Movie;
import ca.mcgill.ecse321.projectgroup1.model.MusicAlbum;
import ca.mcgill.ecse321.projectgroup1.model.Newspaper;
import ca.mcgill.ecse321.projectgroup1.model.WorkSlot;
import ca.mcgill.ecse321.projectgroup1.model.InUseSlot.Status;

/**
 * ControllerConvert - A class that has all convert to dto and to domain objects
 * that will be used in the Controllers
 * 
 * @author Brandon
 */
public class ControllerConvert {

    /**
     * Converts Library instance to LibraryDto
     * 
     * @param library - Library instance
     * @return LibraryDto instance
     * @author Brandon
     */
    protected static LibraryDto convertToDto(Library library) {
        if (library == null) {
            throw new IllegalArgumentException("The library does not exist.");
        }

        return new LibraryDto(library.getName(), library.getAddress(), library.getPhoneNumber(), library.getEmail());
    }

    /**
     * Converts BusinessHours to BusinessHoursDto
     * 
     * @param businessHours - BusinessHours instance
     * @return BusinessHoursDto instance
     * @throws IllegalArgumentException
     * @author Brandon
     */
    protected static BusinessHoursDto convertToDto(BusinessHours businessHours) throws IllegalArgumentException {

        if (businessHours == null) {
            throw new IllegalArgumentException("The library does not exist.");
        }

        LibraryDto libraryDto = convertToDto(businessHours.getLibrary());
        BusinessHoursDto businessHoursDto = new BusinessHoursDto(libraryDto, businessHours.getDayOfWeek(),
                businessHours.getStartTime(), businessHours.getEndTime());
        return businessHoursDto;
    }

    /**
     * Converts HolidaySlot to HolidaySlotDto
     * 
     * @param holidaySlot - HolidaySlot instance
     * @return HolidaySlotDto instance
     * @throws IllegalArgumentException
     * @author Brandon
     */
    protected static HolidaySlotDto convertToDto(HolidaySlot holidaySlot) {
        if (holidaySlot == null) {
            throw new IllegalArgumentException("The library does not exist.");
        }

        LibraryDto libraryDto = convertToDto(holidaySlot.getLibrary());
        return new HolidaySlotDto(libraryDto, holidaySlot.getId(), holidaySlot.getStartDate(), holidaySlot.getEndDate(),
                holidaySlot.getStartTime(), holidaySlot.getEndTime());
    }

    /**
     * Converts EventSlot to EventSlotDto
     * 
     * @param eventSlot - EventSlot instance
     * @return EventSlotDto instance
     * @author Philippe
     */
    protected static EventSlotDto convertToDto(EventSlot eventSlot) {
        if (eventSlot == null) {
            throw new IllegalArgumentException("Event does not exist!");
        }

        ClientDto clientDto = convertToDto(eventSlot.getClient());
        return new EventSlotDto(clientDto, eventSlot.getId(), eventSlot.getStartDate(), eventSlot.getEndDate(),
                eventSlot.getStartTime(), eventSlot.getEndTime(), eventSlot.getDescription());
    }

    /**
     * Converts WorkSlot to WorkSlotDto
     * 
     * @param workSlot - WorkSlot instance
     * @return WorkSlotDto instance
     * @author Chloe
     */
    protected static WorkSlotDto convertToDto(WorkSlot workSlot) {
        if (workSlot == null) {
            throw new IllegalArgumentException("workslot does not exist.");
        }

        EmployeeDto employeeDto = convertToDto(workSlot.getEmployee());
        return new WorkSlotDto(employeeDto, workSlot.getStartDate(), workSlot.getEndDate(), workSlot.getStartTime(),
                workSlot.getEndTime());
    }

    /**
     * Converts Client to ClientDto
     * 
     * @param client - Client instance
     * @return ClientDto instance
     * @author Philippe
     */
    protected static ClientDto convertToDto(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client does not exist!");
        }

        return new ClientDto(convertToDto(client.getLibrary()), client.getId(), client.getName(), client.getPassword(),
                client.getAddress(), client.getPhoneNumber(), client.getEmail(), client.getIsResident());
    }

    /**
     * Converts Employee to EmployeeDto
     * 
     * @param employee - Employee instance
     * @return EmployeeDto instance
     * @author Philippe
     */
    protected static EmployeeDto convertToDto(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee does not exist!");
        }

        return new EmployeeDto(convertToDto(employee.getLibrary()), employee.getId(), employee.getName(),
                employee.getPassword(), employee.getAddress(), employee.getPhoneNumber(), employee.getEmail(),
                convertToDto(employee.getTypeOfEmployee()));
    }

    /**
     * Converts TypeOfEmployee to TypeOfEmployee from EmployeeDto
     * 
     * @param typeOfEmployee
     * @return
     */
    protected static EmployeeDto.TypeOfEmployee convertToDto(Employee.TypeOfEmployee typeOfEmployee) {
        if (typeOfEmployee == Employee.TypeOfEmployee.Librarian)
            return EmployeeDto.TypeOfEmployee.Librarian;

        else
            return EmployeeDto.TypeOfEmployee.HeadLibrarian;
    }

    /**
     * Converts Book to BookDto
     * 
     * @param book - Book instance
     * @return BookDto instance
     * @author Anika
     */
    protected static BookDto convertToDto(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book does not exist.");
        }

        LibraryDto libraryDto = convertToDto(book.getLibrary());
        return new BookDto(libraryDto, book.getAuthor(), book.getPageNumber(), book.getTitle(), book.getDescription(),
                book.getReleaseDate(), book.getIsArchive(), book.getId());

    }

    /**
     * Converts Movie to MovieDto
     * 
     * @param movie - Movie instance
     * @return MovieDto instance
     * @author Anika
     */
    protected static MovieDto convertToDto(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Book does not exist.");
        }

        LibraryDto libraryDto = convertToDto(movie.getLibrary());
        return new MovieDto(libraryDto, movie.getTitle(), movie.getDescription(), movie.getReleaseDate(),
                movie.getIsArchive(), movie.getId(), movie.getDirector(), movie.getMovieLength());

    }

    /**
     * Converts MusicAlbum to MusicAlbumDto
     * 
     * @param album - MusicAlbum instance
     * @return MusicAlbumDto instance
     * @author Anika
     */
    protected static MusicAlbumDto convertToDto(MusicAlbum album) {
        if (album == null) {
            throw new IllegalArgumentException("Book does not exist.");
        }

        LibraryDto libraryDto = convertToDto(album.getLibrary());
        return new MusicAlbumDto(libraryDto, album.getTitle(), album.getDescription(), album.getReleaseDate(),
                album.getIsArchive(), album.getId(), album.getArtist(), album.getGenre());
    }

    /**
     * Converts Newspaper to NewspaperDto
     * 
     * @param newspaper - Newspaper instance
     * @return NewspaperDto instance
     * @author Anika
     */
    protected static NewspaperDto convertToDto(Newspaper newspaper) {
        if (newspaper == null) {
            throw new IllegalArgumentException("Book does not exist.");
        }

        LibraryDto libraryDto = convertToDto(newspaper.getLibrary());
        return new NewspaperDto(libraryDto, newspaper.getPublisher(), newspaper.getTitle(), newspaper.getDescription(),
                newspaper.getReleaseDate(), newspaper.getIsArchive(), newspaper.getId());

    }

    /**
     * Converts LibraryItem to LibraryItemDto
     * 
     * @param libraryItem - LibraryItem instance
     * @return LibraryItemDto instance
     * @author Philippe
     */
    protected static LibraryItemDto convertToDto(LibraryItem libraryItem) {
        if (libraryItem == null) {
            throw new IllegalArgumentException("There is no such libraryItem");
        }
        if (libraryItem instanceof Book) {
            return convertToDto((Book) libraryItem);
        } else if (libraryItem instanceof Newspaper) {
            return convertToDto((Newspaper) libraryItem);
        } else if (libraryItem instanceof Movie) {
            return convertToDto((Movie) libraryItem);
        } else {
            return convertToDto((MusicAlbum) libraryItem);
        }
    }

    /**
     * Converts InUseSlot to InUseSlotDto
     * 
     * @param inUseSlot - InUseSlot instance
     * @return InUseSlotDto instance
     * @author Philippe
     */
    protected static InUseSlotDto convertToDto(InUseSlot inUseSlot) {
        if (inUseSlot == null) {
            throw new IllegalArgumentException("There is no such inUseSlot!");
        }
        LibraryItemDto libraryItemDto = convertToDto(inUseSlot.getLibraryItem());
        ClientDto clientDto = convertToDto(inUseSlot.getClient());
        InUseSlotDto.StatusDto inUseSlotDto = convertToDto(inUseSlot.getStatus());
        return new InUseSlotDto(libraryItemDto, clientDto, inUseSlot.getId(), inUseSlot.getStartDate(),
                inUseSlot.getEndDate(), inUseSlotDto);
    }

    /**
     * Converts Status to Status from InUseSlotDto
     * 
     * @param status
     * @return
     */
    protected static InUseSlotDto.StatusDto convertToDto(InUseSlot.Status status) {
        if (status == null) {
            throw new IllegalArgumentException("There is no such inUseSlot!");
        } else if (status.equals(Status.Booked)) {
            return StatusDto.Booked;
        } else if (status.equals(Status.InCart)) {
            return StatusDto.InCart;
        } else {
            return StatusDto.Reserved;
        }
    }

    /**
     * Converts TypeOfEmployee to domain object
     * 
     * @param typeOfEmployee
     * @return
     */
    protected static Employee.TypeOfEmployee convertToDomainObject(EmployeeDto.TypeOfEmployee t) {
        if (t == EmployeeDto.TypeOfEmployee.Librarian)
            return Employee.TypeOfEmployee.Librarian;
        else
            return Employee.TypeOfEmployee.HeadLibrarian;
    }
}
