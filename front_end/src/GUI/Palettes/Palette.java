package GUI.Palettes;

import GUI.GUI.GUIDisplay;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

/**
 * The Palette class maintains a map from the index of the palette element to the palette element to be displayed.
 * The class allows elements to be added to the palette by either adding a palette element at a specific index or
 * adding to the next index of the palette.
 * @param <R> R is the type of node that is the same as the type of the PaletteElements in the palette
 * Author: Ryan Culhane
 */
public class Palette<R extends Node> extends ScrollPane {

    private static final int SPACING = 5;
    private static final int EIGHT = 8;
    private VBox myVBox;
    private Map<Integer, PaletteElement<R>> myPaletteElements;

    /**
     * Palette Constructor
     */
    public Palette() {
        myVBox = new VBox(SPACING);
        myPaletteElements = new TreeMap<>();
        myVBox.getChildren().addAll(myPaletteElements.values());
        setContent(myVBox);
        setPrefWidth(GUIDisplay.SCENE_WIDTH * 1.0 / EIGHT);
    }

    /**
     * Adds a palette at its correct index by either adding an element or overwriting one
     * @param element element to be added
     */
    public void addPaletteElement(PaletteElement<R> element) {
        myPaletteElements.put(element.getMyIndex(), element);
        myVBox.getChildren().clear();
        myVBox.getChildren().addAll(myPaletteElements.values());
    }

    /**
     * Creates and adds a palette element at an unused index
     * @param elementComponent the node component of the element to be added
     * @return the index of the newly created palette element
     */
    public int addPaletteElement(R elementComponent) {
        int index = myPaletteElements.isEmpty() ? 1 : Collections.max(myPaletteElements.keySet()) + 1;
        PaletteElement<R> elementToAdd = new PaletteElement<>(index, elementComponent);
        addPaletteElement(elementToAdd);
        return index;
    }

    /**
     * Returns the content node of the palette element at index index
     * @param index of the palette element to query
     * @return content at index of the palette
     */
    public R getContent(int index) {
        return myPaletteElements.get(index).getMyDisplay();
    }

    /**
     * Ability to add an element to the palette without having access to the entire palette
     * @return access to the addPaletteElement method
     */
    public Function<R, Integer> getPaletteAccess() {
        return x -> addPaletteElement(x);
    }

}
