package ca.mcgill.ecse321.projectgroup1.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
import ca.mcgill.ecse321.projectgroup1.model.Client;
import ca.mcgill.ecse321.projectgroup1.model.InUseSlot;
import ca.mcgill.ecse321.projectgroup1.model.Library;
import ca.mcgill.ecse321.projectgroup1.model.LibraryItem;
import ca.mcgill.ecse321.projectgroup1.model.Newspaper;
import ca.mcgill.ecse321.projectgroup1.model.InUseSlot.Status;
import ca.mcgill.ecse321.projectgroup1.model.Movie;
import ca.mcgill.ecse321.projectgroup1.model.MusicAlbum;

@Service
public class ViewInventoryService {

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
     * Search item cases
     * 
     */

    // searching for a book in general
    @Transactional
    public List<Book> getAllBooks(Library library) {
        return bookRepository.findBookByLibraryName(library.getName());
    }

     //searching for books by their title 
     @Transactional
     public List<Book> getAllBooksByTitle(String title) {
        
        List <Book> books= new ArrayList<>();
        for(int i=0; i<libraryItemRepository.findLibraryItemsByTitle(title).size(); i++){

            if (libraryItemRepository.findLibraryItemsByTitle(title).get(i) instanceof Book){
 
                Book book= new Book();
                book= (Book) libraryItemRepository.findLibraryItemsByTitle(title).get(i);
                books.add(book);
            }
        
        }

        return books;
        
     }

    // searching for a movie in general
    @Transactional
    public List<Movie> getAllMovies(Library library) {
        return movieRepository.findMovieByLibraryName(library.getName());
    }

     //searching for movies by their title 
     @Transactional
     public List<Movie> getAllMoviesByTitle(String title) {
        
        List <Movie> movies= new ArrayList<>();
        List<LibraryItem> libraryItems = libraryItemRepository.findLibraryItemsByTitle(title);
        for(int i=0; libraryItems != null && i<libraryItems.size(); i++){
            if (libraryItems.get(i) instanceof Movie){
                Movie movie= new Movie();
                movie= (Movie) libraryItems.get(i);
                movies.add(movie);
            }
        
        }

        return movies;
        
     }

    // searching for a music album in general
    @Transactional
    public List<MusicAlbum> getAllMusicAlbums(Library library) {
        return musicAlbumRepository.findMusicAlbumByLibraryName(library.getName());
    }

     //searching for music albums by their title 
     @Transactional
     public List<MusicAlbum> getAllMusicAlbumsByTitle(String title) {
        
        List <MusicAlbum> albums= new ArrayList<>();
        for(int i=0; i<libraryItemRepository.findLibraryItemsByTitle(title).size(); i++){

            if (libraryItemRepository.findLibraryItemsByTitle(title).get(i) instanceof MusicAlbum){
 
                MusicAlbum album= new MusicAlbum();
                album= (MusicAlbum) libraryItemRepository.findLibraryItemsByTitle(title).get(i);
                albums.add(album);
            }
        
        }

        return albums;
        
     }



    // searching for a newspaper in general
    @Transactional
    public List<Newspaper> getAllNewspapers(Library library) {
        return newspaperRepository.findNewspaperByLibraryName(library.getName());
    }

    //searching for newspapers by their title 
    @Transactional
    public List<Newspaper> getAllNewspapersByTitle(String title) {
       
       List <Newspaper> newspapers= new ArrayList<>();
       for(int i=0; i<libraryItemRepository.findLibraryItemsByTitle(title).size(); i++){

           if (libraryItemRepository.findLibraryItemsByTitle(title).get(i) instanceof Newspaper){

               Newspaper newspaper= new Newspaper();
               newspaper= (Newspaper) libraryItemRepository.findLibraryItemsByTitle(title).get(i);
               newspapers.add(newspaper);
           }
       
       }

       return newspapers;
       
    }


    /**
     * View item case
     * 
     */

    // view library item from the library with its id
    @Transactional
    public LibraryItem getLibraryItem(int id) {

        String error = "";

        if (!libraryItemRepository.existsById(id)) {
            error += "This item does not exist.";
            return null;
        }

        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }

        LibraryItem libraryItem = libraryItemRepository.findById(id);
        return libraryItem;
    }

    /**
     * Add item to cart case
     * 
     */

    // add a library item to cart

    @Transactional
    public InUseSlot addItemToCart(int libraryItemId, int clientId, Date currentDate) {

        String error = "";
        if (Boolean.TRUE.equals(!libraryItemRepository.existsById(libraryItemId))
                || Boolean.TRUE.equals(!clientRepository.existsById(clientId))) {
            error += "The library item or the client was not found.";
        }
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        if (libraryItemRepository.findById(libraryItemId).getIsArchive()
                || newspaperRepository.existsById(libraryItemId)) {
            error = "Archives and newspapers can not be borrowed.";
        }
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        LibraryItem libraryItem = libraryItemRepository.findById(libraryItemId);
        Client client = clientRepository.findById(clientId);
        InUseSlot inUseSlot = new InUseSlot();
        inUseSlot.setStatus(Status.InCart);
        inUseSlot.setClient(client);
        inUseSlot.setLibraryItem(libraryItem);
        inUseSlot.setStartDate(null);
        inUseSlot.setEndDate(null);

        inUseSlotRepository.save(inUseSlot);

        return inUseSlot;
    }
}
