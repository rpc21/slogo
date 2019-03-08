package GUI.Buttons;

import GUI.Commands.Language;
import GUI.Commands.LanguageChangeable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import java.util.ResourceBundle;
import java.util.function.Consumer;

public class HelpButton extends Button implements LanguageChangeable {

    private static final String HELP_BUTTON_NAME = "Help";
    private Language myLanguage;

    //TODO: I think we can make this button know its own name and just setText(HELP_NAME), don't need resources, and
    // can just have setOnAction create the alert that is stored in this class
//    public HelpButton(String text, ResourceBundle resources, Consumer<Void> consumer){
//        myLanguage = Language.ENGLISH;
//        this.setText(myLanguage.getTranslatedWord(HELP_BUTTON_NAME));
//        this.setOnAction(event ->{
//            Alert help = showHelpMenu();
//            help.show();
//        });
//    }

    public HelpButton(){
        myLanguage = Language.ENGLISH;
        this.setText(myLanguage.getTranslatedWord(HELP_BUTTON_NAME));
        this.setOnAction(event ->{
            Alert help = showHelpMenu();
            help.show();
        });
    }

    @Override
    public void setLanguage(Language newLanguage) {
        myLanguage = newLanguage;
        this.setText(myLanguage.getTranslatedWord(HELP_BUTTON_NAME));
    }

    private Alert showHelpMenu(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(myLanguage.getTranslatedWord("Help"));
        alert.setHeaderText(myLanguage.getTranslatedWord("HelpHeader"));
        ScrollPane pane = new ScrollPane();
        pane.setContent(new Label(myLanguage.getTranslatedWord("HelpInfo")));
        alert.getDialogPane().setExpandableContent(pane);
        return alert;
    }

}
