package GUI;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.*;

public class ColorPalette<T extends Node> extends ScrollPane {

    VBox myVBox;
    Map<Integer, ColorPaletteElement> myPaletteElements;

    public ColorPalette(){
        myVBox = new VBox();
        myPaletteElements = new TreeMap<>();
        myVBox.getChildren().addAll(myPaletteElements.values());
        setContent(myVBox);
    }

    public void addPaletteElement(ColorPaletteElement element){
        myPaletteElements.put(element.getMyIndex(), element);
        myVBox.getChildren().clear();
        myVBox.getChildren().addAll(myPaletteElements.values());
    }

    public void removePaletteElement(ColorPaletteElement element){
        myPaletteElements.remove(element.getMyIndex());
    }

}
