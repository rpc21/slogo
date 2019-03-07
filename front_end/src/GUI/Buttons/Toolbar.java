package GUI.Buttons;

import GUI.Commands.Language;
import GUI.Commands.LanguageChangeable;
import GUI.GUI.GUIComponent;
import GUI.Turtle.BasicTurtleView;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.function.Consumer;

public class Toolbar extends HBox implements GUIComponent, LanguageChangeable {

    private static final String TURTLE_ICON = "TurtleIcon";
    private ImageChooser<String> myImageChooser;
    private PenColorChooser myPenColorChooser;
    private BackgroundColorChooser myBackgroundColorChooser;
    private LanguageChooser myLanguageChooser;
    private Language myLanguage;

    public Toolbar(){
        super();
        myLanguage = Language.ENGLISH;
        setPadding(new Insets(10, 10, 10, 10));
        setSpacing(10);
    }

    public Toolbar(Consumer<Paint> backgroundColorAccess, Consumer<Paint> penColorAccess, Consumer<String> iconAccess
            , Consumer<Language> languageAccess){
        this();
      //  myLanguageChooser = createLanguageChooser();
        myBackgroundColorChooser = new BackgroundColorChooser(backgroundColorAccess);
        myPenColorChooser = new PenColorChooser(penColorAccess);
        myImageChooser = createImageChooser(iconAccess);
        myLanguageChooser = new LanguageChooser(languageAccess);
        getChildren().addAll(myBackgroundColorChooser, myPenColorChooser, myImageChooser, myLanguageChooser);
    }

    private ImageChooser<String> createImageChooser(Consumer<String> iconAccess) {
        ImageChooser<String> imageChooser = new ImageChooser<>(iconAccess);
        imageChooser.getItems().addAll(new BasicTurtleView().getPossibleImages());
        //imageChooser.getItems().addAll(new AdvancedTurtleView().getPossibleImages());
        imageChooser.getSelectionModel().selectFirst();
        return imageChooser;
    }

    public ImageChooser<String> getMyImageChooser(){
        return myImageChooser;
    }

    public LanguageChooser getMyLanguageChooser(){
        return myLanguageChooser;
    }

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

