package ca.mcgill.ecse321.projectgroup1.controller;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup1.dto.BookDto;
import ca.mcgill.ecse321.projectgroup1.dto.LibraryItemDto;
import ca.mcgill.ecse321.projectgroup1.dto.MovieDto;
import ca.mcgill.ecse321.projectgroup1.dto.MusicAlbumDto;
import ca.mcgill.ecse321.projectgroup1.dto.NewspaperDto;
import ca.mcgill.ecse321.projectgroup1.model.Book;

import ca.mcgill.ecse321.projectgroup1.model.Library;
import ca.mcgill.ecse321.projectgroup1.model.LibraryItem;
import ca.mcgill.ecse321.projectgroup1.model.Movie;
import ca.mcgill.ecse321.projectgroup1.model.MusicAlbum;
import ca.mcgill.ecse321.projectgroup1.model.Newspaper;
import ca.mcgill.ecse321.projectgroup1.service.ManageInventoryService;
import ca.mcgill.ecse321.projectgroup1.service.ViewLibraryService;

@CrossOrigin(origins = "*")
@RestController

public class ManageInventoryController {

    @Autowired
    private ManageInventoryService service;
    
    @Autowired
    private ViewLibraryService viewService;

    /**
     * Add item cases
     * 
     */

    // Creates a Book instance given the appropriate arguments and returns
    // the Book instance
    @PostMapping(value = { "/book/library/{name}", "/book/library/{name}/" })
    public BookDto createBook(@PathVariable("name") String libraryName, @RequestParam("author") String author,
            @RequestParam("pageNumber") int pageNumber, @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("releasedate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") LocalDate releaseDate,
            @RequestParam Boolean isArchive)

            throws IllegalArgumentException {

        Library library = viewService.getLibrary(libraryName);

        Book book = service.createBook(library, author, pageNumber, title, description, Date.valueOf(releaseDate),
                isArchive);
        return ControllerConvert.convertToDto(book);
    }

    // Creates a Movie instance given the appropriate arguments and returns
    // the Movie instance
    @PostMapping(value = { "/movie/library/{name}", "/movie/library/{name}/" })
    public MovieDto createMovie(@PathVariable("name") String libraryName, @RequestParam("director") String director,
            @RequestParam("movieLength") int movieLength, @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("releasedate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") LocalDate releaseDate,
            @RequestParam Boolean isArchive) throws IllegalArgumentException {

        Library library = viewService.getLibrary(libraryName);

        Movie movie = service.createMovie(library, director, movieLength, title, description, Date.valueOf(releaseDate),
                isArchive);
        return ControllerConvert.convertToDto(movie);
    }

    // Creates a MusicAlbum instance given the appropriate arguments and returns
    // the MusicAlbum instance
    @PostMapping(value = { "/musicalbum/library/{name}", "/musicalbum/library/{name}/" })
    public MusicAlbumDto createMusicAlbum(@PathVariable("name") String libraryName,
            @RequestParam("artist") String artist, @RequestParam("genre") String genre,
            @RequestParam("title") String title, @RequestParam("description") String description,
            @RequestParam("releasedate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") LocalDate releaseDate,
            @RequestParam Boolean isArchive) throws IllegalArgumentException {

        Library library = viewService.getLibrary(libraryName);

        MusicAlbum album = service.createMusicAlbum(library, artist, genre, title, description,
                Date.valueOf(releaseDate), isArchive);
        return ControllerConvert.convertToDto(album);
    }

    // Creates a Newspaper instance given the appropriate arguments and returns
    // the Newspaper instance
    @PostMapping(value = { "/newspaper/library/{name}", "/newspaper/library/{name}/" })
    public NewspaperDto createNewspaper(@PathVariable("name") String libraryName,
            @RequestParam("publisher") String publisher, @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("releasedate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") LocalDate releaseDate,
            @RequestParam Boolean isArchive) throws IllegalArgumentException {

        Library library = viewService.getLibrary(libraryName);

        Newspaper newspaper = service.createNewspaper(library, publisher, title, description, Date.valueOf(releaseDate),
                isArchive);
        return ControllerConvert.convertToDto(newspaper);
    }

    /**
     * Remove item case
     * 
     */

    // Remove an item
    @DeleteMapping(value = { "/{id}/remove", "/{id}/remove/" })
    public LibraryItemDto removeLibraryItem(@PathVariable("id") int id)

            throws IllegalArgumentException {

        LibraryItem item = service.removeItem(id);
        return ControllerConvert.convertToDto(item);

    }

    /**
     * Edit item cases
     * 
     */

    // Edit Book
    @PutMapping(value = { "/book/{id}", "/book/{id}/" })
    public BookDto editBook(@PathVariable("id") int id, @RequestParam("libraryname") String lib,
            @RequestParam("author") String author, @RequestParam int pageNumber, @RequestParam("title") String title,
            @RequestParam("description") String description, @RequestParam(name = "releasedate") Date releaseDate,
            @RequestParam Boolean isArchive)

            throws IllegalArgumentException {

        Library library = viewService.getLibrary(lib);

        Book bookModified = service.editBook(library, id, author, pageNumber, title, description, releaseDate,
                isArchive);

        return ControllerConvert.convertToDto(bookModified);

    }

    // Edit Movie
    @PutMapping(value = { "/movie/{id}", "/movie/{id}/" })
    public MovieDto editMovie(@PathVariable("id") int id, @RequestParam("libraryname") String lib,
            @RequestParam("director") String director, @RequestParam int movieLength,
            @RequestParam("title") String title, @RequestParam("description") String description,
            @RequestParam(name = "releasedate") Date releaseDate, @RequestParam Boolean isArchive)

            throws IllegalArgumentException {

        Library library = viewService.getLibrary(lib);

        Movie movieModified = service.editMovie(library, id, director, movieLength, title, description, releaseDate,
                isArchive);

        return ControllerConvert.convertToDto(movieModified);

    }

    // Edit Music Album
    @PutMapping(value = { "/musicalbum/{id}", "/musicalbum/{id}/" })
    public MusicAlbumDto editMusicAlbum(@PathVariable("id") int id, @RequestParam("libraryname") String lib,
            @RequestParam("artist") String artist, @RequestParam("genre") String genre,
            @RequestParam("title") String title, @RequestParam("description") String description,
            @RequestParam(name = "releasedate") Date releaseDate, @RequestParam Boolean isArchive)

            throws IllegalArgumentException {

        Library library = viewService.getLibrary(lib);

        MusicAlbum albumModified = service.editMusicAlbum(library, id, artist, genre, title, description, releaseDate,
                isArchive);

        return ControllerConvert.convertToDto(albumModified);

    }

    // Edit Newspaper
    @PutMapping(value = { "/newspaper/{id}", "/newspaper/{id}/" })
    public NewspaperDto editNewspaper(@PathVariable("id") int id, @RequestParam("libraryname") String lib,
            @RequestParam("publisher") String publisher, @RequestParam("title") String title,
            @RequestParam("description") String description, @RequestParam(name = "releasedate") Date releaseDate,
            @RequestParam Boolean isArchive)

            throws IllegalArgumentException {

        Library library = viewService.getLibrary(lib);

        Newspaper newspaperModified = service.editNewspaper(library, id, publisher, title, description, releaseDate,
                isArchive);

        return ControllerConvert.convertToDto(newspaperModified);

    }
}
