package GUI.Tabs;

import GUI.Buttons.TurtleIconChooser;
import GUI.GUI.GUIDisplay;
import GUI.Palettes.Palette;
import GUI.Palettes.PaletteElement;
import GUI.Turtle.DisplayView;
import GUI.Turtle.BasicTurtleView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The PaletteTabExplorer is an extension of the TabExplorer class with one tab containing a tab of the different
 * icon options and another tab containing the different color options
 * Author: Ryan Culhane
 */
public class PaletteTabExplorer extends TabPane {

    private static final String PALETTE_COLORS = "PaletteColors";
    private static final String TURTLE_PALETTE_NAME = "Turtle Palette";
    private static final String COLOR_PALETTE_NAME = "Color Palette";
    private static final String TURTLE_PACKAGE_LOCATION = "GUI.Turtle.";
    private static final double WIDTH_SCALAR = 0.25;
    private static final double HEIGHT_SCALAR = 0.5;
    public static final int COLOR_PALETTE_WIDTH = 250;
    public static final int COLOR_PALETTE_HEIGHT = 50;

    private Palette<Rectangle> myColorPalette;
    private Palette<DisplayView> myTurtlePalette;
    private ResourceBundle myShapeResources;
    private ResourceBundle myColorResources;

    /**
     * PaletteTabExplorer Constructor
     * Sets preferences and initializes the color and turtle palettes
     */
    public PaletteTabExplorer(){
        setPrefSize(GUIDisplay.SCENE_WIDTH * WIDTH_SCALAR, GUIDisplay.SCENE_HEIGHT * HEIGHT_SCALAR);
        myShapeResources = ResourceBundle.getBundle(TurtleIconChooser.SHAPES_BUNDLE);
        myColorResources = ResourceBundle.getBundle(PALETTE_COLORS);
        createColorPalette();
        createTurtlePalette();
        Tab turtleTab = new Tab(TURTLE_PALETTE_NAME, myTurtlePalette);
        Tab colorTab = new Tab(COLOR_PALETTE_NAME, myColorPalette);
        getTabs().addAll(turtleTab, colorTab);
    }

    private void createTurtlePalette(){
        myTurtlePalette = new Palette<>();
        List<String> keys = Collections.list(myShapeResources.getKeys());
        for (String key : keys) {
            myTurtlePalette.addPaletteElement(generateDisplayView(myShapeResources.getString(key)));
        }
    }

    private DisplayView generateDisplayView(String name) {
        try {
            var clazz = Class.forName(TURTLE_PACKAGE_LOCATION + name.replaceAll(" ", ""));
            return (DisplayView) clazz.getDeclaredConstructor().newInstance();
        }
        catch (Exception e) {
            return new BasicTurtleView();
        }
    }

    private void createColorPalette() {
        myColorPalette = new Palette<>();
        List<String> keys = Collections.list(myColorResources.getKeys());
        for (int i = 0; i < keys.size(); i++){
            Color paletteColor = Color.valueOf(myColorResources.getString(keys.get(i)).toUpperCase());
            Rectangle paletteRectangleToAdd = new Rectangle(COLOR_PALETTE_WIDTH, COLOR_PALETTE_HEIGHT, paletteColor);
            myColorPalette.addPaletteElement(new PaletteElement<>(i + Palette.PALETTE_INDEX_OFFSET,
                    paletteRectangleToAdd));
        }
    }

    /**
     * Getter for color palette
     * @return myColorPalette
     */
    public Palette<Rectangle> getMyColorPalette() {
        return myColorPalette;
    }

    /**
     * Getter for turtle palette
     * @return myTurtlePalette
     */
    public Palette<DisplayView> getMyTurtlePalette() {
        return myTurtlePalette;
    }
}
