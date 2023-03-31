package ca.mcgill.ecse321.projectgroup1.dto;

//imports needed
import java.sql.Date;


/**
 * MovieDto - Data Transfer Objects for the Movie class
 * 
 * @param library - library that has this movie
 * @param title - title of the movie
 * @param description - description of the movie
 * @param releaseDate - release date of the movie
 * @param isArchive - whether the movie is archived or not
 * @param id - the id of the movie
 * @param director - the director of the movie
 * @param movielength - the movie length of the movie
 * 
 * @author Emilia
 */

public class MovieDto extends LibraryItemDto {

    // extra attributes
    private String director;
    private int movieLength;

    // The constructor for the MovieDto

    public MovieDto(LibraryDto library, String title, String description,
    Date releaseDate, Boolean isArchive, int id, String director, int movieLength) {
        
        super(library, title, description, releaseDate, isArchive, id);
        this.director = director;
        this.movieLength = movieLength;
    }

    // The getter and setter methods of the MovieDto

    public String getDirector() {
        return this.director;
    }

    public int getMovieLength() {
        return this.movieLength;
    }
}
