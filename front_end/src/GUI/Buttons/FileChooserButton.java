//package GUI.Buttons;
//
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.control.Button;
//import javafx.stage.FileChooser;
//import javafx.stage.Stage;
//
//import java.io.File;
//
//public class FileChooserButton extends Button {
//
//    final FileChooser fileChooser = new FileChooser();
//
//    public FileChooserButton(Stage stage){
//        setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                File file = fileChooser.showOpenDialog(stage);
//                if (file != null) {
//                    openFile(file);
//                }
//            }
//        });
//    }
//
//
//}
