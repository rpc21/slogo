package GUI.Tabs;

import GUI.GUI.GUIdata;
import javafx.scene.control.Label;

/**
 * Tab Specific to user defined methods
 * Author: Louis Jensen
 */
public class TabMethods extends SLogoTabInteractive {

    /**
     * Default Constructor calls super class
     */
    public TabMethods() {
        super();
    }

    /**
     * Constructor calls super class
     * @param tabTitle Title of Tab
     * @param data Allows info to be passed to Display
     * @param title Title of editor box
     * @param content Contents of editor box
     */
    public TabMethods(String tabTitle, GUIdata data, String title, String content) {
        super(tabTitle, data, title, content);
    }

    @Override
    protected String convertResultToRunnableString(Label label, String value){
        String[] nameAndContents = label.getText().split(" ");
        String stringToRun = nameAndContents[0].substring(0,nameAndContents[0].length()-1) + " [ " + commaSeparatedToSpaces(value) + " ]";
        return stringToRun;
    }

    private String commaSeparatedToSpaces(String withCommas){
        String[] noCommas = withCommas.split(",");
        String withSpaces = "";
        for (String value : noCommas){
            value.trim();
            withSpaces += value + " ";
        }
        return withSpaces;
    }
}
