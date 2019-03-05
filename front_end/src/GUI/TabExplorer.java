package GUI;

import javafx.scene.control.TabPane;

import java.util.function.Consumer;

public class TabExplorer extends TabPane implements GUIComponent, CommandExecutable, LanguageChangeable {

    private Consumer<String> myCommandAccess;

    public TabExplorer(){
        super();
        setPrefSize(GUIDisplay.SCENE_WIDTH, GUIDisplay.SCENE_HEIGHT);
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
        //TODO: Move from GUIDisplay to here
    }
}
