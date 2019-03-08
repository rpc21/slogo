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

public class PaletteTabExplorer extends TabExplorer {

    public static final String PALETTE_COLORS = "PaletteColors";
    private Palette<Rectangle> myColorPalette;
    private Palette<DisplayView> myTurtlePalette;
    private static final int FIRST_INDEX = 1;
    private static final int SECOND_INDEX = 2;
    private static final int THIRD_INDEX = 3;
    private static final int FOURTH_INDEX = 4;
    public static final int COLOR_PALETTE_WIDTH = 250;
    public static final int COLOR_PALETTE_HEIGHT = 50;
    private ResourceBundle myShapeResources;
    private ResourceBundle myColorResources;
//    public static final List<DisplayView> TURTLE_SHAPES = List.of(new BasicTurtleView(), new AdvancedTurtleView(),
//            new CuteTurtleView(), new GitLabView());
//    public static final List<Color> PALETTE_COLORS = List.of()

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
//        myTurtlePalette.addPaletteElement(new PaletteElement<>(FIRST_INDEX, new BasicTurtleView()));
//        myTurtlePalette.addPaletteElement(new PaletteElement<>(FOURTH_INDEX, new GitLabView()));
//        myTurtlePalette.addPaletteElement(new PaletteElement<>(THIRD_INDEX, new CuteTurtleView()));
//        myTurtlePalette.addPaletteElement(new PaletteElement<>(SECOND_INDEX, new AdvancedTurtleView()));
    }

    private DisplayView generateDisplayView(String name) {
        try {
//            System.out.println(name);
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
//        myColorPalette.addPaletteElement(new PaletteElement<>(FIRST_INDEX, new Rectangle(COLOR_PALETTE_WIDTH, COLOR_PALETTE_HEIGHT, Color.BLUE)));
//        myColorPalette.addPaletteElement(new PaletteElement<>(FOURTH_INDEX, new Rectangle(COLOR_PALETTE_WIDTH, COLOR_PALETTE_HEIGHT, Color.ORANGE)));
//        myColorPalette.addPaletteElement(new tavPaletteElement<>(SECOND_INDEX, new Rectangle(COLOR_PALETTE_WIDTH,
//        COLOR_PALETTE_HEIGHT, Color.GREEN)));
//        myColorPalette.addPaletteElement(new PaletteElement<>(THIRD_INDEX, new Rectangle(COLOR_PALETTE_WIDTH, COLOR_PALETTE_HEIGHT, Color.PINK)));
    }

    public Palette<Rectangle> getMyColorPalette() {
        return myColorPalette;
    }

    public Palette<DisplayView> getMyTurtlePalette() {
        return myTurtlePalette;
    }
}
