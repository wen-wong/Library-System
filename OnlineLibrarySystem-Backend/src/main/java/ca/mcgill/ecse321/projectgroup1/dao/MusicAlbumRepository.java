package ca.mcgill.ecse321.projectgroup1.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup1.model.MusicAlbum;

import java.util.List;

/**
 * A client would be able to search a music album by either its artist, or its
 * genre, and also by both. Since none of these two attributes can be unique for
 * each album, simply listing the results is the best way to insure efficient
 * search results.
 * 
 * Author: Anika Kabir
 */

public interface MusicAlbumRepository extends CrudRepository<MusicAlbum, Integer> {
    // Verifies if MusicAlbum instance exists by Id
    Boolean existsById(int id);

    // Query method to find MusicAlbum by Id
    MusicAlbum findById(int id);

    // Query to check if a Music Album instance exists using its artist
    boolean existsMusicAlbumByArtist(String artist);

    // Query method to find all Music Album instances with common artist
    List<MusicAlbum> findByArtist(String artist);

    // Query to check if a Music Album instance exists using its genre
    boolean existsMusicAlbumByGenre(String genre);

    // Query method to find all Music Album instances with common genre
    List<MusicAlbum> findByGenre(String genre);

    // Query to check if a Music Album instance exists using its artist and genre
    boolean existsMusicAlbumByArtistAndGenre(String artist, String genre);

    // Query method to find all Music Album instances with common artist and genre
    List<MusicAlbum> findByArtistAndGenre(String artist, String genre);

    // Query method to find a list of music album in Library
    List<MusicAlbum> findMusicAlbumByLibraryName(String name);

    // Query method to find all music albums with common title
    // List<MusicAlbum> findMusicAlbumByTtile(String title);
}
