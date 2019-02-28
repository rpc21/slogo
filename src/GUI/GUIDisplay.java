package GUI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nodes.VisualCommand;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class GUIDisplay {

    public static final String DEFAULT_LANGUAGE = "English";
    private Stage myStage;
    private Scene myScene;
    private String myTitle = "SLogo";
    private GridPane myRoot;
    private TextArea myTextBox;
    private String commandToExecute;
    private LanguageChooser myLanguageChooser;
    private ImageChooser<String> myTurtleIconChooser;
    private Button myRunButton;
    private Button myClearButton;
    private Button myHelpButton;
    private ResourceBundle myResources;
    private String myLanguage;
    private Toolbar myToolbar;
    private StackedCanvasPane myStackedCanvasPane;
    private static final String LANGUAGE_LOCATION = "languages/";
    private final String VARIABLES = "Variables";
    private final String METHODS = "Methods";
    private final String COMMANDS = "CommandHistory";
    private TabExplorer myTabExplorer;
    private SLogoTab myVariables;
    private SLogoTab myCommands;
    private SLogoTab myMethods;
    private GridPane myCurrentGUIGrid;
    private Consumer<String> myLanguageConsumer;
    public static final int SCENE_WIDTH = 1200;
    public static final int SCENE_HEIGHT = 650;
    public GUIdata dataTracker = new GUIdata();
    private ErrorDisplay myError;

    public GUIDisplay(Stage stage){
        myLanguage = DEFAULT_LANGUAGE;
        myResources = ResourceBundle.getBundle(myLanguage);
        myStage = stage;
        myRoot = createGridPane();
        myLanguageConsumer = (x) -> {
            myLanguage = x;
            updateLanguage(x);
        };
        myLanguageChooser = new LanguageChooser(myLanguageConsumer);
        myToolbar.getChildren().add(myLanguageChooser);
        myScene = new Scene(myRoot, SCENE_WIDTH, SCENE_HEIGHT, Color.LIGHTGRAY);
        myStage.setScene(myScene);
    }

    public void executeVisualCommands(List<VisualCommand> myCommands){
        for (VisualCommand c: myCommands)
            c.execute(myStackedCanvasPane);
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
        grid.setPrefSize(SCENE_WIDTH, SCENE_HEIGHT);
        myCurrentGUIGrid = grid;
        return grid;
    }

    private void createTabExplorer(GridPane grid) {
        myTabExplorer = new TabExplorer();
        myTabExplorer.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        myVariables = makeTab(VARIABLES, dataTracker, myResources, myTextBox);
        myMethods = makeTab(METHODS, dataTracker, myResources, myTextBox);
        myCommands = makeTab(COMMANDS, dataTracker, myResources, myTextBox);

        for(int i = 0; i< 100; i++){
            myVariables.addContents("Blank line");
        }
        myVariables.addContents("Sample variable one");
        myVariables.addContents("practice variable two");
        myCommands.addContents("sample commmand");
        myMethods.addContents("sample method");
        myTabExplorer.getTabs().addAll(myVariables, myMethods, myCommands);
        grid.add(myTabExplorer, 8, 1, 5, 5);
    }

    private SLogoTab makeTab(String tabType, GUIdata data, ResourceBundle resources, TextArea commandLine){
        SLogoTab tab = new SLogoTab(resources.getString(tabType), data);
        tab.getContent().setOnMouseClicked(event -> {
            commandLine.setText(data.getMyTextToUpdate());
        });
        return tab;
    }

    private void createCanvas(GridPane grid) {
        myStackedCanvasPane = new StackedCanvasPane();
        grid.add(myStackedCanvasPane, 0, 1, 5, 1);
    }

    private void makeTextBox(GridPane grid){
        myTextBox = new TextArea();
        myTextBox.setPrefRowCount(4);
        myTextBox.setPrefColumnCount(10);
        myTextBox.setWrapText(true);
        grid.add(myTextBox, 0, 2, 4, 4);
    }

    private void setToolbar(GridPane grid) {
        myToolbar = new Toolbar(myStackedCanvasPane.getBackgroundColorAccess(),
                myStackedCanvasPane.getPenPropertiesAccess(), myStackedCanvasPane.getIconAccess());
        myTurtleIconChooser = myToolbar.getMyImageChooser();
        grid.add(myToolbar, 1, 0, 1, 1);
    }

    private void updateLanguage(String language){
        myLanguage = language;
        myResources = ResourceBundle.getBundle(myLanguage);
        myRunButton.setText(myResources.getString("Run"));
        myClearButton.setText(myResources.getString("Clear"));
        myHelpButton.setText(myResources.getString("Help"));
        myVariables.setText(myResources.getString("Variables"));
        myCommands.setText(myResources.getString("CommandHistory"));
        myMethods.setText(myResources.getString("Methods"));
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
        myClearButton = new ClearButton(myResources.getString("Clear"), myTextBox);
        grid.add(myClearButton, 4, 3);
        myHelpButton = new HelpButton(myResources.getString("Help"), myResources, helpMenuConsumer);
        grid.add(myHelpButton, 4, 4);
        myError = new ErrorDisplay();
        grid.add(myError, 0, 6);
    }

    public void setUpRunButton(GUIExecute ref){
        myRunButton = runButton(ref);
        myCurrentGUIGrid.add(myRunButton, 4, 5);
    }

    private Button runButton(GUIExecute ref){
        Button button = new Button(myResources.getString("Run"));
        button.setOnMouseClicked(event -> {
            commandToExecute = myTextBox.getText();
            myError.setText("");
            try {
                ref.executeCurrentCommand(commandToExecute);
            } catch(Exception InvalidCommandException) {
                System.out.println("Working on it");
                myError.setText("Please Enter a Valid SLogo Command");
            }
            addToCommandHistory(commandToExecute);
        });
        return button;
    }

    private void addToCommandHistory(String command){
        myCommands.addContents(command);
    }

    private Consumer<Void> helpMenuConsumer =  (x) -> {
        Alert help = showHelpMenu();
        help.show();
    };

    private Alert showHelpMenu(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(myResources.getString("Help"));
        alert.setHeaderText(myResources.getString("HelpHeader"));
       // alert.setContentText(myResources.getString("HelpInfo"));
        ScrollPane pane = new ScrollPane();
        pane.setContent(new Label(myResources.getString("HelpInfo")));
        alert.getDialogPane().setExpandableContent(pane);
        return alert;
    }

}
