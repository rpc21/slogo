package GUI.Tabs;

import GUI.Palettes.Palette;
import GUI.Palettes.PaletteElement;
import GUI.Turtle.DisplayView;
import GUI.Turtle.BasicTurtleView;
import GUI.Turtle.AdvancedTurtleView;
import GUI.Turtle.CuteTurtleView;
import GUI.Turtle.GitLabView;
import javafx.scene.control.Tab;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PaletteTabExplorer extends TabExplorer {

    private Palette<PaletteElement<Rectangle>, Rectangle> myColorPalette;
    private Palette<PaletteElement<DisplayView>, DisplayView> myTurtlePalette;

    public PaletteTabExplorer(){
        createColorPalette();
        createTurtlePalette();
        Tab turtleTab = new Tab("Turtle Palette", myTurtlePalette);
        Tab colorTab = new Tab("Color Palette", myColorPalette);
        getTabs().addAll(turtleTab, colorTab);
    }

    private void createTurtlePalette(){
        myTurtlePalette = new Palette<>();
        myTurtlePalette.addPaletteElement(new PaletteElement<>(1, new BasicTurtleView()));
        myTurtlePalette.addPaletteElement(new PaletteElement<>(4, new GitLabView()));
        myTurtlePalette.addPaletteElement(new PaletteElement<>(3, new CuteTurtleView()));
        myTurtlePalette.addPaletteElement(new PaletteElement<>(2, new AdvancedTurtleView()));
    }

    private void createColorPalette() {
        myColorPalette = new Palette<>();
        myColorPalette.addPaletteElement(new PaletteElement<>(1, new Rectangle(20, 25, Color.BLUE)));
        myColorPalette.addPaletteElement(new PaletteElement<>(4, new Rectangle(20, 25, Color.ORANGE)));
        myColorPalette.addPaletteElement(new PaletteElement<>(2, new Rectangle(20, 25, Color.GREEN)));
        myColorPalette.addPaletteElement(new PaletteElement<>(3, new Rectangle(20, 25, Color.PINK)));
    }

    public Palette<PaletteElement<Rectangle>, Rectangle> getMyColorPalette() {
        return myColorPalette;
    }

    public Palette<PaletteElement<DisplayView>, DisplayView> getMyTurtlePalette() {
        return myTurtlePalette;
    }
}
