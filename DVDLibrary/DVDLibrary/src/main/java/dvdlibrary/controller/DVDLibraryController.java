/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dvdlibrary.controller;

/**
 *
 * @author Austin
 */
import dvdlibrary.dao.DVDLibraryDao;
import dvdlibrary.dao.DVDLibraryDaoException;
import dvdlibrary.dto.DVD;
import dvdlibrary.ui.UserIOConsoleImpl;
import dvdlibrary.ui.DVDLibraryView;
import java.util.List;

//Controller for DVD program
public class DVDLibraryController {

    private DVDLibraryDao dao;
    private DVDLibraryView view;

    //IO for input and output to console
    private final UserIOConsoleImpl io = new UserIOConsoleImpl();

    //dependency injection for DAO and VIEW
    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
        this.dao = dao;
        this.view = view;
    }
    
    //repeating loop to run output to Console and control program. 
    //Asks user for a choice from listed options. Choices can call 
    //methods listDVDs, addDVD, viewDVD, removeDVD, editDVD, and exit
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
                
                //Displays menu to console
                menuSelection = getMenuSelection();
                
                //Switch case to call correct option user chooses
                switch (menuSelection) {
                    case 1:
                        //method to display all dvds in file
                        listDVDs();
                        break;
                    case 2:
                        //method to add a dvd into the file
                        addDVD();
                        break;
                    case 3:
                        //method to display a dvd in file given a title by user
                        viewDVD();
                        break;
                    case 4:
                        //method to remove a dvd from the file given title by user
                        removeDVD();
                        break;
                    case 5:
                        //overwrites a dvd in file given user info -> Very similar to addDVD
                        editDVD();
                        break;
                    case 6:
                        //Sets while loop to false to exit program
                        keepGoing = false;
                        break;
                    default:
                        //Displays to user 1-6 was not chosen
                        unknownCommand();
                }
            }
            //Exit message to console so user knows program is closing
            exitMessage();
        } catch (DVDLibraryDaoException e) {
            //If an error is thrown, error message is sent to console output
            view.displayErrorMessage(e.getMessage());
        }
    }

    //Retrieves the menu from view to show in console
    private int getMenuSelection() throws DVDLibraryDaoException {
        return view.printMenuAndGetSelection();
    }

    //Displays the create banner from view. Stores a DVD object given user input
    private void addDVD() throws DVDLibraryDaoException {
        view.displayCreateDVDBanner();
        DVD newDvd = view.getNewDVDInfo();
        dao.addDVD(newDvd.getTitle(), newDvd);
        view.displayCreateSuccessBanner();
    }
    
    //Displays the edit banner from view. Stores a DVD object and overwrites
    //a previous dvd from the file given user input
    private void editDVD() throws DVDLibraryDaoException {
        view.displayEditDVDBanner();
        DVD newDvd = view.getNewDVDInfo();
        dao.addDVD(newDvd.getTitle(), newDvd);
        view.displayEditSuccessBanner();
    }

    //Creates a list by retrieving all DVDS from file and using view to display
    private void listDVDs() throws DVDLibraryDaoException {
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);
    }

    //Gets user input for a title they want to search for.
    //Creates dvd object using view and then displays to console
    private void viewDVD() throws DVDLibraryDaoException {
        view.displayDisplayDVDBanner();
        String dvdTitle = view.getDVDIdChoice();
        DVD dvd = dao.getDVD(dvdTitle);
        view.displayDVD(dvd);
    }
    
    //Gets user input for a title they want to remove from file
    //Uses DAO and view to remove DVD object from file
    private void removeDVD() throws DVDLibraryDaoException {
        view.displayRemoveDVDBanner();
        String dvdTitle = view.getDVDIdChoice();
        DVD removedDvd = dao.getDVD(dvdTitle);
        view.displayRemoveResult(removedDvd);
    }

    //Displays an unknown command was chosen by the user to console
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    //Displays that the program is exiting
    private void exitMessage() {
        view.displayExitBanner();
    }

}
