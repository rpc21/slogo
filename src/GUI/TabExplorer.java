package GUI;

import javafx.scene.control.TabPane;

public class TabExplorer extends TabPane implements GUIComponent {

    public TabExplorer(){
        super();
        setPrefSize(GUIDisplay.SCENE_WIDTH, GUIDisplay.SCENE_HEIGHT);
    }

    @Override
    public boolean isResizable(){
        return true;
    }

    @Override
    public void resize(double width, double height){
        super.setWidth(width);
        super.setHeight(height);
    }
}
