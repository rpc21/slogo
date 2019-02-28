package main;


import GUI.GUIController;
import GUI.GUIDisplay;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class to run the application
 */
public class SLogoMain extends Application {
           
    @Override
    public void start (Stage primaryStage) throws Exception {
        GUIDisplay display = new GUIDisplay(primaryStage);
        GUIController myVisualController = new GUIController(display);
        //make visualization
        //get info from vis
        //set up TurtleState
        //make Parser
        display.display();

    }


}
