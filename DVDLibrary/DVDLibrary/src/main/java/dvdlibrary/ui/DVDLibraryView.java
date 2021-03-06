/*
 * This is the view class that the user will interact with. It contains the console outputs, 
 * as well as the methods for various conditions.
 */
package dvdlibrary.ui;

import java.util.List;
import dvdlibrary.dto.DVD;

/**
 *
 * @author harle
 */
public class DVDLibraryView {
    //Create instance io for the class UserIO, to use the UserIO methods.
	    private UserIO io;

	    public DVDLibraryView(UserIO io) {
	        this.io = io;
	    }

	    //Method for printing menu and returning an integer. 
	    public int printMenuAndGetSelection() {
	        io.print("Main Menu");
	        io.print("1. List DVDs");
	        io.print("2. Add DVD to the collection");
	        io.print("3. View information for a DVD");
	        io.print("4. Remove a DVD from the collection");
	        io.print("5. Edit the information for a DVD in the the collection");
	        io.print("6. Find Movies from the past number of years");
	        io.print("7. Find Movies by an average MPAA Rating");
	        io.print("8. Find Movies By Director Name");
	        io.print("9. Find Movies by Studio Name");
	        io.print("10. Find Average Movie Age of all movies in our list");
	        io.print("11. Find the newest movie released");
	        io.print("12. Find the oldest movie released");
	        io.print("13. Exit");

	        return io.readInt("Please select from the above choices.", 1, 13);
	    }

	    //Method for creating an instance of class DVD, prompting for and setting the properties, then returning the object.
	    public DVD getNewDVDInfo() {
	        String title = io.readString("Please enter DVD Title");
	        String releaseDate = io.readString("Please enter the release date");
	        String mpaa = io.readString("Please enter the MPAA rating");
	        String director = io.readString("Please enter the Director's name");
	        String studio = io.readString("Please enter the Studio");
	        String userRating = io.readString("Please enter the User rating or a note");
	        DVD currentDVD = new DVD(title);
	        currentDVD.setReleaseDate(releaseDate);
	        currentDVD.setMpaa(mpaa);
	        currentDVD.setDirector(director);
	        currentDVD.setStudio(studio);
	        currentDVD.setUserRating(userRating);
	        return currentDVD;
	    }

	    //Below are all methods for displaying a string for the solicited information.
	    public void displayCreateDVDBanner() {
	        io.print("=== Create DVD ===");
	    }

	    public void displayCreateSuccessBanner() {
	        io.readString(
	                "DVD successfully created.  Please hit enter to continue");
	    }

	    public void displayEditSuccessBanner() {
	        io.readString(
	                "DVD successfully edited.  Please hit enter to continue");
	    }
	    
	    public void displayDVDList(List<DVD> dvdList) {
	        for (DVD currentDVD : dvdList) {
	            String dvdInfo = String.format("#%s : %s %s %s %s %s",
	                    currentDVD.getTitle(),
	                    currentDVD.getReleaseDate(),
	                    currentDVD.getMpaa(),
	                    currentDVD.getDirector(),
	                    currentDVD.getStudio(),
	                    currentDVD.getUserRating());
	            io.print(dvdInfo);
	        }
	        io.readString("Please hit enter to continue.");
	    }

	    public void displayDisplayAllBanner() {
	        io.print("=== Display All DVDs ===");
	    }

	    public void displayDisplayDVDBanner() {
	        io.print("=== Display DVD ===");
	    }

	    public String getDVDIdChoice() {
	        return io.readString("Please enter the DVD Title.");
	    }

	    public void displayDVD(DVD dvd) {
	        if (dvd != null) {
	            io.print(dvd.getTitle());
	            io.print(dvd.getReleaseDate());
	            io.print(dvd.getMpaa());
	            io.print(dvd.getDirector());
	            io.print(dvd.getStudio());
	            io.print(dvd.getUserRating());
	            io.print("");
	        } else {
	            io.print("No such DVD.");
	        }
	        io.readString("Please hit enter to continue.");
	    }

	    public void displayRemoveDVDBanner() {
	        io.print("=== Remove DVD ===");
	    }

	    public void displayRemoveResult(DVD dvdRecord) {
	        if (dvdRecord != null) {
	            io.print("DVD successfully removed.");
	        } else {
	            io.print("No such DVD.");
	        }
	        io.readString("Please hit enter to continue.");
	    }

	    public void displayEditDVDBanner() {
	        io.print("=== Edit DVD ===");
	    }
	    
	    public void displayExitBanner() {
	        io.print("Good Bye!!!");
	    }

	    public void displayUnknownCommandBanner() {
	        io.print("Unknown Command!!!");
	    }

	    public void displayErrorMessage(String errorMsg) {
	        io.print("=== ERROR ===");
	        io.print(errorMsg);
	    }
	    public int displayMoviesByYear() {
	    	return io.readInt("Please Enter Number Of Years Since Release");
	    	
	    }
	    public String displayMoviesByMPAA() {
	    	return io.readString("Please Enter MPAA Rating you want to sort by");
	    }
	    public String displayMoviesByDirector() {
	    	return io.readString("Enter Directors Name for Movies You Want");
	    }
	    public String displayMoviesByStudio() {
	    	return io.readString("Enter Studio Name For Movies You Want");
	    }
	    public void displayAverageMovieAge(int age) {
	    	io.print("Average Age Of Movies Is: " + age);
	    }
	    public void displayNewestMovie(DVD dvd) {
	    	io.print("Newest Movie To Be Released is " + dvd.getTitle());
	    }
	    public void displayOldestMovie(DVD dvd) {
	    	io.print("Oldest Movie To Be Released is " + dvd.getTitle());
	    }
}
