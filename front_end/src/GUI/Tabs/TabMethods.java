package GUI.Tabs;

import GUI.GUI.GUIdata;
import javafx.scene.control.*;

public class TabMethods extends SLogoTabInteractive {

    public TabMethods() {
        super();
    }

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
