package GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private TabExplorer myTabExplorer;
    private SLogoTab myVariables;
    private SLogoTab myCommands;
    private SLogoTab myMethods;
    private StackPane myStackPane;
    RetrieveCommand myRetrieveCommand = new RetrieveCommand() {
        @Override
        public void retrieveCommand(String a) {
            myTextBox.setText(a);
        }
    };
        public static final int SCENE_WIDTH = 1200;
    public static final int SCENE_HEIGHT = 650;

    public GUIdata dataTracker = new GUIdata();

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
        myTabExplorer = new TabExplorer();
        myTabExplorer.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        myVariables = new SLogoTab(myResources.getString("Variables"), dataTracker);
        myVariables.getContent().setOnMouseClicked(event -> {
            myTextBox.setText(dataTracker.getMyTextToUpdate());
        });
        myMethods = new SLogoTab(myResources.getString("Methods"), dataTracker);
        myMethods.getContent().setOnMouseClicked(event -> {
            myTextBox.setText(dataTracker.getMyTextToUpdate());
        });
        myCommands = new SLogoTab(myResources.getString("CommandHistory"), dataTracker);
        myCommands.getContent().setOnMouseClicked(event -> {
            myTextBox.setText(dataTracker.getMyTextToUpdate());
        });
        myVariables.addContents("Sample variable one");
        myVariables.addContents("practice variable two");
        myCommands.addContents("sample commmand");
        myMethods.addContents("sample method");
        myTabExplorer.getTabs().addAll(myVariables, myMethods, myCommands);
        grid.add(myTabExplorer, 6, 1, 3, 5);
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
        myTurtleIconChooser = createImageChooser();
        toolbarMenus.add(myTurtleIconChooser);
        myBackGroundColorChooser = createBackgroundChooser();
        toolbarMenus.add(myBackGroundColorChooser);
        myPenColorChooser = new PenColorChooser();
        toolbarMenus.add(myPenColorChooser);
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
     //   imageChooser.getSelectionModel().selectFirst();
        imageChooser.setPromptText(myResources.getString("TurtleIcon"));
        return imageChooser;
    }


    private LanguageChooser createLanguageChooser() {
        LanguageChooser menuButton = new LanguageChooser();
        menuButton.setText(myResources.getString("Language"));
        MenuItem english = new MenuItem("English");
        english.setOnAction(event -> {
            myLanguage = "English";
            updateLanguage();
            myLanguageChooser.setText(myLanguage);
        });

        MenuItem french = new MenuItem("Français");
        french.setOnAction(event -> {
            myLanguage = "French";
            updateLanguage();
            myLanguageChooser.setText("Français");
        });
        MenuItem chinese = new MenuItem("Zhōngwén");
        chinese.setOnAction(event -> {
            myLanguage = "Chinese";
            updateLanguage();
            myLanguageChooser.setText("Zhōngwén");
        });
        MenuItem german = new MenuItem("Deutsche");
        german.setOnAction(event -> {
            myLanguage = "German";
            updateLanguage();
            myLanguageChooser.setText("Deutsche");
        });
        MenuItem italian = new MenuItem("Italiano");
        italian.setOnAction(event -> {
            myLanguage = "Italian";
            updateLanguage();
            myLanguageChooser.setText("Italiano");
        });
        MenuItem portuguese = new MenuItem("Português");
        portuguese.setOnAction(event -> {
            myLanguage = "Portuguese";
            updateLanguage();
            myLanguageChooser.setText("Português");
        });
        MenuItem russian = new MenuItem("Russkiy");
        russian.setOnAction(event -> {
            myLanguage = "Russian";
            updateLanguage();
            myLanguageChooser.setText("Russkiy");
        });
        MenuItem spanish = new MenuItem("Español");
        spanish.setOnAction(event -> {
            myLanguage = "Spanish";
            updateLanguage();
            myLanguageChooser.setText("Español");
        });
        MenuItem urdu = new MenuItem("Urdu");
        urdu.setOnAction(event -> {
            myLanguage = "Urdu";
            updateLanguage();
            myLanguageChooser.setText("Urdu");
        });
        menuButton.getItems().addAll(english, french, chinese, german, italian, portuguese, russian, spanish, urdu);
        return menuButton;
    }

    private void updateLanguage(){
        myResources = ResourceBundle.getBundle("/resources.languages/"+myLanguage);
        myRunButton.setText(myResources.getString("Run"));
        myClearButton.setText(myResources.getString("Clear"));
        myHelpButton.setText(myResources.getString("Help"));
        myVariables.setText(myResources.getString("Variables"));
        myCommands.setText(myResources.getString("CommandHistory"));
        myMethods.setText(myResources.getString("Methods"));
      //  myLanguageChooser.setText(myResources.getString("Language"));
   //     myBackGroundColorChooser.setPromptText(myResources.getString("BackgroundColor"));
   //     myPenColorChooser.setPromptText(myResources.getString("PenColor"));
        myTurtleIconChooser.setPromptText(myResources.getString("TurtleIcon"));
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
       // Button button = new Button(myResources.getString("Run"));
        Button button = new Button(myResources.getString("Run"));
        button.setOnAction(event -> {
            commandToExecute = myTextBox.getText();
            addToCommandHistory(commandToExecute);
        });
        return button;
    }

    private void addToCommandHistory(String command){
        myCommands.addContents(command);
    }

    private Button helpButton(){
        Button button = new Button(myResources.getString("Help"));
        button.setOnAction(event -> {
            Alert help = showHelpMenu();
            help.show();
        });
        return button;
    }

    private Alert showHelpMenu(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(myResources.getString("Help"));
        alert.setHeaderText(myResources.getString("HelpHeader"));
        alert.setContentText(myResources.getString("HelpInfo"));
        return alert;
    }

    private Button clearButton(){
        Button button = new Button(myResources.getString("Clear"));
        button.setOnAction(event -> {
            myTextBox.setText("");
        });
        return button;
    }
}
