package GUI.Turtle;

import GUI.Commands.IntegerCommandInputDialog;
import GUI.Commands.Language;
import GUI.Commands.LanguageChangeable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class SubContextMenu extends Menu implements LanguageChangeable {

    private static final String TURTLE_CONTEXT_NAME = "Interactive Turtle Commands";

    private Language myLanguage;
    private Consumer<String> myCommandAccess;
    protected List<String> contextSpecificCommandsWithInput;
    protected List<String> noInputContextSpecificCommands;
    protected List<String> allContextSpecificCommands;

    public SubContextMenu(Language language, Consumer<String> commandAccess) {
        setText(TURTLE_CONTEXT_NAME);
        myLanguage = language;
        myCommandAccess = commandAccess;
        contextSpecificCommandsWithInput = new ArrayList<>();
        noInputContextSpecificCommands = new ArrayList<>();
        allContextSpecificCommands = new ArrayList<>();
        defineContextSpecificCommands();
        initializeMenuItems();
    }

    protected abstract void defineContextSpecificCommands();

    private void initializeMenuItems() {
        for (String command : contextSpecificCommandsWithInput) {
            MenuItem item = new MenuItem(myLanguage.getTranslatedWord(command));
            item.setOnAction(e -> promptForFurtherInput(command));
            this.getItems().add(item);
        }
        for (String command : noInputContextSpecificCommands){
            MenuItem item = new MenuItem(myLanguage.getTranslatedWord(command));
            item.setOnAction(e -> myCommandAccess.accept(myLanguage.getTranslatedWord(command)));
            this.getItems().add(item);
        }
    }

    private void promptForFurtherInput(String text) {
        IntegerCommandInputDialog dialog = new IntegerCommandInputDialog();
        Optional<String> input = dialog.showAndWait();
        input.ifPresent(argument -> myCommandAccess.accept(myLanguage.getTranslatedWord(text) + " " + argument));
    }

    @Override
    public void setLanguage(Language newLanguage) {
        myLanguage = newLanguage;
        for (int i = 0; i<getItems().size(); i++){
            getItems().get(i).setText(myLanguage.getTranslatedWord(allContextSpecificCommands.get(i)));
        }
    }
}

