package GUI.Palettes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import java.util.Comparator;

/**
 * To be used in code masterpiece
 * PaletteElements extend HBoxes and contain a Text element for the index and a node of type R for the corresponding
 * contents.
 * @param <R> Type of node that is the content of each Palette element e.g. Rectangle, ImageView, etc.
 * Author: Ryan Culhane
 */
public class PaletteElement<R extends Node> extends HBox implements Comparator<PaletteElement> {

    private int myIndex;
    private R myContent;

    /**
     * PaletteElement constructor
     * @param index index of the palette element
     * @param content display content of the palette element
     */
    public PaletteElement(int index, R content){
        setAlignment(Pos.CENTER);
        setPadding(new Insets(0, 5, 0, 5));
        myIndex = index;
        myContent = content;
        getChildren().add(new Text(myIndex + ": "));
        getChildren().add(myContent);
    }

    int getMyIndex(){
        return myIndex;
    }

    R getMyDisplay(){
        return myContent;
    }

    /**
     * Implementation of the compare function to implement Comparator interface so palette elements can be put in a
     * tree set
     * @param o1 first palette element
     * @param o2 second palette element
     * @return negative value if o1 smaller, positive if o1 bigger and 0 if the same
     */
    @Override
    public int compare(PaletteElement o1, PaletteElement o2) {
        return Integer.compare(o1.myIndex, o2.myIndex);
    }

}
