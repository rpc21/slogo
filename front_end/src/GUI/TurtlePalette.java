package GUI;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.*;
import java.util.function.Function;

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

    public String getContent(int index) {
        return myPaletteElements.get(index).getMyTurtleType();
    }

    public Function<Integer, String> turtleLookupAccess(){
        return (x) -> getContent(x);
    }
}