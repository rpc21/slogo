package GUI.Tabs;

import GUI.GUI.GUIdata;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

/**
 * Tabs to be used in the SLogo project.  Makes the tabs scrollable and so that contents get added to the top
 * Author: Louis Jensen
 */
public class SLogoTab extends Tab {

    protected GUIdata guiData;
    protected VBox myVBoxOfStrings;
    private ScrollPane myScrollPane;

    /**
     * Default SLogoTab Constructor
     */
    public SLogoTab(){
        super();
        initializeScrollPane();
        setContent(myScrollPane);
    }

    /**
     * Constructor that sets the title of the tab
     * @param tabTitle Tab title
     */
    public SLogoTab(String tabTitle){
        this();
        setText(tabTitle);
    }

    /**
     * Constructor that sets Title of tab and allows tab to pass info to command line
     * @param tabTitle Tab Title
     * @param data Object that can pass info to be displayed or executed by GUIDisplay
     */
    public SLogoTab(String tabTitle, GUIdata data){
        this(tabTitle);
        guiData = data;
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
        contents.setOnMouseClicked(event -> {
            guiData.setMyTextToUpdate(contents.getText());
        });
        myVBoxOfStrings.getChildren().add(0, contents);
    }

    /**
     * Removes all contents from tab
     */
    public void clearContents(){
        myVBoxOfStrings.getChildren().clear();
    }

}
