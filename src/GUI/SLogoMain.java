package GUI;

import GUI.Display;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class to run the application
 */
public class SLogoMain extends Application {

    Display displayMaker = new Display();

    @Override
    public void start (Stage primaryStage) throws Exception {
        displayMaker.start(primaryStage);
        primaryStage.show();
    }

    public static void main (String[] args){
        launch(args);
    }
}
