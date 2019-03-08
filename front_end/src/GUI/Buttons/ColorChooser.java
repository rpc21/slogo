package GUI.Buttons;

import GUI.Commands.CommandExecutable;
import GUI.Commands.Language;
import GUI.Commands.LanguageChangeable;
import GUI.GUI.GUIComponent;
import GUI.Tabs.PaletteTabExplorer;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.function.Consumer;
import java.util.function.Function;

public class ColorChooser extends ColorPicker implements GUIComponent, LanguageChangeable, CommandExecutable {

    private Language myLanguage;
    private String myCommandTemplate;
    private Consumer<String> myCommandAccess;

    public ColorChooser(){
        super();
    }

    public ColorChooser(Function<Rectangle, Integer> colorPaletteAccess, String commandTemplate){
        super();
        myLanguage = Language.ENGLISH;
        myCommandTemplate = commandTemplate;
        setOnAction(event -> {
            int index = colorPaletteAccess.apply(new Rectangle(PaletteTabExplorer.COLOR_PALETTE_WIDTH,
                    PaletteTabExplorer.COLOR_PALETTE_HEIGHT, getValue()));
            runCommand(myLanguage.getTranslatedWord(myCommandTemplate) + " " + index);
        });
    }

    @Override
    public void setLanguage(Language newLanguage) {
        myLanguage = newLanguage;
    }

    @Override
    public void giveAbilityToRunCommands(Consumer<String> commandAccess) {
        myCommandAccess = commandAccess;
    }

    @Override
    public void runCommand(String command) {
        myCommandAccess.accept(command);
    }
}
