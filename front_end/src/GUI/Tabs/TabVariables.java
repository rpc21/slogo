package GUI.Tabs;

import GUI.GUI.GUIdata;
import javafx.scene.control.Label;

/**
 * Tab Specific to Variables
 * Author: Louis Jensen
 */
public class TabVariables extends SLogoTabInteractive {

    /**
     * Default constructor calls super class
     */
    public TabVariables() {
        super();
    }

    /**
     * Constructor calls super class
     * @param tabTitle Title of Tab
     * @param data Allows info to be passed to Display
     * @param title Title of editor box
     * @param content Contents of editor box
     */
    public TabVariables(String tabTitle, GUIdata data, String title, String content) {
        super(tabTitle, data, title, content);
    }

    @Override
    protected String convertResultToRunnableString(Label label, String value){
        String[] nameAndValue = label.getText().split(" ");
        String stringToRun = "make " + nameAndValue[0] + " " +value;
        return stringToRun;
    }
}
