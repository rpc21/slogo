package GUI;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class to run the application
 */
public class SLogoMain extends Application {

    @Override
    public void start (Stage primaryStage) throws Exception {
        GUIDisplay display = new GUIDisplay(primaryStage);
        display.display();
        display.makeMoves();

    }


}
