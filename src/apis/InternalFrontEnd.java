package apis;

import java.util.*;

/**
 *
 */
public interface InternalFrontEnd {

    /**
     * Handles event of user selecting color in a drop down menu and updating the display of the GUI accordingly
     */
    public void changeBackgroundColor();

    /**
     * Handles event of user selecting language different than the current one so that they may view information
     * in the chosen language and input commands in that language
     */
    public void changeLanguage();

    /**
     * Clears all previous commands in the ScrollPane display
     */
    public void clearCommandHistory();

    /**
     * Called when external method encounters an exception from the backend
     */
    public void displayError();

    /**
     * Adds pop-up window to display information about how to use the application and about available commands
     * from a file depending on the current language
     */
    public void displayHelpPage();

    /**
     * Sets up all of the GUI components
     */
    public void render();

    /**
     * When run button is pressed, takes command from command terminal window and adds it to the command history
     */
    public void updateCommandHistory();

    /**
     * Gets backend parameters to display on the screen for the user
     */
    public void updateDisplay();

    /**
     * When a valid variable is set, add variable and its definition to a ScrollPane of variables
     */
    public void updateVariableView();

}
