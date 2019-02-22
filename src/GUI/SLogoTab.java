package GUI;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.*;

public class SLogoTab extends Tab implements GUIComponent{

    VBox myVBoxOfStrings;
    ScrollPane myScrollPane;

    public SLogoTab(){
        super();
        initializeScrollPane();
        setContent(myScrollPane);
    }

    public SLogoTab(String tabTitle){
        super(tabTitle);
        initializeScrollPane();
        setContent(myScrollPane);
    }

    private void initializeScrollPane() {
        myVBoxOfStrings = new VBox();
        myScrollPane = new ScrollPane();
        myScrollPane.setContent(myVBoxOfStrings);
        myScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    public void addContents(String newContents){
        Label contents = new Label(newContents);
        contents.setWrapText(true);
        contents.setOnMouseClicked(event -> {
            System.out.println(contents.getText());
        });
        myVBoxOfStrings.getChildren().add(0, contents);
//        addContents(new Label(newContents));
    }

    public void addContents(Label newContents){
        newContents.setWrapText(true);
        myVBoxOfStrings.getChildren().add(0, newContents);
    }
}
