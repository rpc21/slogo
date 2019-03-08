package GUI.Palettes;

import GUI.GUI.GUIDisplay;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;

public class Palette<R extends Node> extends ScrollPane {

    VBox myVBox;
    Map<Integer, PaletteElement<R>> myPaletteElements;

    public Palette() {
        myVBox = new VBox(5);
        myPaletteElements = new TreeMap<>();
        myVBox.getChildren().addAll(myPaletteElements.values());
        setContent(myVBox);
        setPrefWidth(GUIDisplay.SCENE_WIDTH * 1.0 / 8);
    }

    public void addPaletteElement(PaletteElement<R> element) {
        myPaletteElements.put(element.getMyIndex(), element);
        myVBox.getChildren().clear();
        myVBox.getChildren().addAll(myPaletteElements.values());
    }

    public int addPaletteElement(R elementComponent) {
        int index = myPaletteElements.isEmpty() ? 1 : Collections.max(myPaletteElements.keySet()) + 1;
//        if (myPaletteElements.isEmpty()) {
//            index = 1;
//        } else {
//            index = Collections.max(myPaletteElements.keySet()) + 1;
//        }
        PaletteElement<R> elementToAdd = new PaletteElement<>(index, elementComponent);
        addPaletteElement(elementToAdd);
        return index;
    }

    public void removePaletteElement(PaletteElement<R> element) {
        myPaletteElements.remove(element.getMyIndex());
    }

    public R getContent(int index) {
        return myPaletteElements.get(index).getMyDisplay();
    }

    public Function<R, Integer> getPaletteAccess() {
        return x -> addPaletteElement(x);
    }

}
