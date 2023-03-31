package ca.mcgill.ecse321.projectgroup1.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup1.dao.BookRepository;
import ca.mcgill.ecse321.projectgroup1.dao.ClientRepository;
import ca.mcgill.ecse321.projectgroup1.dao.InUseSlotRepository;
import ca.mcgill.ecse321.projectgroup1.dao.LibraryItemRepository;
import ca.mcgill.ecse321.projectgroup1.dao.LibraryRepository;
import ca.mcgill.ecse321.projectgroup1.dao.MovieRepository;
import ca.mcgill.ecse321.projectgroup1.dao.MusicAlbumRepository;
import ca.mcgill.ecse321.projectgroup1.dao.NewspaperRepository;

import ca.mcgill.ecse321.projectgroup1.model.Book;
import ca.mcgill.ecse321.projectgroup1.model.Library;
import ca.mcgill.ecse321.projectgroup1.model.LibraryItem;
import ca.mcgill.ecse321.projectgroup1.model.Newspaper;
import ca.mcgill.ecse321.projectgroup1.model.Movie;
import ca.mcgill.ecse321.projectgroup1.model.MusicAlbum;

@Service
public class ManageInventoryService {

    @Autowired
    InUseSlotRepository inUseSlotRepository;
    
    @Autowired
    LibraryRepository libraryRepository;

    @Autowired
    LibraryItemRepository libraryItemRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired 
    BookRepository bookRepository;

    @Autowired 
    MovieRepository movieRepository;

    @Autowired 
    MusicAlbumRepository musicAlbumRepository;

    @Autowired 
    NewspaperRepository newspaperRepository;


    /**
     * Required helper get methods
     * 
     */

//Get library item from the library with its id
@Transactional
public LibraryItem getLibraryItem(int id){
    
    String error = "";
    
    if(libraryItemRepository.existsById(id)==false){
        
        error += "This item does not exist.";

        }

    error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }

        LibraryItem libraryItem= libraryItemRepository.findById(id);
        return libraryItem;
    }


    /**
     * Add item cases
     * 
     */

    //Creates an instance of Book
    @Transactional
    public Book createBook(Library library, String author, int pageNumber, String title, String description, Date releaseDate, Boolean isArchive) {
    String error = "";
    
    if (library == null) {
        error += "Library needs to be selected to create the business hours.";
    } else if (!libraryRepository.existsByName(library.getName())) {
        error += "Library does not exist.";
    }

    if(author==null){
        error += "Book's author name cannot be empty.";
    }

    if(pageNumber == 0){ 
        error += "Book's page number cannot be 0.";
    }

    if(title ==null){
        error += "Book's title cannot be empty.";
    }

    if(description==null){
        error += "Book's description cannot be empty.";
    }

    if(releaseDate == null){
        error += "Book's release date cannot be empty.";
    }

    if(isArchive==null){
        error += "Book must be either an archive or not an archive.";
    }

    error = error.trim();
    if (error.length() > 0) {
        throw new IllegalArgumentException(error);
    }
   
    
   Book book = new Book();
   book.setLibrary(library);
   book.setAuthor(author);
   book.setPageNumber(pageNumber);
   book.setTitle(title);
   book.setDescription(description);
   book.setReleaseDate(releaseDate);
   book.setIsArchive(isArchive);
   bookRepository.save(book);
   return book;
}

//Creates an instance of Movie
@Transactional
public Movie createMovie(Library library, String director, int movieLength, String title, String description, Date releaseDate, Boolean isArchive) {
String error = "";

if (library == null) {
    error += "Library needs to be selected to create the business hours.";
} else if (!libraryRepository.existsByName(library.getName())) {
    error += "Library does not exist.";
}

if(director==null){
    error += "Movie's director name cannot be empty.";
}

if(movieLength == 0){ 
    error += "Movie's length in minutes cannot be 0.";
}

if(title ==null){
    error += "Movie's title cannot be empty.";
}

if(description==null){
    error += "Movie's description cannot be empty.";
}

if(releaseDate == null){
    error += "Movie's release date cannot be empty.";
}

if(isArchive==null){
    error += "Movie must be either an archive or not an archive.";
}

error = error.trim();
if (error.length() > 0) {
    throw new IllegalArgumentException(error);
}


Movie movie = new Movie();
movie.setLibrary(library);
movie.setDirector(director);
movie.setMovieLength(movieLength);
movie.setTitle(title);
movie.setDescription(description);
movie.setReleaseDate(releaseDate);
movie.setIsArchive(isArchive);
movieRepository.save(movie);
return movie;
}

//Creates an instance of a Music Album
@Transactional
public MusicAlbum createMusicAlbum(Library library, String artist, String genre, String title, String description, Date releaseDate, Boolean isArchive) {
String error = "";

if (library == null) {
    error += "Library needs to be selected to create the business hours.";
} else if (!libraryRepository.existsByName(library.getName())) {
    error += "Library does not exist.";
}

if(artist==null){
    error += "Music album's artist name cannot be empty.";
}

if(genre==null){ 
    error += "Music album's genre type cannot be empty.";
}

if(title ==null){
    error += "Music album's title cannot be empty.";
}

if(description==null){
    error += "Music album's description cannot be empty.";
}

if(releaseDate == null){
    error += "Music album's release date cannot be empty.";
}

if(isArchive==null){
    error += "Music album must be either an archive or not an archive.";
}

error = error.trim();
if (error.length() > 0) {
    throw new IllegalArgumentException(error);
}


MusicAlbum album = new MusicAlbum();
album.setLibrary(library);
album.setArtist(artist);
album.setGenre(genre);
album.setTitle(title);
album.setDescription(description);
album.setReleaseDate(releaseDate);
album.setIsArchive(isArchive);
musicAlbumRepository.save(album);
return album;
}

//Creates an instance of Newspaper
@Transactional
public Newspaper createNewspaper(Library library, String publisher, String title, String description, Date releaseDate, Boolean isArchive){
   String error="";

   if (library == null) {
    error += "Library needs to be selected to create the business hours.";
} else if (!libraryRepository.existsByName(library.getName())) {
    error += "Library does not exist.";
}

if(library==null){
    error += "Book's author name cannot be empty.";
}

if(title ==null){
    error += "Book's title cannot be empty.";
}

if(description==null){
    error += "Book's description cannot be empty.";
}

if(releaseDate == null){
    error += "Book's release date cannot be empty.";
}

if(isArchive==null){
    error += "Book must be either an archive or not an archive.";
}

error = error.trim();
if (error.length() > 0) {
    throw new IllegalArgumentException(error);
}


   Newspaper newspaper = new Newspaper();
   newspaper.setLibrary(library);
   newspaper.setPublisher(publisher);
   newspaper.setTitle(title);
   newspaper.setDescription(description);
   newspaper.setReleaseDate(releaseDate);
   newspaper.setIsArchive(isArchive);
   newspaperRepository.save(newspaper);
   return newspaper;
}
    
/**
 * Remove item case
 * 
 */

//remove an item from the library using its id
@Transactional
public LibraryItem removeItem(int id){
    
    String error = "";
    //String success= "Item was successfully removed";
   
    if(libraryItemRepository.existsById(id)==false){
        
        error += "This item does not exist.";
        return null;
        }

    error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }

        LibraryItem item= libraryItemRepository.findById(id);
        libraryItemRepository.deleteById(id);
        return item;
    }
    
/**
 * Edit item cases
 * 
 */

//Service method to edit a book
@Transactional
public Book editBook(Library library, int id, String author, int pageNumber, String title, String description, Date releaseDate, Boolean isArchive) { 
    
    if (!bookRepository.existsById(id)) {
        throw new IllegalArgumentException("Book does not exist!");
    }

    Book book = bookRepository.findById(id);

    String error = "";

        if (library == null) {
            error += "Library must be selected to edit a client account!";    
        }

        if (author == null || author.trim().length() == 0) {
            error += "Author name cannot be empty!";
        }

        if (pageNumber ==0) {
            error += "The amount of page number cannot be empty!";
        }

        if (title == null || title.trim().length() == 0) {
            error += "Title cannot be empty!";
        }

        if (description == null || description.trim().length() == 0) {
            error += "Description cannot be empty!";
        }

        if (releaseDate == null ) {
            error += "The realease date cannot be empty!";
        }

        if (isArchive == null) {
            error += "Archive status cannot be empty!";
        }

        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
         
        book.setLibrary(library);
        book.setAuthor(author);
        book.setPageNumber(pageNumber);
        book.setTitle(title);
        book.setDescription(description);
        book.setReleaseDate(releaseDate);
        book.setIsArchive(isArchive);

        bookRepository.save(book);

    return book;

    }
 
//Service method to edit a movie
@Transactional
public Movie editMovie(Library library, int id, String director, int movieLength, String title, String description, Date releaseDate, Boolean isArchive) { 
    
    if (!movieRepository.existsById(id)) {
        throw new IllegalArgumentException("Movie does not exist!");
    }

    Movie movie = movieRepository.findById(id);

    String error = "";

        if (library == null) {
            error += "Library must be selected to edit a client account!";    
        }

        if (director == null || director.trim().length() == 0) {
            error += "Director name cannot be empty!";
        }

        if (movieLength ==0) {
            error += "Genre type cannot be empty!";
        }

        if (title == null || title.trim().length() == 0) {
            error += "Title cannot be empty!";
        }

        if (description == null || description.trim().length() == 0) {
            error += "Description cannot be empty!";
        }

        if (releaseDate == null ) {
            error += "The realease date cannot be empty!";
        }

        if (isArchive == null) {
            error += "Archive status cannot be empty!";
        }

        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        
        movie.setLibrary(library);
        movie.setDirector(director);
        movie.setMovieLength(movieLength);;
        movie.setTitle(title);
        movie.setDescription(description);
        movie.setReleaseDate(releaseDate);
        movie.setIsArchive(isArchive);

        movieRepository.save(movie);

    return movie;

    }

//Service method to edit a music album
@Transactional
public MusicAlbum editMusicAlbum(Library library, int id, String artist, String genre, String title, String description, Date releaseDate, Boolean isArchive) { 
    
    if (!musicAlbumRepository.existsById(id)) {
        throw new IllegalArgumentException("Music Album does not exist!");
    }

    MusicAlbum album = musicAlbumRepository.findById(id);

    String error = "";

        if (library == null) {
            error += "Library must be selected to edit a client account!";    
        }

        if (artist == null || artist.trim().length() == 0) {
            error += "artist name cannot be empty!";
        }

        if (genre == null || genre.trim().length() == 0) {
            error += "Genre type cannot be empty!";
        }

        if (title == null || title.trim().length() == 0) {
            error += "Title cannot be empty!";
        }

        if (description == null || description.trim().length() == 0) {
            error += "Description cannot be empty!";
        }

        if (releaseDate == null ) {
            error += "The realease date cannot be empty!";
        }

        if (isArchive == null) {
            error += "Archive status cannot be empty!";
        }

        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        
        album.setLibrary(library);
        album.setArtist(artist);
        album.setGenre(genre);
        album.setTitle(title);
        album.setDescription(description);
        album.setReleaseDate(releaseDate);
        album.setIsArchive(isArchive);

        musicAlbumRepository.save(album);

    return album;

    }

//Service method to edit a newspaper
@Transactional
public Newspaper editNewspaper(Library library, int id, String publisher, String title, String description, Date releaseDate, Boolean isArchive) { 
    
    if (!newspaperRepository.existsById(id)) {
        throw new IllegalArgumentException("Newspaper does not exist!");
    }

    Newspaper newspaper = newspaperRepository.findById(id);

    String error = "";

        if (library == null) {
            error += "Library must be selected to edit a client account!";    
        }

        if (publisher == null || publisher.trim().length() == 0) {
            error += "Publisher name cannot be empty!";
        }

        if (title == null || title.trim().length() == 0) {
            error += "Title cannot be empty!";
        }

        if (description == null || description.trim().length() == 0) {
            error += "Description cannot be empty!";
        }

        if (releaseDate == null ) {
            error += "The realease date cannot be empty!";
        }

        if (isArchive == null) {
            error += "Archive status cannot be empty!";
        }

        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
         
        newspaper.setLibrary(library);
        newspaper.setPublisher(publisher);
        newspaper.setTitle(title);
        newspaper.setDescription(description);
        newspaper.setReleaseDate(releaseDate);
        newspaper.setIsArchive(isArchive);

        newspaperRepository.save(newspaper);

    return newspaper;

    }
}
