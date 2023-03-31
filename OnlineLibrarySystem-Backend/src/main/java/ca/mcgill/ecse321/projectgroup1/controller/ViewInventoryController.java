package ca.mcgill.ecse321.projectgroup1.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.projectgroup1.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup1.model.Book;

import ca.mcgill.ecse321.projectgroup1.model.InUseSlot;
import ca.mcgill.ecse321.projectgroup1.model.Library;
import ca.mcgill.ecse321.projectgroup1.model.LibraryItem;
import ca.mcgill.ecse321.projectgroup1.model.Movie;
import ca.mcgill.ecse321.projectgroup1.model.MusicAlbum;
import ca.mcgill.ecse321.projectgroup1.model.Newspaper;
import ca.mcgill.ecse321.projectgroup1.service.ViewLibraryService;
import ca.mcgill.ecse321.projectgroup1.service.ViewInventoryService;

@CrossOrigin(origins = "*")
@RestController
public class ViewInventoryController {

    @Autowired
    private ViewInventoryService service;

    @Autowired
    private ViewLibraryService LibraryService;

    /**
     * Search item cases
     * 
     */

    // Returns the list of requested Book instances using the associated
    // library name
    @GetMapping(value = { "/book/library/{name}", "/book/library/{name}/" })
    public List<BookDto> getBooks(@PathVariable("name") String libraryName) throws IllegalArgumentException {

        List<BookDto> bookDtos = new ArrayList<>();
        Library library = LibraryService.getLibrary(libraryName);

        for (Book books : service.getAllBooks(library)) {
            bookDtos.add(ControllerConvert.convertToDto(books));
        }

        return bookDtos;
    }

    // Returns the list of requested Book instances using the entered title
     
    @GetMapping(value = { "/book/title", "/book/title/" })
    public List<BookDto> getBooksByTitle(@RequestParam(name = "title") String title) throws IllegalArgumentException {

        List<BookDto> bookDtos = new ArrayList<>();

        for (Book books : service.getAllBooksByTitle(title)) {
            bookDtos.add(ControllerConvert.convertToDto(books));
        }

        return bookDtos;
    }

    // Returns the list of requested Movie instances using the associated
    // library name
    @GetMapping(value = { "/movie/library/{name}", "/movie/library/{name}/" })
    public List<MovieDto> getMovies(@PathVariable("name") String libraryName) throws IllegalArgumentException {

        List<MovieDto> movieDtos = new ArrayList<>();
        Library library = LibraryService.getLibrary(libraryName);

        for (Movie movies : service.getAllMovies(library)) {
            movieDtos.add(ControllerConvert.convertToDto(movies));
        }

        return movieDtos;
    }

     // Returns the list of requested Movie instances using the entered title
     
     @GetMapping(value = { "/movie/title", "/movie/title/" })
     public List<MovieDto> getMoviesByTitle(@RequestParam(name = "title") String title) throws IllegalArgumentException {
 
        List<MovieDto> movieDtos = new ArrayList<>();
 
         for (Movie movies : service.getAllMoviesByTitle(title)) {
            movieDtos.add(ControllerConvert.convertToDto(movies));
         }
 
         return movieDtos;
     }

    // Returns the list of requested MusicAlbum instances using the associated
    // library name
    @GetMapping(value = { "/musicalbum/library/{name}", "/musicalbum/library/{name}/" })
    public List<MusicAlbumDto> getMusicAlbums(@PathVariable("name") String libraryName)
            throws IllegalArgumentException {

        List<MusicAlbumDto> musicAlbumDtos = new ArrayList<>();
        Library library = LibraryService.getLibrary(libraryName);

        for (MusicAlbum albums : service.getAllMusicAlbums(library)) {
            musicAlbumDtos.add(ControllerConvert.convertToDto(albums));
        }

        return musicAlbumDtos;
    }

     // Returns the list of requested Music Album instances using the entered title
     
     @GetMapping(value = { "/musicalbum/title", "/musicalbum/title/" })
     public List<MusicAlbumDto> getMusicAlbumsByTitle(@RequestParam(name = "title") String title) throws IllegalArgumentException {
 
        List<MusicAlbumDto> musicAlbumDtos = new ArrayList<>();
 
        for (MusicAlbum albums : service.getAllMusicAlbumsByTitle(title)) {
            musicAlbumDtos.add(ControllerConvert.convertToDto(albums));
         }
 
         return musicAlbumDtos;
     }

    // Returns the list of requested Newspaper instances using the associated
    // library name
    @GetMapping(value = { "/newspaper/library/{name}", "/newspaper/library/{name}/" })
    public List<NewspaperDto> getNewspapers(@PathVariable("name") String libraryName) throws IllegalArgumentException {

        List<NewspaperDto> newspaperDtos = new ArrayList<>();
        Library library = LibraryService.getLibrary(libraryName);

        for (Newspaper newspapers : service.getAllNewspapers(library)) {
            newspaperDtos.add(ControllerConvert.convertToDto(newspapers));
        }

        return newspaperDtos;
    }

    
     // Returns the list of requested Newspaper instances using the entered title
     
     @GetMapping(value = { "/newspaper/title", "/newspaper/title/" })
     public List<NewspaperDto> getNewspapersByTitle(@RequestParam(name = "title") String title) throws IllegalArgumentException {
 
        List<NewspaperDto> newspaperDtos = new ArrayList<>();
 
        for (Newspaper newspapers : service.getAllNewspapersByTitle(title)) {
            newspaperDtos.add(ControllerConvert.convertToDto(newspapers));
         }
 
         return newspaperDtos;
     }

    /**
     * View item case
     * 
     */

    @GetMapping(value = { "/libraryitem/{id}", "/libraryitem/{id}/" })
    public LibraryItemDto getLibraryItem(@PathVariable("id") int id)

            throws IllegalArgumentException {
        LibraryItem item = service.getLibraryItem(id);
        return ControllerConvert.convertToDto(item);
    }

    /**
     * Add item to cart case
     *
     * @return
     */

    // Add item to cart
    @PostMapping(value = { "/add", "/add/" })
    public Object addCartItem(@RequestParam(name = "clientid") int clientId,
                                    @RequestParam(name = "itemid") int itemId,
                                    @RequestParam("currentdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") LocalDate currentDate)

            throws IllegalArgumentException {
        try {
            InUseSlot inUseSlot = service.addItemToCart(itemId, clientId, Date.valueOf(currentDate));
            return ControllerConvert.convertToDto(inUseSlot);
        }
        catch(IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}
