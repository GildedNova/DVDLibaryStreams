/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dvdlibrary.dao;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import dvdlibrary.dao.DVDLibraryDao;
import dvdlibrary.dto.DVD;

//Dejan Savic
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {
	//list
	public static final String LIBRARY_FILE = "library.txt";
	public static final String DELIMITER = "::";
	private Map<String, DVD> dvds = new HashMap<>();
	@Override
	public DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException {
		loadDVDs();
		DVD prevDVD = dvds.put(title, dvd);
		writeLibrary();
		return prevDVD;
	}
	

	@Override
	public List<DVD> getAllDVDs() throws DVDLibraryDaoException {
		loadDVDs();
		return new ArrayList<DVD>(dvds.values());
	}

	@Override
	public DVD getDVD(String title) throws DVDLibraryDaoException{
		loadDVDs();
		return dvds.get(title);
	}

	@Override
	public DVD removeDVD(String title) throws DVDLibraryDaoException{
		loadDVDs();
		DVD removedDVD = dvds.remove(title);
		writeLibrary();
		return removedDVD;
	}
	
	
	//Unmarshall to read text from file
	private DVD unmarshallDVD(String DVDAsText){
	    // DVDAsText is expecting a line read in from our file.
	    // For example, it might look like this:
	    // title::release date::rating::director name::studio::User rating or note
	    //
	    // We then split that line on our DELIMITER - which we are using as ::
	    // Leaving us with an array of Strings, stored in DVD Tokens.
	    // Which should look like this:
	    // ______________________________________________________
	    // |     |rel.|       |        |      |    				|
	    // |Title|date|rating |director|studio|user rating/note |
	    // |     |    |       |        |      |    				|
	    // -----------------------------------------------------
	    //  [0]  [1]    [2]       [3]    [4]     [5]
	    String[] DVDTokens = DVDAsText.split(DELIMITER);

	    // Given the pattern above, the DVD title is in index 0 of the array.
	    String DVDTitle = DVDTokens[0];

	    // Which we can then use to create a new DVD object to satisfy
	    // the requirements of the Student constructor.
	    DVD DVDFromFile = new DVD(DVDTitle);

	    // However, there are 4 remaining tokens that need to be set into the
	    // new DVD object. Do this manually by using the appropriate setters.

	    // Index 1 - Title
	    DVDFromFile.setReleaseDate(DVDTokens[1]);

	    // Index 2 - mpaa Rating
	    DVDFromFile.setMpaa(DVDTokens[2]);

	    // Index 3 - Director
	    DVDFromFile.setDirector(DVDTokens[3]);
		// Index 4 - Studio
	    DVDFromFile.setStudio(DVDTokens[4]);
	    // Index 5 - User Rating/note
	    DVDFromFile.setUserRating(DVDTokens[5]);

	    // We have now created a DVD! Return it!
	    return DVDFromFile;
	}
	private void loadDVDs() throws  DVDLibraryDaoException  {
	    Scanner scanner;

	    try {
	        // Create Scanner for reading the file
	        scanner = new Scanner(
	                new BufferedReader(
	                        new FileReader(LIBRARY_FILE)));
	    } catch (FileNotFoundException e) {
	        throw new DVDLibraryDaoException(
	                "-_- Could not load roster data into memory.", e);
	    }
	    // currentLine holds the most recent line read from the file
	    String currentLine;
	    // currentDVD holds the most recent DVD unmarshalled
	    DVD currentDVD;
	    // Go through DVD_FILE line by line, decoding each line into a 
	    // DVD object by calling the unmarshallDVD method.
	    // Process while we have more lines in the file
	    while (scanner.hasNextLine()) {
	        // get the next line in the file
	        currentLine = scanner.nextLine();
	        // unmarshall the line into a DVD
	        currentDVD = unmarshallDVD(currentLine);

	        // We are going to use the DVD title as the map key for our DVD object.
	        // Put currentDVD into the map using DVD title as the key
	        dvds.put(currentDVD.getTitle(), currentDVD);
	    }
	    // close scanner
	    scanner.close();
	}
	private String marshallDVD(DVD aDVD){
	    // We need to turn a DVD object into a line of text for our file.
	    // For example, we need an in memory object to end up like this:
	    // title::release date::rating::director name::studio::User rating or note

	    // It's not a complicated process. Just get out each property,
	    // and concatenate with our DELIMITER as a kind of spacer. 

	    // Start with the DVD title, since that's supposed to be first.
	    String DVDAsText = aDVD.getTitle() + DELIMITER;

	    // add the rest of the properties in the correct order:

	    // get Release Date
	    DVDAsText += aDVD.getReleaseDate() + DELIMITER;

	    // get MPAA Rating
	    DVDAsText += aDVD.getMpaa() + DELIMITER;

	    // get Director Name
	    DVDAsText += aDVD.getDirector()  + DELIMITER;
	    //get studio name
	    DVDAsText += aDVD.getStudio()  + DELIMITER;
	    //get user rating
	    DVDAsText += aDVD.getUserRating()  + DELIMITER;
	    

	    // We have now turned a DVD to text! Return it!
	    return DVDAsText;
	}
	/**
	 * Writes all DVDs in the roster out to a Library_FILE.  See loadRoster
	 * for file format.
	 * 
	 * @throws ClassRosterDaoException if an error occurs writing to the file
	 */
	private void writeLibrary() throws DVDLibraryDaoException {
	    // NOTE FOR APPRENTICES: We are not handling the IOException - but
	    // we are translating it to an application specific exception and 
	    // then simple throwing it (i.e. 'reporting' it) to the code that
	    // called us.  It is the responsibility of the calling code to 
	    // handle any errors that occur.
	    PrintWriter out;

	    try {
	        out = new PrintWriter(new FileWriter(LIBRARY_FILE));
	    } catch (IOException e) {
	        throw new DVDLibraryDaoException(
	                "Could not save DVD data.", e);
	    }

	    // Write out the DVD objects to the Library file.
	    // NOTE TO THE APPRENTICES: We could just grab the DVD map,
	    // get the Collection of DVD and iterate over them but we've
	    // already created a method that gets a List of DVDs so
	    // we'll reuse it.
	    String DVDAsText;
	    List<DVD> DVDList = this.getAllDVDs();
	    for (DVD currentDVD : DVDList) {
	        // turn a DVD into a String
	        DVDAsText = marshallDVD(currentDVD);
	        // write the DVD object to the file
	        out.println(DVDAsText);
	        // force PrintWriter to write line to the file
	        out.flush();
	    }
	    // Clean up
	    out.close();
	}
	
	



}
