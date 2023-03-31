package ca.mcgill.ecse321.projectgroup1.dto;

import java.sql.Date;

/**
 * NewspaperDto - Data Transfer Objects for the Newspaper class
 * 
 * @param library - library that has this newspaper 
 * @param publisher - publisher of this newspaper 
 * @param title - title of this newspaper 
 * @param description - description of the newspaper 
 * @param releaseDate - the release date of the newspaper 
 * @param isArchive - checking if the newspaper is an archive or not
 * @param id - id of the newspaper 
 *
 * @author Chloe Nasrallah
 */

 //Attributes for the Newspaper class, which is a subclass of LibraryItem 
public class NewspaperDto extends LibraryItemDto{
    private String publisher;
   

    //Contructor of the NewspaperDto
    public NewspaperDto(LibraryDto library, String publisher, String title, String description, Date releaseDate, Boolean isArchive, int id){
        super(library, title, description, releaseDate, isArchive, id);
        this.publisher = publisher;
    }
                                                                            
    // Getter for the NewspaperDto

    public String getPublisher() {
        return this.publisher;
    }
}