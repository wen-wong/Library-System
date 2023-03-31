package ca.mcgill.ecse321.projectgroup1.dto;

//imports
import java.sql.Date;

/**
 * MusicAlbumDto - Data Transfer Objects for the MusicAlbum class
 * 
 * @param library - library that has this musicAlbum
 * @param title - title of the musicAlbum
 * @param description - description of the m
 * @param releaseDate - release date of the movie
 * @param isArchive - whether the movie is archived or not
 * @param id - the id of the movie
 * @param artist - the director of the movie
 * @param genre - the movie length of the movie
 * 
 * @author Emilia
 */

public class MusicAlbumDto extends LibraryItemDto {

    // extra attributes for MusicAlbum
    private String artist;
    private String genre;

    // The constructor for the MovieDto

    public MusicAlbumDto (LibraryDto library, String title, String description,
    Date releaseDate, Boolean isArchive, int id, String artist, String genre) {
        
        super(library, title, description, releaseDate, isArchive, id);
        this.artist = artist;
        this.genre = genre;
    }

    // The getter and setter methods of the MovieDto    

    public String getArtist() {
        return this.artist;
    }

    public String getGenre() {
        return this.genre;
    }
}
