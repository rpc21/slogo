package GUI.Buttons;

import GUI.Commands.Language;
import GUI.Commands.LanguageChangeable;
import GUI.GUI.GUIComponent;
import GUI.Turtle.BasicTurtleView;
import GUI.Turtle.DisplayView;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.function.Consumer;

public class Toolbar extends HBox implements GUIComponent, LanguageChangeable {

    private static final String TURTLE_ICON = "TurtleIcon";
    private ImageChooser<String> myImageChooser;
    private ColorChooser myPenColorChooser;
    private ColorChooser myBackgroundColorChooser;
    private LanguageChooser myLanguageChooser;
    private Language myLanguage;

    public Toolbar(){
        super();
        myLanguage = Language.ENGLISH;
        setPadding(new Insets(10, 10, 10, 10));
        setSpacing(10);
    }

    public Toolbar(Consumer<Paint> backgroundColorAccess, Consumer<Paint> penColorAccess, Consumer<String> iconAccess
            , Consumer<Language> languageAccess, Consumer<Rectangle> colorPaletteAccess){
        this();
      //  myLanguageChooser = createLanguageChooser();
        myBackgroundColorChooser = new ColorChooser(colorPaletteAccess, backgroundColorAccess);
        myPenColorChooser = new ColorChooser(colorPaletteAccess, penColorAccess);
        myImageChooser = createImageChooser(iconAccess);
        myLanguageChooser = new LanguageChooser(languageAccess);
        getChildren().addAll(myBackgroundColorChooser, myPenColorChooser, myImageChooser, myLanguageChooser);
    }

    private ImageChooser<String> createImageChooser(Consumer<String> iconAccess) {
        ImageChooser<String> imageChooser = new ImageChooser<>(iconAccess);
        imageChooser.getItems().addAll(DisplayView.POSSIBLE_IMAGES);
//        imageChooser.getItems().addAll(new AdvancedTurtleView().getPossibleImages());
        imageChooser.getSelectionModel().selectFirst();
        return imageChooser;
    }

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
        myImageChooser.setPromptText(myLanguage.getTranslatedWord(TURTLE_ICON));
    }

//    private LanguageChooser createLanguageChooser() {
//        LanguageChooser languageChooser = new LanguageChooser<>();
//        languageChooser.getItems().addAll("English", "German", "French");
////        languageChooser.getSelectionModel().selectFirst();
//        return languageChooser;
//    }


}

