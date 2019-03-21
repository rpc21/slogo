package GUI.Tabs;

import GUI.GUI.GUIdata;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

public class BasicTab extends Tab {

    protected VBox myVBoxOfStrings;
    protected ScrollPane myScrollPane;

    /**
     * Default SLogoTab Constructor
     */
    public BasicTab(){
        super();
        initializeScrollPane();
        setContent(myScrollPane);
    }

    /**
     * Constructor that sets the title of the tab
     * @param tabTitle Tab title
     */
    public BasicTab(String tabTitle){
        this();
        setText(tabTitle);
    }

    private void initializeScrollPane() {
        myVBoxOfStrings = new VBox();
        myScrollPane = new ScrollPane();
        myScrollPane.setContent(myVBoxOfStrings);
        myScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    /**
     * Adds a new item to tab
     * @param newContents String to add to tab display
     */
    public void addContents(String newContents){
        Label contents = new Label(newContents);
        contents.setWrapText(true);
        myVBoxOfStrings.getChildren().add(0, contents);
    }

    /**
     * Removes all contents from tab
     */
    public void clearContents(){
        myVBoxOfStrings.getChildren().clear();
    }

}
