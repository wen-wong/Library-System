package ca.mcgill.ecse321.projectgroup1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.time.LocalDate;

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
import ca.mcgill.ecse321.projectgroup1.model.Library;
import ca.mcgill.ecse321.projectgroup1.model.LibraryItem;
import ca.mcgill.ecse321.projectgroup1.model.Movie;
import ca.mcgill.ecse321.projectgroup1.model.MusicAlbum;
import ca.mcgill.ecse321.projectgroup1.model.Newspaper;

@ExtendWith(MockitoExtension.class)
public class TestManageInventoryService {

    @Mock
    private InUseSlotRepository inUseSlotDao;

    @Mock
    private LibraryRepository libraryDao;

    @Mock
    private LibraryItemRepository libraryItemDao;

    @Mock
    private ClientRepository clientDao;

    @Mock
    private BookRepository bookDao;

    @Mock
    private MovieRepository movieDao;

    @Mock
    private MusicAlbumRepository musicAlbumDao;

    @Mock
    private NewspaperRepository newspaperDao;

    @InjectMocks
    private ManageInventoryService manageInventoryService;

    @InjectMocks
    private ViewInventoryService inventoryService;
    
    @InjectMocks
    private ViewLibraryService viewLibraryService;

    private static final String LIBRARY_KEY = "TestLibrary";
    private static final String LIBRARY_ADDRESS = "Library Street";
    private static final String LIBRARY_PHONENUMBER = "514-123-1234";
    private static final String LIBRARY_EMAIL = "library@mail.ca";

    private static final int BOOK_ID = 1233;
    private static final String BOOK_AUTHOR = "Roman";
    private static final int BOOK_PAGE_NUMBER = 89;
    private static final String BOOK_TITLE = "Oh to be a duck";
    private static final String BOOK_DESCRIPTION = "stuff";
    private static final LocalDate BOOK_RELEASE_DATE = LocalDate.of(2001, 12, 25);
    private static final Boolean BOOK_ISARCHIVE = Boolean.FALSE;

    private static final int MOVIE_ID = 7833;
    private static final String MOVIE_DIRECTOR = "Me";
    private static final int MOVIE_LENGTH = 35;
    private static final String MOVIE_TITLE = "testinggg";
    private static final String MOVIE_DESCRIPTION = "stuff";
    private static final LocalDate MOVIE_RELEASE_DATE = LocalDate.of(2020, 8, 25);
    private static final Boolean MOVIE_ISARCHIVE = Boolean.FALSE;

    private static final int ALBUM_ID = 1003;
    private static final String ALBUM_ARTIST = "JLO";
    private static final String ALBUM_GENRE = "classical";
    private static final String ALBUM_TITLE = "success";
    private static final String ALBUM_DESCRIPTION = "stuff";
    private static final LocalDate ALBUM_RELEASE_DATE = LocalDate.of(2010, 8, 9);
    private static final Boolean ALBUM_ISARCHIVE = Boolean.FALSE;

    private static final int NEWSPAPER_ID = 1003;
    private static final String NEWSPAPER_PUBLISHER = "Someone";
    private static final String NEWSPAPER_TITLE = "idk anymore";
    private static final String NEWSPAPER_DESCRIPTION = "More stuff";
    private static final LocalDate NEWSPAPER_RELEASE_DATE = LocalDate.of(2019, 8, 9);
    private static final Boolean NEWSPAPER_ISARCHIVE = Boolean.FALSE;

    @BeforeEach
    public void setMockOutput() {

        // ALSO DO EXIST BY ID, AND SET EVERY ATTRIBUTE

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
        
        lenient().when(libraryItemDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(BOOK_ID)) {
            	Book book = new Book();
                book.setId(BOOK_ID);
                return (LibraryItem) book;
            } else {
                return null;
            }
        });

        lenient().when(bookDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(BOOK_ID)) {
                Book book = new Book();
                book.setLibrary(libraryDao.findByName(LIBRARY_KEY));
                book.setId(BOOK_ID);
                book.setAuthor(BOOK_AUTHOR);
                book.setPageNumber(BOOK_PAGE_NUMBER);
                book.setTitle(BOOK_TITLE);
                book.setDescription(BOOK_DESCRIPTION);
                book.setReleaseDate(Date.valueOf(BOOK_RELEASE_DATE));
                book.setIsArchive(BOOK_ISARCHIVE);
                return book;
            } else {
                return null;
            }
        });

        lenient().when(movieDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(MOVIE_ID)) {
                Movie movie = new Movie();
                movie.setLibrary(libraryDao.findByName(LIBRARY_KEY));
                movie.setId(MOVIE_ID);
                movie.setDirector(MOVIE_DIRECTOR);

                movie.setMovieLength(MOVIE_LENGTH);

                movie.setTitle(MOVIE_TITLE);
                movie.setDescription(MOVIE_DESCRIPTION);
                movie.setReleaseDate(Date.valueOf(MOVIE_RELEASE_DATE));
                movie.setIsArchive(MOVIE_ISARCHIVE);
                return movie;
            } else {
                return null;
            }
        });

        lenient().when(musicAlbumDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(ALBUM_ID)) {
                MusicAlbum album = new MusicAlbum();
                album.setLibrary(libraryDao.findByName(LIBRARY_KEY));
                album.setId(ALBUM_ID);
                album.setArtist(ALBUM_ARTIST);
                album.setGenre(ALBUM_GENRE);
                album.setTitle(ALBUM_TITLE);
                album.setDescription(ALBUM_DESCRIPTION);
                album.setReleaseDate(Date.valueOf(ALBUM_RELEASE_DATE));
                album.setIsArchive(ALBUM_ISARCHIVE);
                return album;
            } else {
                return null;
            }
        });

        lenient().when(newspaperDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(NEWSPAPER_ID)) {
                Newspaper newspaper = new Newspaper();
                newspaper.setLibrary(libraryDao.findByName(LIBRARY_KEY));
                newspaper.setId(NEWSPAPER_ID);
                newspaper.setPublisher(NEWSPAPER_PUBLISHER);
                newspaper.setTitle(NEWSPAPER_TITLE);
                newspaper.setDescription(NEWSPAPER_DESCRIPTION);
                newspaper.setReleaseDate(Date.valueOf(NEWSPAPER_RELEASE_DATE));
                newspaper.setIsArchive(NEWSPAPER_ISARCHIVE);
                return newspaper;
            } else {
                return null;
            }
        });

        lenient().when(bookDao.existsById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(BOOK_ID)) {
                return true;
            } else {
                return null;
            }
        });

        lenient().when(movieDao.existsById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(MOVIE_ID)) {
                return true;
            } else {
                return null;
            }
        });

        lenient().when(musicAlbumDao.existsById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(ALBUM_ID)) {
                return true;
            } else {
                return null;
            }
        });

        lenient().when(newspaperDao.existsById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(NEWSPAPER_ID)) {
                return true;
            } else {
                return null;
            }
        });
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };

        lenient().when(libraryDao.save(any(Library.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(bookDao.save(any(Book.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(movieDao.save(any(Movie.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(musicAlbumDao.save(any(MusicAlbum.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(newspaperDao.save(any(Newspaper.class))).thenAnswer(returnParameterAsAnswer);
    }

    // create a valid book
    @Test
    public void testCreateBook() {

        Library library = new Library();
        library.setName(LIBRARY_KEY);

        String author = "testAuthor";
        int pageNumber = 37;
        String title = "testBoook";
        String description = "Library Street";
        LocalDate releaseDate = LocalDate.of(2011, 03, 06);
        Boolean isArchive = false;

        Book book = null;

        try {
            book = manageInventoryService.createBook(library, author, pageNumber, title, description,
                    Date.valueOf(releaseDate), isArchive);
        } catch (IllegalArgumentException error) {
            fail();
        }

        assertNotNull(library);
        assertEquals(author, book.getAuthor());
        assertEquals(pageNumber, book.getPageNumber());
        assertEquals(title, book.getTitle());
        assertEquals(description, book.getDescription());
        assertEquals(Date.valueOf(releaseDate), book.getReleaseDate());
        assertEquals(isArchive, book.getIsArchive());
    }
    
    // create book with empty author
    @Test
    public void testCreateBookEmptyAuthor() {

        Library library = new Library();
        library.setName(LIBRARY_KEY);

        String author = null;
        int pageNumber = 37;
        String title = "testBoook";
        String description = "Library Street";
        LocalDate releaseDate = LocalDate.of(2011, 03, 06);
        Boolean isArchive = false;

        String error = null;

        try {
            Book book = manageInventoryService.createBook(library, author, pageNumber, title, description,
                    Date.valueOf(releaseDate), isArchive);
        } catch (IllegalArgumentException errMessage) {
            error = errMessage.getMessage();
        }

        assertTrue(error.contains("Book's author name cannot be empty."));
    }
    
    // create book with empty page number
    @Test
    public void testCreateBookEmptyPageNumber() {

        Library library = new Library();
        library.setName(LIBRARY_KEY);

        String author = "testAuthor";
        int pageNumber = 0;
        String title = "testBoook";
        String description = "Library Street";
        LocalDate releaseDate = LocalDate.of(2011, 03, 06);
        Boolean isArchive = false;

        String error = null;

        try {
            Book book = manageInventoryService.createBook(library, author, pageNumber, title, description,
                    Date.valueOf(releaseDate), isArchive);
        } catch (IllegalArgumentException errMessage) {
            error = errMessage.getMessage();
        }

        assertTrue(error.contains("Book's page number cannot be 0."));
    }
    
    // create book with empty description
    @Test
    public void testCreateBookEmptyDescription() {

        Library library = new Library();
        library.setName(LIBRARY_KEY);

        String author = "testAuthor";
        int pageNumber = 37;
        String title = "testBoook";
        String description = null;
        LocalDate releaseDate = LocalDate.of(2011, 03, 06);
        Boolean isArchive = false;

        String error = null;

        try {
            Book book = manageInventoryService.createBook(library, author, pageNumber, title, description,
                    Date.valueOf(releaseDate), isArchive);
        } catch (IllegalArgumentException errMessage) {
            error = errMessage.getMessage();
        }

        assertTrue(error.contains("Book's description cannot be empty."));
    }
    
    // create book with empty release date
    @Test
    public void testCreateBookEmptyReleaseDate() {

        Library library = new Library();
        library.setName(LIBRARY_KEY);

        String author = "testAuthor";
        int pageNumber = 37;
        String title = "testBoook";
        String description = "Library Street";
        Date releaseDate = null;
        Boolean isArchive = false;

        String error = null;

        try {
            Book book = manageInventoryService.createBook(library, author, pageNumber, title, description,
                    releaseDate, isArchive);
        } catch (IllegalArgumentException errMessage) {
            error = errMessage.getMessage();
        }

        assertTrue(error.contains("Book's release date cannot be empty."));
    }
    
 // create book with empty description
    @Test
    public void testCreateBookEmptyIsArchived() {

        Library library = new Library();
        library.setName(LIBRARY_KEY);

        String author = "testAuthor";
        int pageNumber = 37;
        String title = "testBoook";
        String description = null;
        LocalDate releaseDate = LocalDate.of(2011, 03, 06);
        Boolean isArchive = false;

        String error = null;

        try {
            Book book = manageInventoryService.createBook(library, author, pageNumber, title, description,
                    Date.valueOf(releaseDate), isArchive);
        } catch (IllegalArgumentException errMessage) {
            error = errMessage.getMessage();
        }

        assertTrue(error.contains("Book's description cannot be empty."));
    }
    
    // create book with empty archived
    @Test
    public void testCreateBookEmptyArchived() {

        Library library = new Library();
        library.setName(LIBRARY_KEY);

        String author = "testAuthor";
        int pageNumber = 37;
        String title = "Library Street";
        String description = "Library Street";
        LocalDate releaseDate = LocalDate.of(2011, 03, 06);
        Boolean isArchive = null;

        String error = null;

        try {
            Book book = manageInventoryService.createBook(library, author, pageNumber, title, description,
                    Date.valueOf(releaseDate), isArchive);
        } catch (IllegalArgumentException errMessage) {
            error = errMessage.getMessage();
        }

        assertTrue(error.contains("Book must be either an archive or not an archive."));
    }
    
    // edit a valid book
    @Test
    public void testEditBook() {

        Library library = new Library();
        library.setName(LIBRARY_KEY);

        String author = "testAuthorAgain";
        int pageNumber = 97;
        String title = "testBoook";
        String description = "Library Street";
        LocalDate releaseDate = LocalDate.of(2011, 03, 06);
        Boolean isArchive = false;

        Book book = null;

        try {
            book = manageInventoryService.editBook(library, BOOK_ID, author, pageNumber, title, description,
                    Date.valueOf(releaseDate), isArchive);
        } catch (IllegalArgumentException error) {
            fail();
        }

        assertNotNull(library);
        assertEquals(author, book.getAuthor());
        assertEquals(pageNumber, book.getPageNumber());
        assertEquals(title, book.getTitle());
        assertEquals(description, book.getDescription());
        assertEquals(Date.valueOf(releaseDate), book.getReleaseDate());
        assertEquals(isArchive, book.getIsArchive());
    }

    // create a valid movie
    @Test
    public void testCreateMovie() {

        Library library = new Library();
        library.setName(LIBRARY_KEY);

        String director = "testAuthor";
        int movieLength = 90;
        String title = "testMovie";
        String description = "stuff";
        LocalDate releaseDate = LocalDate.of(2019, 03, 06);
        Boolean isArchive = false;

        Movie movie = null;

        try {
            movie = manageInventoryService.createMovie(library, director, movieLength, title, description,
                    Date.valueOf(releaseDate), isArchive);
        } catch (IllegalArgumentException error) {
            fail();
        }

        assertNotNull(library);
        assertEquals(director, movie.getDirector());
        assertEquals(movieLength, movie.getMovieLength());
        assertEquals(title, movie.getTitle());
        assertEquals(description, movie.getDescription());
        assertEquals(Date.valueOf(releaseDate), movie.getReleaseDate());
        assertEquals(isArchive, movie.getIsArchive());
    }
    
    // edit a valid book
    @Test
    public void testEditMovie() {
    	Library library = new Library();
        library.setName(LIBRARY_KEY);

        String director = "testAuthor";
        int movieLength = 90;
        String title = "testMovie";
        String description = "stuff";
        LocalDate releaseDate = LocalDate.of(2019, 03, 06);
        Boolean isArchive = false;

        Movie movie = null;
        String error = null;

        try {
            movie = manageInventoryService.editMovie(library, MOVIE_ID, director, movieLength, title, description,
                    Date.valueOf(releaseDate), isArchive);
        } catch (IllegalArgumentException errMessage) {
            error = errMessage.getMessage();
        }
        
        assertNotNull(library);
        assertEquals(director, movie.getDirector());
        assertEquals(movieLength, movie.getMovieLength());
        assertEquals(title, movie.getTitle());
        assertEquals(description, movie.getDescription());
        assertEquals(Date.valueOf(releaseDate), movie.getReleaseDate());
        assertEquals(isArchive, movie.getIsArchive());
    }

    // edit a valid music album
    @Test
    public void testEditMusicAlbum() {

        Library library = new Library();
        library.setName(LIBRARY_KEY);

        String artist = "testArtist";
        String genre = "idk";
        String title = "testAlbum";
        String description = "stuff";
        LocalDate releaseDate = LocalDate.of(2015, 03, 06);
        Boolean isArchive = false;

        MusicAlbum album = null;

        try {
            album = manageInventoryService.editMusicAlbum(library, ALBUM_ID, artist, genre, title, description,
                    Date.valueOf(releaseDate), isArchive);
        } catch (IllegalArgumentException error) {
            fail();
        }

        assertNotNull(library);
        assertEquals(artist, album.getArtist());
        assertEquals(genre, album.getGenre());
        assertEquals(title, album.getTitle());
        assertEquals(description, album.getDescription());
        assertEquals(Date.valueOf(releaseDate), album.getReleaseDate());
        assertEquals(isArchive, album.getIsArchive());
    }
    
 // create a valid music album
    @Test
    public void testCreateMusicAlbum() {

        Library library = new Library();
        library.setName(LIBRARY_KEY);

        String artist = "testArtist";
        String genre = "idk";
        String title = "testAlbum";
        String description = "stuff";
        LocalDate releaseDate = LocalDate.of(2015, 03, 06);
        Boolean isArchive = false;

        MusicAlbum album = null;

        try {
            album = manageInventoryService.createMusicAlbum(library, artist, genre, title, description,
                    Date.valueOf(releaseDate), isArchive);
        } catch (IllegalArgumentException error) {
            fail();
        }

        assertNotNull(library);
        assertEquals(artist, album.getArtist());
        assertEquals(genre, album.getGenre());
        assertEquals(title, album.getTitle());
        assertEquals(description, album.getDescription());
        assertEquals(Date.valueOf(releaseDate), album.getReleaseDate());
        assertEquals(isArchive, album.getIsArchive());
    }

    // create a valid newspaper
    @Test
    public void testCreateNewspaper() {

        Library library = new Library();
        library.setName(LIBRARY_KEY);

        String publisher = "testPublisher";
        String title = "testPaper";
        String description = "other stuff";
        LocalDate releaseDate = LocalDate.of(2015, 03, 06);
        Boolean isArchive = false;

        Newspaper newspaper = null;

        try {
            newspaper = manageInventoryService.createNewspaper(library, publisher, title, description,
                    Date.valueOf(releaseDate), isArchive);
        } catch (IllegalArgumentException error) {
            fail();
        }

        assertNotNull(library);
        assertEquals(publisher, newspaper.getPublisher());
        assertEquals(title, newspaper.getTitle());
        assertEquals(description, newspaper.getDescription());
        assertEquals(Date.valueOf(releaseDate), newspaper.getReleaseDate());
        assertEquals(isArchive, newspaper.getIsArchive());
    }
    
    // edit a valid newspaper
    @Test
    public void testEditNewspaper() {

        Library library = new Library();
        library.setName(LIBRARY_KEY);

        String publisher = "testPublisher";
        String title = "testPaper";
        String description = "other stuff";
        LocalDate releaseDate = LocalDate.of(2015, 03, 06);
        Boolean isArchive = false;

        Newspaper newspaper = null;

        try {
            newspaper = manageInventoryService.editNewspaper(library, NEWSPAPER_ID, publisher, title, description,
                    Date.valueOf(releaseDate), isArchive);
        } catch (IllegalArgumentException error) {
            fail();
        }

        assertNotNull(library);
        assertEquals(publisher, newspaper.getPublisher());
        assertEquals(title, newspaper.getTitle());
        assertEquals(description, newspaper.getDescription());
        assertEquals(Date.valueOf(releaseDate), newspaper.getReleaseDate());
        assertEquals(isArchive, newspaper.getIsArchive());
    }
    
    @Test
    public void testGetLibraryItemFailed() {
    	
    	Book book = null;
    	String error = null;
    	
    	try {
    		book = (Book) manageInventoryService.getLibraryItem(BOOK_ID);
    	} catch (IllegalArgumentException errMessage) {
    		error = errMessage.getMessage();
    	}
    	
    	assertNull(book);
    	assertTrue(error.contains("This item does not exist."));
    }

}
