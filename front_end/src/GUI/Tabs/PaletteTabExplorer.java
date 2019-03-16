package GUI.Tabs;

import GUI.Buttons.TurtleIconChooser;
import GUI.Palettes.Palette;
import GUI.Palettes.PaletteElement;
import GUI.Turtle.DisplayView;
import GUI.Turtle.BasicTurtleView;
import javafx.scene.control.Tab;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The PaletteTabExplorer is an extension of the TabExplorer class with one tab containing a tab of the different
 * icon options and another tab containing the different color options
 */
public class PaletteTabExplorer extends TabExplorer {

    private static final String PALETTE_COLORS = "PaletteColors";
    private Palette<Rectangle> myColorPalette;
    private Palette<DisplayView> myTurtlePalette;
    public static final int COLOR_PALETTE_WIDTH = 250;
    public static final int COLOR_PALETTE_HEIGHT = 50;
    private ResourceBundle myShapeResources;
    private ResourceBundle myColorResources;

    public PaletteTabExplorer(){
        myShapeResources = ResourceBundle.getBundle(TurtleIconChooser.SHAPES_BUNDLE);
        myColorResources = ResourceBundle.getBundle(PALETTE_COLORS);
        createColorPalette();
        createTurtlePalette();
        Tab turtleTab = new Tab("Turtle Palette", myTurtlePalette);
        Tab colorTab = new Tab("Color Palette", myColorPalette);
        getTabs().addAll(turtleTab, colorTab);
    }

    private void createTurtlePalette(){
        myTurtlePalette = new Palette<>();
        List<String> keys = Collections.list(myShapeResources.getKeys());
        for (int i = 0; i < keys.size(); i++){
            myTurtlePalette.addPaletteElement(generateDisplayView(myShapeResources.getString(keys.get(i))));
        }
    }

    private DisplayView generateDisplayView(String name) {
        try {
            var clazz = Class.forName("GUI.Turtle." + name.replaceAll(" ", ""));
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
            myColorPalette.addPaletteElement(new PaletteElement<>(i + 1, new Rectangle(COLOR_PALETTE_WIDTH,
                    COLOR_PALETTE_HEIGHT, Color.valueOf(myColorResources.getString(keys.get(i)).toUpperCase()))));
        }
    }

    public Palette<Rectangle> getMyColorPalette() {
        return myColorPalette;
    }

    public Palette<DisplayView> getMyTurtlePalette() {
        return myTurtlePalette;
    }
}
