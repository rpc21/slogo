package main;

import GUI.GUI.GUIController;
import GUI.GUI.GUIDisplay;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class to run the application
 */
public class SLogoMain extends Application {

    /**
     * Runs the program
     * @param primaryStage stage to create UI
     * @throws Exception errors in user input
     */
    @Override
    public void start (Stage primaryStage) throws Exception {
        GUIDisplay window = new GUIDisplay(primaryStage);
        makeDisplay(window);
    }

    private void makeDisplay(GUIDisplay display){
        GUIController myVisualController = new GUIController(display);
        display.display();
        display.getNewWindowButton().setOnMouseClicked(event -> {
            makeNewWindow();
        });
    }

    private void makeNewWindow(){
        GUIDisplay anotherWindow = new GUIDisplay(new Stage());
        makeDisplay(anotherWindow);
    }

}
