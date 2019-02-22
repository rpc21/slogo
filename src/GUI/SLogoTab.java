package GUI;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
    }

    public void addContents(String newContents){
        addContents(new Text(newContents));
    }

    public void addContents(Text newContents){
        myVBoxOfStrings.getChildren().add(0, newContents);
    }
}
