package GUI;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.*;

public class TurtlePalette<T extends Node> extends ScrollPane {

    VBox myVBox;
    Map<Integer, TurtlePaletteElement> myPaletteElements;

    public TurtlePalette(){
        myVBox = new VBox();
        myPaletteElements = new TreeMap<>();
        myVBox.getChildren().addAll(myPaletteElements.values());
        setContent(myVBox);
    }

    public void addPaletteElement(TurtlePaletteElement element){
        myPaletteElements.put(element.getMyIndex(), element);
        myVBox.getChildren().clear();
        myVBox.getChildren().addAll(myPaletteElements.values());
    }

    public void removePaletteElement(TurtlePaletteElement element){
        myPaletteElements.remove(element.getMyIndex());
    }

}