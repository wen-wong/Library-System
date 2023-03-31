package ca.mcgill.ecse321.projectgroup1.dto;

import java.sql.Date;


/**
 * BookDto - Data Transfer Objects for the Book class
 * 
 * @param library - library that has this book
 * @param author - author of this book
 * @param pageNumber - number of pages of this book
 * @param title - title of this book
 * @param description - description of the book
 * @param releaseDate - the release date of the book
 * @param isArchive - checking if the book is an archive or not
 * @param id - id of the book
 * 
 * @author Chloe Nasrallah
 */

//Attributes for the Book class which extends LibraryItem
public class BookDto extends LibraryItemDto {
    private String author;
    private int pageNumber;

    //Contructor of the BookDto
    public BookDto(LibraryDto library, String author, int pageNumber, String title, String description, Date releaseDate, Boolean isArchive, int id){
        super(library, title, description, releaseDate, isArchive, id);
        this.author = author;
        this.pageNumber = pageNumber;
    }
  
 // Getters for BookDto

    public String getAuthor() {
        return this.author;
    }

    public int getPageNumber() {
        return this.pageNumber;
    }
}
