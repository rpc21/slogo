package GUI.Buttons;

import GUI.Commands.CommandExecutable;
import GUI.Commands.Language;
import GUI.Commands.LanguageChangeable;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import java.util.function.Consumer;
import java.util.function.Function;

public class Toolbar extends HBox implements LanguageChangeable, CommandExecutable {

    //    private static final String TURTLE_ICON = "TurtleIcon";
    private static final String SET_BACKGROUND = "SetBackground";
    private static final String SET_PEN_COLOR = "SetPenColor";
    public static final int SPACING = 10;
    //    private ImageChooser<String> myImageChooser;
    private TurtleIconChooser myTurtleIconChooser;
    private ColorChooser myPenColorChooser;
    private ColorChooser myBackgroundColorChooser;
    private LanguageChooser myLanguageChooser;
    private Consumer<String> myCommandAccess;
    private Language myLanguage;

    public Toolbar(){
        super();
        myLanguage = Language.ENGLISH;
        setPadding(new Insets(SPACING, SPACING, SPACING, SPACING));
        setSpacing(SPACING);
    }

    public Toolbar(Consumer<Language> languageAccess, Function<Rectangle, Integer> colorPaletteAccess){
        this();
      //  myLanguageChooser = createLanguageChooser();
        myBackgroundColorChooser = new ColorChooser(colorPaletteAccess, SET_BACKGROUND);
        myPenColorChooser = new ColorChooser(colorPaletteAccess, SET_PEN_COLOR);
//        myImageChooser = createImageChooser(iconAccess);
        myTurtleIconChooser = new TurtleIconChooser(myCommandAccess);
        myLanguageChooser = new LanguageChooser(languageAccess);
        getChildren().addAll(myBackgroundColorChooser, myPenColorChooser, myTurtleIconChooser, myLanguageChooser);
    }

//    private ImageChooser<String> createImageChooser(Consumer<String> iconAccess) {
//        ImageChooser<String> imageChooser = new ImageChooser<>(iconAccess);
////        imageChooser.getItems().addAll(new AdvancedTurtleView().getPossibleImages());
//        imageChooser.getSelectionModel().selectFirst();
//        return imageChooser;
//    }

//    private TurtleIconChooser createTurtleIconChooser(Consumer<String> commandAccess){
//
//    }

//    public ImageChooser<String> getMyImageChooser(){
//        return myImageChooser;
//    }
//
//    public LanguageChooser getMyLanguageChooser(){
//        return myLanguageChooser;
//    }

    @Override
    public void setLanguage(Language newLanguage) {
        myLanguage = newLanguage;
        myPenColorChooser.setLanguage(newLanguage);
        myBackgroundColorChooser.setLanguage(newLanguage);
        myTurtleIconChooser.setLanguage(newLanguage);
        myLanguageChooser.setLanguage(newLanguage);
//        myImageChooser.setPromptText(myLanguage.getTranslatedWord(TURTLE_ICON));
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

//    private LanguageChooser createLanguageChooser() {
//        LanguageChooser languageChooser = new LanguageChooser<>();
//        languageChooser.getItems().addAll("English", "German", "French");
////        languageChooser.getSelectionModel().selectFirst();
//        return languageChooser;
//    }


}

