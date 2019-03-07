package GUI.Palettes;

import GUI.GUI.GUIDisplay;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.Map;
import java.util.TreeMap;

public class Palette<T extends PaletteElement<R>, R extends Node> extends ScrollPane {

    VBox myVBox;
    Map<Integer, T> myPaletteElements;

    public Palette(){
        myVBox = new VBox(5);
        myPaletteElements = new TreeMap<>();
        myVBox.getChildren().addAll(myPaletteElements.values());
        setContent(myVBox);
        setPrefWidth(GUIDisplay.SCENE_WIDTH * 1.0 / 8);
    }

    public void addPaletteElement(T element){
        myPaletteElements.put(element.getMyIndex(), element);
        myVBox.getChildren().clear();
        myVBox.getChildren().addAll(myPaletteElements.values());
    }

    public void removePaletteElement(T element){
        myPaletteElements.remove(element.getMyIndex());
    }

    public R getContent(int index) {
        return myPaletteElements.get(index).getMyDisplay();
    }

}
