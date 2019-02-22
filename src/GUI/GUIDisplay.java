package GUI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GUIDisplay {

    public static final String DEFAULT_LANGUAGE = "English";
    private Stage myStage;
    private Scene myScene;
    private String myTitle = "SLogo";
    private GridPane myRoot;
    private TextArea myTextBox;
    private String commandToExecute;
    private LanguageChooser myLanguageChooser;
    private PenColorChooser myPenColorChooser;
    private BackgroundColorChooser myBackGroundColorChooser;
    private ImageChooser myTurtleIconChooser;
    private Button myRunButton;
    private Button myClearButton;
    private Button myHelpButton;
    private TabPane myTabs;
    private Canvas myTurtleCanvas;
    private ResourceBundle myResources;
    private String myLanguage;
    private Toolbar myToolbar;
    private StackPane myStackPane;

    public static final int SCENE_WIDTH = 1200;
    public static final int SCENE_HEIGHT = 650;

    public GUIDisplay(Stage stage){
        myLanguage = DEFAULT_LANGUAGE;
        myResources = ResourceBundle.getBundle("/resources.languages/"+myLanguage);
        myStage = stage;
        myRoot = createGridPane();
        myScene = new Scene(myRoot, SCENE_WIDTH, SCENE_HEIGHT, Color.LIGHTGRAY);
        myStage.setScene(myScene);
    }

    public void display(){
        myStage.show();
    }


    private GridPane createGridPane(){
        GridPane grid = new GridPane();
        setGridProperties(grid);
        setTitle(grid);
        setToolbar(grid);
        makeTextBox(grid);
        initializeButtons(grid);
        createCanvas(grid);
        createTabExplorer(grid);
        return grid;
    }

    private void createTabExplorer(GridPane grid) {
        TabExplorer tabExplorer = new TabExplorer();
        SLogoTab variablesTab = new SLogoTab("Variables");
        SLogoTab userDefinedMethods = new SLogoTab("Methods");
        SLogoTab commandHistory = new SLogoTab("Command History");
        variablesTab.addContents("First Contents added to variables");
        variablesTab.addContents("Second Contents added to variables");
        commandHistory.addContents("here are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.");
        for (int i = 0; i < 100; i++) {
            userDefinedMethods.addContents("Is this scrollable? "+i);
        }
        tabExplorer.getTabs().addAll(variablesTab, userDefinedMethods, commandHistory);
        grid.add(tabExplorer, 6, 1, 3, 3);
    }

    private void createCanvas(GridPane grid) {
        myStackPane = new StackPane();
        myTurtleCanvas = new Canvas(700, 450);
        GraphicsContext gc = myTurtleCanvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.rect(0, 0, myTurtleCanvas.getWidth(), myTurtleCanvas.getHeight());
        gc.fill();
        myStackPane.getChildren().add(myTurtleCanvas);
        myStackPane.getChildren().add(new TurtleView());
        grid.add(myStackPane, 0, 1, 5, 5);
    }

    private void makeTextBox(GridPane grid){
        myTextBox = new TextArea();
        myTextBox.setPrefRowCount(4);
        myTextBox.setPrefColumnCount(10);
        myTextBox.setWrapText(true);
        grid.add(myTextBox, 0, 7, 5, 3);
    }

    private void setToolbar(GridPane grid) {
        Toolbar toolbar = new Toolbar();
        List<Control> toolbarMenus = new ArrayList<>();
        initializeToolbarMenus(toolbarMenus);
        System.out.println(toolbar);
        toolbar.getChildren().addAll(toolbarMenus);
        grid.add(toolbar, 1, 0, 4, 1);
    }

    private void initializeToolbarMenus(List<Control> toolbarMenus) {
        myLanguageChooser = createLanguageChooser();
        toolbarMenus.add(myLanguageChooser);
        myBackGroundColorChooser = createBackgroundChooser();
        toolbarMenus.add(myBackGroundColorChooser);
        myPenColorChooser = new PenColorChooser();
        toolbarMenus.add(myPenColorChooser);
        myTurtleIconChooser = createImageChooser();
        toolbarMenus.add(myTurtleIconChooser);
    }

    private BackgroundColorChooser createBackgroundChooser() {

        BackgroundColorChooser backgroundColorChooser = new BackgroundColorChooser();
        backgroundColorChooser.setOnAction(event -> {
            myTurtleCanvas.getGraphicsContext2D().setFill(backgroundColorChooser.getValue());
            myTurtleCanvas.getGraphicsContext2D().rect(0, 0, myTurtleCanvas.getWidth(), myTurtleCanvas.getHeight());
            myTurtleCanvas.getGraphicsContext2D().fill();
        });
        return backgroundColorChooser;
    }

    private ImageChooser createImageChooser() {
        ImageChooser imageChooser = new ImageChooser();
        imageChooser.getItems().addAll("Basic Turtle Image", "Advanced Turtle Image");
        imageChooser.getSelectionModel().selectFirst();
        return imageChooser;
    }


    private LanguageChooser<String> createLanguageChooser() {
        LanguageChooser<String> languageChooser = new LanguageChooser<>();
        languageChooser.getItems().addAll("English", "German", "French");
        languageChooser.getSelectionModel().selectFirst();
        return languageChooser;
    }


    private void setTitle(GridPane grid) {
        Text title = new Text("SLogo");
        title.setFont(Font.font("Arial", 50));
        grid.add(title, 0, 0);
    }

    private void setGridProperties(GridPane grid) {
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
    }

    private void initializeButtons(GridPane grid){
        myRunButton = runButton();
        grid.add(myRunButton, 5, 7);
        myClearButton = clearButton();
        grid.add(myClearButton, 5, 8);
        myHelpButton = helpButton();
        grid.add(myHelpButton, 5, 9);
    }

    private Button runButton(){
        Button button = new Button("Run");
        button.setOnAction(event -> {
            System.out.println("Run");
            commandToExecute = myTextBox.getText();
        });
        return button;
    }

    private Button helpButton(){
        Button button = new Button("Help");
        button.setOnAction(event -> {
            System.out.println("Help");
            Alert help = showHelpMenu();
            help.show();
        });
        return button;
    }

    private Alert showHelpMenu(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("Help Menu");
        alert.setContentText("all the help info");
        return alert;
    }

    private Button clearButton(){
        Button button = new Button("Clear");
        button.setOnAction(event -> {
            System.out.println("Clear");
            myTextBox.setText("");
        });
        return button;
    }
}
