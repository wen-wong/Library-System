package ca.mcgill.ecse321.projectgroup1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.projectgroup1.dao.BookRepository;
import ca.mcgill.ecse321.projectgroup1.dao.ClientRepository;
import ca.mcgill.ecse321.projectgroup1.dao.InUseSlotRepository;
import ca.mcgill.ecse321.projectgroup1.dao.LibraryItemRepository;
import ca.mcgill.ecse321.projectgroup1.dao.LibraryRepository;
import ca.mcgill.ecse321.projectgroup1.dao.MovieRepository;
import ca.mcgill.ecse321.projectgroup1.dao.MusicAlbumRepository;
import ca.mcgill.ecse321.projectgroup1.dao.NewspaperRepository;
import ca.mcgill.ecse321.projectgroup1.model.Book;
import ca.mcgill.ecse321.projectgroup1.model.InUseSlot;
import ca.mcgill.ecse321.projectgroup1.model.Library;
import ca.mcgill.ecse321.projectgroup1.model.LibraryItem;
import ca.mcgill.ecse321.projectgroup1.model.Movie;
import ca.mcgill.ecse321.projectgroup1.model.MusicAlbum;
import ca.mcgill.ecse321.projectgroup1.model.Newspaper;


@ExtendWith(MockitoExtension.class)
public class TestViewInventoryService {

    @Mock
    private InUseSlotRepository inUseSlotDao;
    

    @Mock
    private LibraryRepository libraryDao;

    @Mock
    private LibraryItemRepository libraryItemDao;

    @Mock
    private BookRepository bookDao;

    @Mock
    private MovieRepository movieDao;

    @Mock
    private MusicAlbumRepository musicAlbumDao;

    @Mock
    private NewspaperRepository newspaperDao;

    @Mock
    private ClientRepository clientDao;

    @InjectMocks
    private ViewInventoryService inventoryService;



     private static final String LIBRARY_KEY = "TestLibrary";
     private static final int BOOK1_ID = 1;
     private static final int BOOK2_ID = 2;
     private static final String BOOK_TITLE = "Oh to be a duck";
     private static final String MOVIE_TITLE = "something deeper";
     private static final String MUSICALBUM_TITLE = "funny thingzz";
     private static final String NEWSPAPER_TITLE = "thats deep";
     private static final int CLIENT_KEY = 1;
     private static final int NEWSPAPER_ID = 2;
     private static final int MUSICALBUM_ID = 3;
     private static final int MOVIE_ID = 4;
     private static final int OTHER_MOVIE_ID = 9;
     private static final int NON_EXISTENT = 0;



    @BeforeEach
    public void setMockOutput() {

        // MOCK DATABASE
        
        Library library = new Library();
        library.setName(LIBRARY_KEY);

        Newspaper newspaper = new Newspaper();
        newspaper.setId(NEWSPAPER_ID);
        newspaper.setTitle(NEWSPAPER_TITLE);
        newspaper.setLibrary(library);

        List<Newspaper> newspapers = new ArrayList<>();
        newspapers.add(newspaper);

        MusicAlbum musicAlbum = new MusicAlbum();
        musicAlbum.setId(MUSICALBUM_ID);
        musicAlbum.setLibrary(library);
        musicAlbum.setTitle(MUSICALBUM_TITLE);

        List<MusicAlbum> albums = new ArrayList<>();
        albums.add(musicAlbum);

        lenient().when(libraryDao.findByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(LIBRARY_KEY)) {
                return library;
            } else {
                return null;
            }
        });

        lenient().when(bookDao.findBookByLibraryName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(LIBRARY_KEY)) {
                Book book1 = new Book();
                book1.setId(BOOK1_ID);
                book1.setTitle(BOOK_TITLE);
                book1.setLibrary(library);

                Book book2 = new Book();
                book1.setId(BOOK2_ID);
                book1.setTitle(BOOK_TITLE);
                book1.setLibrary(library);

                List<Book> books = new ArrayList<>();
                books.add(book1);
                books.add(book2);

                return books;
            } else {
                return null;
            }
        });

        lenient().when(bookDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(BOOK1_ID)) {

                Book book1 = new Book();
                book1.setId(BOOK1_ID);
                book1.setTitle(BOOK_TITLE);
                book1.setLibrary(library);

                return book1;
            } else {
                return null;
            }
        });

        lenient().when(newspaperDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(NEWSPAPER_ID)) {
                return newspaper;
            } else {
                return null;
            }
        });

        lenient().when(newspaperDao.findNewspaperByLibraryName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(LIBRARY_KEY)) {
                return newspapers;
            } else {
                return null;
            }
        });

        lenient().when(musicAlbumDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(MUSICALBUM_ID)) {
                return musicAlbum;
            } else {
                return null;
            }
        });

        lenient().when(musicAlbumDao.findMusicAlbumByLibraryName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(LIBRARY_KEY)) {
                return albums;
            } else {
                return null;
            }
        });

        lenient().when(movieDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(MOVIE_ID)) {
                Movie movie = new Movie();
                movie.setIsArchive(false);
                movie.setId(MOVIE_ID);
                movie.setLibrary(library);
                return movie;
            } else {
                return null;
            }
        });

        lenient().when(movieDao.findMovieByLibraryName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(LIBRARY_KEY)) {
                List<Movie> movies = new ArrayList<>();
                movies.add(movieDao.findById(MOVIE_ID));
                return movies;
            } else {
                return null;
            }
        });

        lenient().when(libraryItemDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(MOVIE_ID)) {
                return movieDao.findById(MOVIE_ID);
            } else {
                return null;
            }
        });
        lenient().when(newspaperDao.existsById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(NEWSPAPER_ID)) {
                return true;
            } else {
                return false;
            }
        });
        lenient().when(libraryItemDao.existsById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(MOVIE_ID)) {
                return true;
            } else {
                return false;
            }
        });
        lenient().when(clientDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(CLIENT_KEY)) {
                return movieDao.findById(CLIENT_KEY);
            } else {
                return null;
            }
        });
        lenient().when(clientDao.existsById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(CLIENT_KEY)) {
                return true;
            } else {
                return false;
            }
        });

        lenient().when(libraryItemDao.findLibraryItemsByTitle(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(MOVIE_TITLE)) {

                Movie movie1 = new Movie();
                movie1.setIsArchive(false);
                movie1.setId(MOVIE_ID);
                movie1.setLibrary(library);
                movie1.setTitle(MOVIE_TITLE);

                Movie movie2 = new Movie();
                movie2.setIsArchive(false);
                movie2.setId(OTHER_MOVIE_ID);
                movie2.setLibrary(library);
                movie2.setTitle(MOVIE_TITLE);

                List<Movie> movies = new ArrayList<>();
                movies.add(movie1);
                movies.add(movie2);
                return movies;
        
            } 
            else if (invocation.getArgument(0).equals(BOOK_TITLE)) {

                Book book1 = new Book();
                book1.setId(BOOK1_ID);
                book1.setTitle(BOOK_TITLE);
                book1.setIsArchive(false);
                book1.setLibrary(library);

                Book book2 = new Book();
                book2.setId(BOOK2_ID);
                book2.setTitle(BOOK_TITLE);
                book2.setIsArchive(false);
                book2.setLibrary(library);

                List<Book> books = new ArrayList<>();
                books.add(book1);
                books.add(book2);

                return books;
        
            }
            else if (invocation.getArgument(0).equals(MUSICALBUM_TITLE)) {

                MusicAlbum album = new MusicAlbum();
                album.setId(MUSICALBUM_ID);
                album.setTitle(MUSICALBUM_TITLE);
                album.setLibrary(library);

                List<MusicAlbum> albumss = new ArrayList<>();
                albumss.add(album);

                return albumss;
        
            } 
            else if (invocation.getArgument(0).equals(NEWSPAPER_TITLE)) {
            
                Newspaper newspaper1 = new Newspaper();
                newspaper1.setId(NEWSPAPER_ID);
                newspaper1.setTitle(NEWSPAPER_TITLE);
                newspaper1.setLibrary(library);

                List<Newspaper> newspaperss = new ArrayList<>();
                newspaperss.add(newspaper1);

                return newspaperss;

            }

            else {
                return null;
            }
        });

        // lenient().when(libraryDao.save(any(Library.class))).thenAnswer(returnParameterAsAnswer);

    }

    @Test
    public void testGetAllBooks() {

        Library library = new Library();
        library.setName(LIBRARY_KEY);

        List<Book> allBooks = null;

        try {
            // TODO
            allBooks = inventoryService.getAllBooks(library);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertEquals(2, allBooks.size()); 
    }

    @Test
    public void testGetAllNewspapers() {

        Library library = new Library();
        library.setName(LIBRARY_KEY);

        List<Newspaper> allNewspapers = null;

        try {
            // TODO
            allNewspapers = inventoryService.getAllNewspapers(library);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertEquals(1, allNewspapers.size());
    }

    @Test
    public void testGetAllMusicAlbums() {

        Library library = new Library();
        library.setName(LIBRARY_KEY);

        List<MusicAlbum> allMusicAlbums = null;

        try {
            // TODO
            allMusicAlbums = inventoryService.getAllMusicAlbums(library);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertEquals(1, allMusicAlbums.size());
    }

    @Test
    public void testGetAllMovies() {

        Library library = new Library();
        library.setName(LIBRARY_KEY);

        List<Movie> allMovies = null;

        try {
            allMovies = inventoryService.getAllMovies(library);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertEquals(1, allMovies.size());
    }

    @Test
	public void testGetLibraryItem() {
		assertEquals(MOVIE_ID, inventoryService.getLibraryItem(MOVIE_ID).getId()); 
	}

    @Test
	public void testGetLibraryItemFail() {
		assertNull(inventoryService.getLibraryItem(NON_EXISTENT));
	}

    @Test
	public void testGetBooksByTitleSize() {
		assertEquals(2, inventoryService.getAllBooksByTitle(BOOK_TITLE).size()); 
	}

    @Test
	public void testGetBooksByTitle1() {
		assertEquals(BOOK_TITLE, inventoryService.getAllBooksByTitle(BOOK_TITLE).get(0).getTitle()); 
	}


    @Test
	public void testGetMoviesByTitleSize() {
		assertEquals(2, inventoryService.getAllMoviesByTitle(MOVIE_TITLE).size()); 
	}

    @Test
	public void testGetMoviesByTitle() {
		assertEquals(MOVIE_TITLE, inventoryService.getAllMoviesByTitle(MOVIE_TITLE).get(0).getTitle()); 
	}

    @Test
	public void testGetMoviesByTitle2() {
		assertEquals(MOVIE_TITLE, inventoryService.getAllMoviesByTitle(MOVIE_TITLE).get(1).getTitle()); 
	}

    @Test
	public void testGetMusicAlbumsByTitle() {
		assertEquals(MUSICALBUM_TITLE, inventoryService.getAllMusicAlbumsByTitle(MUSICALBUM_TITLE).get(0).getTitle()); 
	}

    @Test
	public void testGetNewspapersByTitle() {
		assertEquals(NEWSPAPER_TITLE, inventoryService.getAllNewspapersByTitle(NEWSPAPER_TITLE).get(0).getTitle()); 
	}

    @Test
    public void testAddItemToCart() {
        String error = null;
        InUseSlot inUseSlot = null;
        Date currentDate = Date.valueOf("2021-11-14");
        try {
            inUseSlot = inventoryService.addItemToCart(MOVIE_ID, CLIENT_KEY, currentDate);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals(MOVIE_ID, inUseSlot.getLibraryItem().getId());
    }

}
