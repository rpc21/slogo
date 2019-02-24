package GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
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
    private ImageChooser<String> myTurtleIconChooser;
    private Button myRunButton;
    private Button myClearButton;
    private Button myHelpButton;
    private TabPane myTabs;
    private TurtleCanvas myBackgroundCanvas;
    private TurtleCanvas myTurtleCanvas;
    private ResourceBundle myResources;
    private String myLanguage;
    private Toolbar myToolbar;
    private StackedCanvasPane myStackedCanvasPane;
    private DisplayView currentDisplayView;

    private TabExplorer myTabExplorer;
    private SLogoTab myVariables;
    private SLogoTab myCommands;
    private SLogoTab myMethods;
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
        myResources = ResourceBundle.getBundle("/resources.languages/" + myLanguage);
        myStage = stage;
        myRoot = createGridPane();
//        createLanguageChooser();
        myScene = new Scene(myRoot, SCENE_WIDTH, SCENE_HEIGHT, Color.LIGHTGRAY);
        handleResizability();
        myStage.setScene(myScene);
    }

    private void handleResizability() {
        myScene.widthProperty().addListener(observable -> {
            resizeCanvases();
        });
        myScene.heightProperty().addListener(observable -> {
            resizeCanvases();
        });
    }

    private void resizeCanvases() {
        myBackgroundCanvas.resize(myScene.getWidth() * 7/12, myScene.getHeight()*4.5/6.5);
        myTurtleCanvas.resize(myScene.getWidth() * 7/12, myScene.getHeight()*4.5/6.5);
    }

    public void display(){
        myStage.show();
    }

    private GridPane createGridPane(){
        GridPane grid = new GridPane();
        setGridProperties(grid);
        setTitle(grid);
        createCanvas(grid);
        setToolbar(grid);
        createLanguageChooser();
        makeTextBox(grid);
        initializeButtons(grid);
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
        myStackedCanvasPane = new StackedCanvasPane();
        grid.add(myStackedCanvasPane, 0, 1, 7, 5);
    }

//    private TurtleCanvas createBackgroundCanvas(double width, double height) {
//        TurtleCanvas canvas = new TurtleCanvas(width, height);
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        gc.setFill(Color.WHITE);
//        gc.rect(0, 0, canvas.getWidth(), canvas.getHeight());
//        gc.fill();
//        return canvas;
//    }

    private void makeTextBox(GridPane grid){
        myTextBox = new TextArea();
        myTextBox.setPrefRowCount(4);
        myTextBox.setPrefColumnCount(10);
        myTextBox.setWrapText(true);
        grid.add(myTextBox, 0, 7, 8, 3);
    }

    private void setToolbar(GridPane grid) {
        myToolbar = new Toolbar(myStackedCanvasPane.getBackgroundColorAccess(),
                myStackedCanvasPane.getPenPropertiesAccess(), myStackedCanvasPane.getIconAccess());
        grid.add(myToolbar, 1, 0, 6, 1);
    }

//    private void initializeToolbarMenus(List<Control> toolbarMenus) {
//        myLanguageChooser = createLanguageChooser();
//        toolbarMenus.add(myLanguageChooser);
//        myBackGroundColorChooser = createBackgroundChooser();
//        toolbarMenus.add(myBackGroundColorChooser);
//        myPenColorChooser = createPenColorChooser();
//        toolbarMenus.add(myPenColorChooser);
//        myTurtleIconChooser = createImageChooser();
//        toolbarMenus.add(myTurtleIconChooser);
//    }

//    private PenColorChooser createPenColorChooser(){
//        PenColorChooser penColorChooser = new PenColorChooser();
//        penColorChooser.setOnAction(event -> {
//            myTurtleCanvas.getGraphicsContext2D().setStroke(penColorChooser.getValue());
//        });
//        return penColorChooser;
//    }


//    private BackgroundColorChooser createBackgroundChooser() {
//
//        BackgroundColorChooser backgroundColorChooser = new BackgroundColorChooser();
//        backgroundColorChooser.setOnAction(event -> {
//            setBackgroundColor(backgroundColorChooser);
//        });
//        return backgroundColorChooser;
//    }

//    private void setBackgroundColor(BackgroundColorChooser backgroundColorChooser) {
//        myBackgroundCanvas.getGraphicsContext2D().setFill(backgroundColorChooser.getValue());
//        myBackgroundCanvas.getGraphicsContext2D().rect(0, 0, myBackgroundCanvas.getWidth(), myBackgroundCanvas.getHeight());
//        myBackgroundCanvas.getGraphicsContext2D().fill();
//    }

//    private ImageChooser<String> createImageChooser() {
//        ImageChooser<String> imageChooser = new ImageChooser<>();
//        imageChooser.getItems().addAll(currentDisplayView.getPossibleImages());
//        imageChooser.getSelectionModel().selectFirst();
//        imageChooser.setOnAction(event-> {
//            myStackPane.getChildren().remove(currentDisplayView);
//            currentDisplayView = new DisplayViewFactory().generateDislplayView(imageChooser.getValue(),
//                    currentDisplayView);
//            myStackPane.getChildren().add(currentDisplayView);
//        });
//        return imageChooser;
//    }


//    private LanguageChooser<String> createLanguageChooser() {
//        LanguageChooser<String> languageChooser = new LanguageChooser<>();
//        languageChooser.getItems().addAll("English", "German", "French");
//        languageChooser.getSelectionModel().selectFirst();
//        return languageChooser;
//    }
//        Toolbar toolbar = new Toolbar();
//        List<Control> toolbarMenus = new ArrayList<>();
//        initializeToolbarMenus(toolbarMenus);
//        System.out.println(toolbar);
//        toolbar.getChildren().addAll(toolbarMenus);
//        grid.add(toolbar, 1, 0, 4, 1);
//    }

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
        myToolbar.getChildren().add(menuButton);
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
        grid.add(myRunButton, 8, 7);
        myClearButton = clearButton();
        grid.add(myClearButton, 8, 8);
        myHelpButton = helpButton();
        grid.add(myHelpButton, 8, 9);
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

    public void makeMoves(){
        List<Move> moves = new ArrayList<>();
        moves.add(new Move(Color.BLACK, true, PenStyle.DASHED, 2.0, new double[] {50, 0}));
        moves.add(new Move(Color.BLACK, true, PenStyle.DASHED, 2.0, new double[] {0, 100}));
        moves.add(new Move(Color.BLACK, true, PenStyle.DASHED, 10.0, new double[] {20, 100}));
        moves.add(new Move(Color.BLUE, true, PenStyle.DASHED, 1.0, new double[] {-50, -10}));
        moves.add(new Move(Color.BLACK, true, PenStyle.DASHED, 5.0, new double[] {10, -100}));
        moves.add(new Move(Color.PINK, true, PenStyle.DASHED, 3.0, new double[] {0, -12}));
        myStackedCanvasPane.addAllMoves(moves);
        myStackedCanvasPane.batchUpdateCanvas();
    }
}
