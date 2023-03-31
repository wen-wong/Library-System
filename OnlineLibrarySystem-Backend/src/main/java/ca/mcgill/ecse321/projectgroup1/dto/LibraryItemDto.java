package ca.mcgill.ecse321.projectgroup1.dto;

import java.sql.Date;

/**
 * 
 * @param library - library that has this movie
 * @param title - title of the movie
 * @param description - description of the movie
 * @param releaseDate - release date of the movie
 * @param isArchive - whether the movie is archived or not
 * @param id - the id of the movie
 * 
 * @author Anika
 */

public abstract class LibraryItemDto {

    //Attributes
    private LibraryDto library;
    private String title;
    private String description;
    private Date releaseDate;
    private Boolean isArchive;
    private int id;


    //Constructor for LibraryItemDto
    public LibraryItemDto(LibraryDto library, String title, String description, Date releaseDate, Boolean isArchive, int id) {
        this.library = library;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.isArchive= isArchive;
        this.id = id;

    }

    // Getter and Setter Methods for associations

    public LibraryDto getLibrary() {
        return this.library;
    }

    public void setLibrary(LibraryDto library) { 
        this.library = library;
    }

     // Getter methods 
    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public Date getReleaseDate() {
        return this.releaseDate;
    }

    public Boolean getIsArchive() {
        return this.isArchive;
    }

    public int getId() {
        return this.id;
    }
}
