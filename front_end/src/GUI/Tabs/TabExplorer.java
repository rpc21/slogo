package GUI.Tabs;

import GUI.Commands.CommandExecutable;
import GUI.Commands.CommandLine;
import GUI.Commands.Language;
import GUI.Commands.LanguageChangeable;
import GUI.GUI.GUIDisplay;
import GUI.GUI.GUIdata;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

/*
 * Class that contains tabs with information to display to user
 */
public class TabExplorer extends TabPane implements CommandExecutable, LanguageChangeable {

    private static final String VARIABLES = "Variables";
    private static final String METHODS = "Methods";
    private static final String COMMANDS = "CommandHistory";
    private static final Double QUARTER = 1.0 / 4;
    private static final Double HALF = 1.0 / 2;
    private Consumer<String> myCommandAccess;
    private SLogoTab myVariables;
    private SLogoTab myCommands;
    private SLogoTab myMethods;
    private GUIdata myDataTracker;
    private CommandLine myTextBox;
    private Language myLanguage;
    private ResourceBundle myFilePaths;
    private static final String METHODS_TITLE = "Variables";
    private static final String METHODS_CONTENT = "Parameters Separated by Commas:";
    private static final String VARIABLES_TITLE = "Variable Editor";
    private static final String VARIABLES_CONTENT = "Enter new value:";

    /*
     * Default Constructor for TabExplorer calls TabPane constructor
     */
    public TabExplorer(){
        super();
        setPrefSize(GUIDisplay.SCENE_WIDTH * QUARTER, GUIDisplay.SCENE_HEIGHT * HALF);
    }

    /**
     * Constructor for TabExplorer calls TabPane constructor and establishes instance variables
     * @param dataTracker Passes data between TabExplorer and GUIDisplay
     * @param language Current language for user
     * @param textBox Box in which user enters commands
     */
    public TabExplorer(GUIdata dataTracker, Language language, CommandLine textBox){
        super();
        myLanguage = language;
        myDataTracker = dataTracker;
        myTextBox = textBox;
        myFilePaths = ResourceBundle.getBundle("FilePaths");
        initializeTabs();
        setPrefSize(GUIDisplay.SCENE_WIDTH * QUARTER, GUIDisplay.SCENE_HEIGHT * HALF);
    }

    private void initializeTabs() {
        myVariables = makeInteractiveTab(VARIABLES, myDataTracker, VARIABLES_TITLE, VARIABLES_CONTENT);
        myMethods = makeInteractiveTab(METHODS, myDataTracker, METHODS_TITLE, METHODS_CONTENT);
        myCommands = makeTab(COMMANDS, myDataTracker, myTextBox);
        getTabs().addAll(myVariables, myMethods, myCommands);
    }

    /**
     * TabExplorer is always resizeable
     * @return true
     */
    @Override
    public boolean isResizable(){
        return true;
    }

    /**
     * Resizes TabExplorer
     * @param width new width to set
     * @param height new height to set
     */
    @Override
    public void resize(double width, double height){
        super.setWidth(width);
        super.setHeight(height);
    }

    /**
     * Gives the class the ability to run commands by passing a consumer that takes a String and passes
     * the command to the parser to be run through the backend and have its effects displayed on the front end as
     * well as stored in the backend
     * @param commandAccess a consumer that feeds the command to the parser to run the command through the backend.
     */
    @Override
    public void giveAbilityToRunCommands(Consumer<String> commandAccess) {
        myCommandAccess = commandAccess;
    }

    /**
     * Method that calls the accept method on the consumer that was passed in the giveAbilityToRunCommands method
     * @param command the command to be run
     */
    @Override
    public void runCommand(String command) {
        myCommandAccess.accept(command);
    }

    /**
     * Change the language dependent features of the class to accommodate the new language
     * @param newLanguage new language that the program is being changed to
     */
    @Override
    public void setLanguage(Language newLanguage) {
        myLanguage = newLanguage;
        myVariables.setText(myLanguage.getTranslatedWord(VARIABLES));
        myCommands.setText(myLanguage.getTranslatedWord(COMMANDS));
        myMethods.setText(myLanguage.getTranslatedWord(METHODS));
    }

    private SLogoTab makeInteractiveTab(String tabType, GUIdata dataTracker, String title, String content) {
        SLogoTab tab;
        try {
            Class tabClass = Class.forName(myFilePaths.getString(tabType));
            Constructor constructor = tabClass.getConstructor(String.class, GUIdata.class, String.class, String.class);
            tab = (SLogoTabInteractive) constructor.newInstance(myLanguage.getTranslatedWord(tabType), dataTracker, title, content);
        } catch (Exception e) {
            tab = new SLogoTab();
        }
        tab.getContent().setOnMouseClicked(event -> {
            runCommand(dataTracker.getMyCommandToRun());
        });
        return tab;
    }

    private SLogoTab makeTab(String tabType, GUIdata data, TextArea commandLine){
        SLogoTab tab = new SLogoTab(myLanguage.getTranslatedWord(tabType), data);
        tab.getContent().setOnMouseClicked(event -> {
            commandLine.setText(data.getMyTextToUpdate());
        });
        return tab;
    }

    /**
     * Adds a command to the history when user clicks run
     * @param command Command to add to history
     */
    public void addToCommandHistory(String command){
        myCommands.addContents(command);
    }

    /**
     * Clears command history so it is empty
     */
    public void clearCommandHistory() {
        myCommands.clearContents();
    }

    /**
     * Adds a variable and value to the variables tab
     * @param name Name of variable
     * @param val Value of variable
     */
    public void addVariable(String name, Double val) {
        myVariables.addContents(name + " " + val);
    }

    /**
     * Adds a user defined command to the methods tab
     * @param name Name of user defined command
     * @param myVars variables necessary to run user defined command
     */
    public void addUserDefinedCommand(String name, List<String> myVars) {
        myMethods.addContents(name + ": " + String.join(" ", myVars));
    }
}
