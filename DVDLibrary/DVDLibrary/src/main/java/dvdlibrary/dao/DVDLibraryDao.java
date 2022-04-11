package dvdlibrary.dao;

import java.util.List;

import dvdlibrary.dto.DVD;
import java.util.OptionalDouble;


//Dejan Savic
public interface DVDLibraryDao {
    /**
     * Adds the given DVD to the library and associates it with the given
     * title. If there is already a dvd associated with the given
     * DVD title it will return that DVD object, otherwise it will
     * return null.
     *
     * @param DVD title with which DVD is to be associated
     * @param DVD dvd to be added to the Library
     * @return the DVD object previously associated with the given  
     * DVD title if it exists, null otherwise
     */
    DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException;

    /**
     * Returns a List of all DVDs in the library.
     *
     * @return List containing all dvds in the library.
     */
    List<DVD> getAllDVDs() throws DVDLibraryDaoException;

    /**
     * Returns the DVD object associated with the given DVD title.
     * Returns null if no such DVD exists
     *
     * @param DVD title of the DVD to retrieve
     * @return the DVD object associated with the given DVD title,  
     * null if no such DVD exists
     */
    DVD getDVD(String title) throws DVDLibraryDaoException;

    /**
     * Removes from the library the DVD associated with the given title.
     * Returns the DVD object that is being removed or null if
     * there is no DVD associated with the given title
     *
     * @param dvdTitle tile of DVD to be removed
     * @return DVD object that was removed or null if DVD
     * was associated with the given student title
     */
    DVD removeDVD(String title) throws DVDLibraryDaoException;
    
    
    /**
     * Returns the DVD list associated with the years from current date minus
     * given date.
     *
     * @param Number of years to search back
     * @return the DVD list
     */
    List<DVD> findMoviesByYear(int years) throws DVDLibraryDaoException;
    
     /**
     * Returns the DVD list associated with the mpaa given
     *
     * @param MPAA string
     * @return the DVD list containing same mpaa
     */
    List<DVD> findMoviesByMpaa(String mpaa) throws DVDLibraryDaoException;
    
    /**
     * Returns the DVD list associated with the director
     *
     * @param Director String
     * @return the DVD list containing same director
     */
    List<DVD> findMoviesByDirector(String director) throws DVDLibraryDaoException;
    
     /**
     * Returns the DVD list associated with the studio
     *
     * @param Studio String
     * @return the DVD list containing same studio
     */
    List<DVD> findMoviesByStudio(String studio) throws DVDLibraryDaoException;
    
     /**
     * Returns the average release date of all movies in DVD list
     *
     * @param DVD list
     * @return optionalDouble of the average date
     */
    OptionalDouble findAverageMovieAge() throws DVDLibraryDaoException;
    
     /**
     * Returns DVD list of the latest release date 
     *
     * @param DVD List 
     * @return the DVD list containing only the latest release date
     */
    List<DVD> findNewestMovie() throws DVDLibraryDaoException;
    
     /**
     * Returns DVD list of the earliest release date 
     *
     * @param DVD List 
     * @return the DVD list containing only the earliest release date
     */
    List<DVD> findOldestMovie() throws DVDLibraryDaoException;
    
    
}
