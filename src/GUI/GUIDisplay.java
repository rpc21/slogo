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

    public static final int SCENE_WIDTH = 1200;
    public static final int SCENE_HEIGHT = 650;

    public GUIDisplay(Stage stage){
        myLanguage = DEFAULT_LANGUAGE;
        myResources = ResourceBundle.getBundle("/resources.languages/" + myLanguage);
        myStage = stage;
        myRoot = createGridPane();
        myScene = new Scene(myRoot, SCENE_WIDTH, SCENE_HEIGHT, Color.LIGHTGRAY);
        myScene.widthProperty().addListener(observable -> {
            resizeCanvases();
        });
        myScene.heightProperty().addListener(observable -> {
            resizeCanvases();
        });
        myStage.setScene(myScene);
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
        makeTextBox(grid);
        initializeButtons(grid);
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
        grid.add(tabExplorer, 8, 1, 5, 5);
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
        Toolbar toolbar = new Toolbar(myStackedCanvasPane.getBackgroundColorAccess(),
                myStackedCanvasPane.getPenPropertiesAccess(), myStackedCanvasPane.getIconAccess());
        grid.add(toolbar, 1, 0, 6, 1);
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

    public void makeMoves(){
        List<Move> moves = new ArrayList<>();
        moves.add(new Move(Color.BLACK, true, PenStyle.DASHED, 2.0, new double[] {50, 0}));
        moves.add(new Move(Color.BLACK, true, PenStyle.DASHED, 2.0, new double[] {0, 100}));
//        currentDisplayView.makeMove(new Move(Color.BLACK, true, PenStyle.DASHED, 2.0, new double[] {-50, 0}));
//        currentDisplayView.makeMove(new Move(Color.BLACK, true, PenStyle.DASHED, 2.0, new double[] {0, -100}));
        moves.add(new Move(Color.BLACK, true, PenStyle.DASHED, 10.0, new double[] {20, 100}));
        moves.add(new Move(Color.BLUE, true, PenStyle.DASHED, 1.0, new double[] {-50, -10}));
        moves.add(new Move(Color.BLACK, true, PenStyle.DASHED, 5.0, new double[] {10, -100}));
        moves.add(new Move(Color.PINK, true, PenStyle.DASHED, 3.0, new double[] {0, -12}));
        myStackedCanvasPane.addAllMoves(moves);
        myStackedCanvasPane.batchUpdateCanvas();
    }
}
