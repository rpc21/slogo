package GUI;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.util.ResourceBundle;
import java.util.function.Consumer;


public class LanguageChooser extends MenuButton implements GUIComponent {
    private ResourceBundle myResources;
    private Consumer<String> myConsumer;
    private String myLanguage;

    LanguageChooser(Consumer<String> consumer){
        myConsumer = consumer;
        myLanguage = "English";
        myResources = ResourceBundle.getBundle(myLanguage);
        buildLanguageChooser();
    }

    private void buildLanguageChooser() {
        // LanguageChooser menuButton = new LanguageChooser();
        this.setText(myResources.getString("Language"));
        MenuItem english = new MenuItem("English");
        english.setOnAction(event -> {
            myLanguage = "English";
            myConsumer.accept(myLanguage);
       //     myLanguageChooser.setText(myLanguage);
        });

        MenuItem french = new MenuItem("Français");
        french.setOnAction(event -> {
            myLanguage = "French";
            myConsumer.accept(myLanguage);
      //      myLanguageChooser.setText("Français");
        });
        MenuItem chinese = new MenuItem("Zhōngwén");
        chinese.setOnAction(event -> {
            myLanguage = "Chinese";
            myConsumer.accept(myLanguage);
         //   myLanguageChooser.setText("Zhōngwén");
        });
        MenuItem german = new MenuItem("Deutsche");
        german.setOnAction(event -> {
            myLanguage = "German";
            myConsumer.accept(myLanguage);
         //   myLanguageChooser.setText("Deutsche");
        });
        MenuItem italian = new MenuItem("Italiano");
        italian.setOnAction(event -> {
            myLanguage = "Italian";
            myConsumer.accept(myLanguage);
         //   myLanguageChooser.setText("Italiano");
        });
        MenuItem portuguese = new MenuItem("Português");
        portuguese.setOnAction(event -> {
            myLanguage = "Portuguese";
            myConsumer.accept(myLanguage);
         //   myLanguageChooser.setText("Português");
        });
        MenuItem russian = new MenuItem("Russkiy");
        russian.setOnAction(event -> {
            myLanguage = "Russian";
            myConsumer.accept(myLanguage);
        //    myLanguageChooser.setText("Russkiy");
        });
        MenuItem spanish = new MenuItem("Español");
        spanish.setOnAction(event -> {
            myLanguage = "Spanish";
            myConsumer.accept(myLanguage);
            //myLanguageChooser.setText("Español");
        });
        MenuItem urdu = new MenuItem("Urdu");
        urdu.setOnAction(event -> {
            myLanguage = "Urdu";
            myConsumer.accept(myLanguage);
//            myLanguageChooser.setText("Urdu");
        });
        this.getItems().addAll(english, french, chinese, german, italian, portuguese, russian, spanish, urdu);
       // myToolbar.getChildren().add(menuButton);
        return;
    }
}
