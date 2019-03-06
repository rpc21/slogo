package GUI;

import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;

import java.util.ResourceBundle;
import java.util.function.Consumer;

public class TabExplorer extends TabPane implements GUIComponent, CommandExecutable, LanguageChangeable {

    private final String VARIABLES = "Variables";
    private final String METHODS = "Methods";
    private final String COMMANDS = "CommandHistory";
    private Consumer<String> myCommandAccess;
    private SLogoTab myVariables;
    private SLogoTab myCommands;
    private SLogoTab myMethods;
    private GUIdata myDataTracker;
    private CommandLine myTextBox;
    private Language myLanguage;


    public TabExplorer(){
        super();
        setPrefSize(GUIDisplay.SCENE_WIDTH * 1.0 / 4, GUIDisplay.SCENE_HEIGHT * 1.0/2);
    }

    public TabExplorer(GUIdata dataTracker, Language language, CommandLine textBox){
        super();
        myLanguage = language;
        myDataTracker = dataTracker;
        myTextBox = textBox;
        initializeTabs();
        setPrefSize(GUIDisplay.SCENE_WIDTH * 1.0 / 4, GUIDisplay.SCENE_HEIGHT * 1.0/2);
    }

    private void initializeTabs() {
        myVariables = makeVariablesTab(myDataTracker, myTextBox);
        myMethods = makeTab(METHODS, myDataTracker, myTextBox);
        myCommands = makeTab(COMMANDS, myDataTracker, myTextBox);

        myVariables.addContents(":var1");
        myVariables.addContents(":var2");
        myCommands.addContents("sample commmand");
        myMethods.addContents("sample method");
        getTabs().addAll(myVariables, myMethods, myCommands);
    }

    @Override
    public boolean isResizable(){
        return true;
    }

    @Override
    public void resize(double width, double height){
        super.setWidth(width);
        super.setHeight(height);
    }

    @Override
    public void giveAbilityToRunCommands(Consumer<String> commandAccess) {
        myCommandAccess = commandAccess;
    }

    @Override
    public void runCommand(String command) {
        myCommandAccess.accept(command);
    }

    @Override
    public void setLanguage(Language newLanguage) {
        myLanguage = newLanguage;
        myVariables.setText(myLanguage.getTranslatedWord(VARIABLES));
        myCommands.setText(myLanguage.getTranslatedWord(COMMANDS));
        myMethods.setText(myLanguage.getTranslatedWord(METHODS));
    }

    private SLogoTab makeVariablesTab(GUIdata dataTracker, TextArea myTextBox) {
        SLogoTabVariables tab = new SLogoTabVariables(myLanguage.getTranslatedWord(VARIABLES), dataTracker);
        tab.getContent().setOnMouseClicked(event -> {
            //(dataTracker.getMyCommandToRun());
            System.out.println("!!! " + dataTracker.getMyCommandToRun() + "!!!");
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

    public void addToCommandHistory(String command){
        myCommands.addContents(command);
    }


}
