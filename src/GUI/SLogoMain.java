package GUI;

import GUI.GUIManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;

import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Main class to run the application
 */
public class SLogoMain extends Application {

    @Override
    public void start (Stage primaryStage) throws Exception {
        GUIManager guiManager = new GUIManager(primaryStage);
    }


}
