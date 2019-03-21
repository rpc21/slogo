package GUI.Buttons;

import GUI.Commands.CommandExecutable;
import GUI.Commands.Language;
import GUI.Commands.LanguageChangeable;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * The Toolbar class contains the the LanguageChooser, the ColorChoosers for the background color and pen color and
 * the turtle icon chooser. Class is used to manage the various choosers in bulk and isolate the functionality of
 * these user buttons to one larger class.
 * Author: Ryan Culhane, Louis Jensen
 */
public class Toolbar extends HBox implements LanguageChangeable, CommandExecutable {

    private static final String SET_BACKGROUND = "SetBackground";
    private static final String SET_PEN_COLOR = "SetPenColor";
    private static final int SPACING = 10;
    public static final Color BACKGROUND_COLOR_DEFAULT = Color.WHITE;
    public static final Color PEN_COLOR_DEFAULT = Color.BLACK;
    private TurtleIconChooser myTurtleIconChooser;
    private ColorChooser myPenColorChooser;
    private ColorChooser myBackgroundColorChooser;
    private LanguageChooser myLanguageChooser;
    private Consumer<String> myCommandAccess;
    private Language myLanguage;

    /**
     * Private constructor for the Toolbar
     */
    private Toolbar(){
        super();
        myLanguage = Language.ENGLISH;
        setPadding(new Insets(SPACING, SPACING, SPACING, SPACING));
        setSpacing(SPACING);
    }

    /**
     * Constructor for the Toolbar that calls constructors for the four components of the toolbar and adds them to
     * the children of the Toolbar
     * @param languageAccess consumer that takes in a Language and changes the language of all LanguageChangeable
     *                       components of the gui - passed to the LanguageChooser constructor
     * @param colorPaletteAccess a function that can add a PaletteElement to the colorPalette and returns the index
     *                           of where it was added - passed to the ColorChooser constructors
     */
    public Toolbar(Consumer<Language> languageAccess, Function<Rectangle, Integer> colorPaletteAccess){
        this();
        myBackgroundColorChooser = new ColorChooser(colorPaletteAccess, SET_BACKGROUND);
        myPenColorChooser = new ColorChooser(colorPaletteAccess, SET_PEN_COLOR);
        myTurtleIconChooser = new TurtleIconChooser(myCommandAccess);
        myLanguageChooser = new LanguageChooser(languageAccess);
        setToDefaults();
        getChildren().addAll(myBackgroundColorChooser, myPenColorChooser, myTurtleIconChooser, myLanguageChooser);
    }

    /**
     * Change the language dependent features of the class to accommodate the new language
     * @param newLanguage new language that the program is being changed to
     */
    @Override
    public void setLanguage(Language newLanguage) {
        myLanguage = newLanguage;
        myPenColorChooser.setLanguage(newLanguage);
        myBackgroundColorChooser.setLanguage(newLanguage);
        myTurtleIconChooser.setLanguage(newLanguage);
        myLanguageChooser.setLanguage(newLanguage);
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
        myTurtleIconChooser.giveAbilityToRunCommands(commandAccess);
        myBackgroundColorChooser.giveAbilityToRunCommands(commandAccess);
        myPenColorChooser.giveAbilityToRunCommands(commandAccess);
    }

    /**
     * Method that calls the accept method on the consumer that was passed in the giveAbilityToRunCommands method
     * @param command the command to be run
     */
    @Override
    public void runCommand(String command) {
        myCommandAccess.accept(command);
    }

    public void setToDefaults() {
        myTurtleIconChooser.setText(myTurtleIconChooser.getItems().get(0).getText());
        myBackgroundColorChooser.setValue(BACKGROUND_COLOR_DEFAULT);
        myPenColorChooser.setValue(PEN_COLOR_DEFAULT);
    }
}

