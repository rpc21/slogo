package GUI;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.function.Consumer;

public class Toolbar extends HBox implements GUIComponent {

    private ImageChooser<String> myImageChooser;
    private PenColorChooser myPenColorChooser;
    private BackgroundColorChooser myBackgroundColorChooser;
    private LanguageChooser myLanguageChooser;

    public Toolbar(){
        super();
        setPadding(new Insets(10, 10, 10, 10));
        setSpacing(10);
    }

    public Toolbar(Consumer<Color> backgroundColorAccess, Consumer<Paint> penColorAccess, Consumer<String> iconAccess){
        this();
      //  myLanguageChooser = createLanguageChooser();
        myBackgroundColorChooser = new BackgroundColorChooser(backgroundColorAccess);
        myPenColorChooser = new PenColorChooser(penColorAccess);
        myImageChooser = createImageChooser(iconAccess);
        getChildren().addAll(myBackgroundColorChooser, myPenColorChooser, myImageChooser);
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

//    private LanguageChooser createLanguageChooser() {
//        LanguageChooser languageChooser = new LanguageChooser<>();
//        languageChooser.getItems().addAll("English", "German", "French");
////        languageChooser.getSelectionModel().selectFirst();
//        return languageChooser;
//    }


}

