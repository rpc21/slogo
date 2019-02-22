package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GUIManager {

    private Stage myStage;
    private List<GUIComponent> myGUIComponents;
    BorderPaneComponent myRoot;

    public GUIManager(Stage stage){
        myStage = stage;
        myGUIComponents = new ArrayList<>();
        render();
        Scene myScene = new Scene(myRoot);
        myStage.show();
    }

    private void render() {
        setBorderPane();
        createToolbar();
        createRightPanel();
        createCommandLine();
    }

    private void createCommandLine() {

    }

    private void createRightPanel() {

    }

    private void createToolbar() {
        Toolbar toolbar = new Toolbar();
        List<Control> toolbarMenus = new ArrayList<>();
        initializeToolbarMenus(toolbarMenus);
        myRoot.setTop(toolbar);
    }

    private void initializeToolbarMenus(List<Control> toolbarMenus) {
        ChoiceBox<String> languageChooser = createLanguageChooser();
//        toolbarMenus.add(languageChooser);
        ColorPicker backgroundColorChooser = new ColorPicker();
//        BackgroundColorChooser backgroundColorChooser = new BackgroundColorChooser();
        toolbarMenus.add(backgroundColorChooser);
        ColorPicker penColorChooser = new PenColorChooser();
        toolbarMenus.add(penColorChooser);
//        ImageChooser imageChooser = createImageChooser();
//        toolbarMenus.add(imageChooser);
    }

    private ImageChooser createImageChooser() {
        ImageChooser imageChooser = new ImageChooser();
        imageChooser.getItems().addAll("Basic Turtle Image", "Advanced Turtle Image");
        return imageChooser;
    }


    private ChoiceBox<String> createLanguageChooser() {
        ChoiceBox<String> months = new ChoiceBox<>();
//        List<String> options = new ArrayList<>(List.of("English", "French", "German"));
//        languageChooser.getItems().addAll(FXCollections.observableArrayList("English", "French", "German"));
//        return  languageChooser;

        // Create the ComboBox
//        final ComboBox<String> months = new ComboBox<>();
        // Add the Months to the ComboBox
        months.getItems().addAll("January", "February", "March", "April", "May", "June",
                "July",  "August", "September", "October", "November", "December");
        // Set the Limit of visible months to 5
//        months.setVisibleRowCount(5);
        return months;
    }

    private void setBorderPane() {
        myRoot = new BorderPaneComponent();
        myGUIComponents.add(myRoot);
    }


}
