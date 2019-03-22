package GUI.GUI;

import GUI.Buttons.*;
import GUI.CanvasItems.StackedCanvasPane;
import GUI.Commands.*;
import GUI.Tabs.PaletteTabExplorer;
import GUI.Tabs.TabExplorer;
import GUI.Tabs.TurtleViewTabExplorer;
import apis.ImmutableVisualCommand;
import exceptions.external.InvalidInputException;
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

/**
 * The GUIDisplay class sets up the entire GUI by calling the constructors of all the gui components and placing them
 * in the appropriate locations of the grid pane.  The GUIDisplay class also maintains lists of command executable
 * components and language changeable components for batch updates to the language and to distribute access to the
 * parser effectively.
 * Author: Louis Jensen, Ryan Culhane
 */
public class GUIDisplay {

    private static final Language DEFAULT_LANGUAGE = Language.ENGLISH;
    private static final String CLEAR_SCREEN = "ClearScreen";
    private static final String S_LOGO = "SLogo";
    private static final String ARIAL = "Arial";
    private static final int SLOGO_FONT_SIZE = 50;
    private static final int SPACING = 10;
    private static final String UNDO = "Undo";
    private static final String NEW_WINDOW = "+";
    public static final String NOTHING_TO_UNDO = "Nothing to undo";
    private Stage myStage;
    private Scene myScene;
    private GridPane myRoot;
    private CommandLine myTextBox;
    private RunButton myRunButton;
    private ClearButton myClearButton;
    private HelpButton myHelpButton;
    private Language myLanguage;
    private Toolbar myToolbar;
    private StackedCanvasPane myStackedCanvasPane;
    private PaletteTabExplorer myPaletteTabExplorer;
    private TabExplorer myTabExplorer;
    private GridPane myCurrentGUIGrid;
    public static final int SCENE_WIDTH = 1200;
    public static final int SCENE_HEIGHT = 650;
    private GUIdata dataTracker;
    private ErrorDisplay myError;
    private TurtleViewTabExplorer myTurtleViewTabExplorer;
    private GUIExecute myGUIExecute;
    private NewWindowButton myNewWindowButton;
    private List<String> myListOfCommands;
    private UndoButton myUndoButton;
    private Delegator myDelegator;
    private List<LanguageChangeable> languageChangeableComponents;
    private List<CommandExecutable> commandExecutableComponents;

    /**
     * Constructor for GUIDisplay - creates all the gui component objects
     * @param stage stage on which display should be displayed
     */
    public GUIDisplay(Stage stage){
        myLanguage = DEFAULT_LANGUAGE;
        myStage = stage;
        myListOfCommands = new ArrayList<>();
        dataTracker = new GUIdata();
        myRoot = createGridPane();
        myDelegator = new Delegator(myStackedCanvasPane, myTabExplorer, myPaletteTabExplorer, myToolbar);
        myRoot.setGridLinesVisible(false);
        myScene = new Scene(myRoot, SCENE_WIDTH, SCENE_HEIGHT, Color.LIGHTGRAY);
        myStage.setScene(myScene);
    }

    void executeVisualCommands(List<ImmutableVisualCommand> myCommands){
        for (ImmutableVisualCommand c: myCommands) {
            c.execute(myDelegator);
        }
    }

    /**
     * Displays the GUI
     */
    public void display(){
        myStage.show();
    }

    private GridPane createGridPane(){
        GridPane grid = new GridPane();
        setGridProperties(grid);
        setTitle(grid);
        createCanvas(grid);
        makeCommandLine(grid);
        initializeButtons(grid);
        createRightSidePane(grid);
        setToolbar(grid);
        grid.setPrefSize(SCENE_WIDTH, SCENE_HEIGHT);
        myCurrentGUIGrid = grid;
        return grid;
    }

    private void createRightSidePane(GridPane grid) {
        createTabExplorers(grid);
    }

    private void createTabExplorers(GridPane grid) {
        createMethodsAndVariablesTabExplorer(grid);
        createTurtleViewTabExplorer(grid);
        createPaletteTabExplorer(grid);
    }

    private void createPaletteTabExplorer(GridPane grid) {
        myPaletteTabExplorer = new PaletteTabExplorer();
        grid.add(myPaletteTabExplorer, 2, 3, 2, 1);
    }

    private void createTurtleViewTabExplorer(GridPane grid) {
        myTurtleViewTabExplorer = new TurtleViewTabExplorer();
        grid.add(myTurtleViewTabExplorer, 2, 4, 2, 1);
        myStackedCanvasPane.grantTabAccess(myTurtleViewTabExplorer.getTabAccess());
    }

    private void makeUndoButton(GridPane grid){
        myUndoButton = new UndoButton(UNDO);
        myUndoButton.setOnMouseClicked(event -> undoCommand());
        grid.add(myUndoButton, 3, 0);
    }

    private void makeNewWindowButton(GridPane grid){
        myNewWindowButton = new NewWindowButton(NEW_WINDOW);
        grid.add(myNewWindowButton, 2,0);
    }

    public Button getNewWindowButton(){
        return myNewWindowButton;
    }

    private void createMethodsAndVariablesTabExplorer(GridPane grid) {
        myTabExplorer = new TabExplorer(dataTracker, myLanguage, myTextBox);
        grid.add(myTabExplorer, 2, 1, 2, 1);
    }

    private void createCanvas(GridPane grid) {
        myStackedCanvasPane = new StackedCanvasPane();
        grid.add(myStackedCanvasPane, 0, 1, 2, 4);
    }

    private void makeCommandLine(GridPane grid){
        myTextBox = new CommandLine();
        grid.add(myTextBox, 0, 5, 2, 4);
    }

    private void setToolbar(GridPane grid) {
        myToolbar = new Toolbar(this::updateLanguage, myPaletteTabExplorer.getMyColorPalette().getPaletteAccess());
        grid.add(myToolbar, 1, 0, 1, 1);
    }

    private void updateLanguage(Language language){
        myLanguage = language;
        for (LanguageChangeable component : languageChangeableComponents){
            component.setLanguage(language);
        }
    }

    private void setTitle(GridPane grid) {
        Text title = new Text(S_LOGO);
        title.setFont(Font.font(ARIAL, SLOGO_FONT_SIZE));
        grid.add(title, 0, 0);
    }

    private void setGridProperties(GridPane grid) {
        grid.setVgap(SPACING);
        grid.setHgap(SPACING);
        grid.setPadding(new Insets(SPACING, SPACING, SPACING, SPACING));
    }

    private void initializeButtons(GridPane grid){
        makeNewWindowButton(grid);
        makeUndoButton(grid);
        myClearButton = new ClearButton(myTextBox);
        grid.add(myClearButton, 2, 5);
        myHelpButton = new HelpButton();
        grid.add(myHelpButton, 2, 6);
        myRunButton =new RunButton(myTextBox);
        grid.add(myRunButton, 2, 7);
        myError = new ErrorDisplay();
        grid.add(myError, 0, 6);
    }

    /**
     * Gives this class ability to directly execute commands in backend
     * @param ref GUIExecute object that allows this class to run commands
     */
    public void setUpRunButton(GUIExecute ref){
        myGUIExecute = ref;
        myRunButton =new RunButton(myTextBox);
        groupGUIComponents();
        updateLanguage(Language.ENGLISH);
        giveAccessToRunCommands();
        myCurrentGUIGrid.add(myRunButton, 2, 7);
    }

    private void groupGUIComponents() {
        languageChangeableComponents = List.of(myTabExplorer, myStackedCanvasPane, myToolbar, myRunButton,
                myClearButton, myHelpButton);
        commandExecutableComponents = List.of(myTabExplorer, myStackedCanvasPane, myToolbar, myClearButton,
                myRunButton);
    }

    private void giveAccessToRunCommands() {
        for (CommandExecutable component : commandExecutableComponents){
            component.giveAbilityToRunCommands(x -> runCommand(myGUIExecute, x));
        }
    }

    private void runCommand(GUIExecute ref, String commandToExecute) {
        try {
            ref.executeCurrentCommand(commandToExecute, myLanguage.getLanguageString());
            myError.setText("");
        } catch(InvalidInputException e) {
            myError.setText(e.getReason());
        }
        addToCommandHistory(commandToExecute);
    }

    private void addToCommandHistory(String command){
        myTabExplorer.addToCommandHistory(command);
        myListOfCommands.add(command);
    }

    private void undoCommand(){
        List<String> copyOfCommandHistory = new ArrayList<>(myListOfCommands);
        try {
            copyOfCommandHistory.remove(copyOfCommandHistory.size() - 1);
            runCommand(myGUIExecute, myLanguage.getTranslatedWord(CLEAR_SCREEN));
            for(String command : copyOfCommandHistory){
                runCommand(myGUIExecute, command);
            }
        } catch (Exception e) {
            myError.setText(NOTHING_TO_UNDO);
        }
        myListOfCommands = copyOfCommandHistory;
    }

}