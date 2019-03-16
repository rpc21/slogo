package GUI.GUI;

import GUI.CanvasItems.StackedCanvasPane;
import GUI.Palettes.PaletteElement;
import GUI.Tabs.PaletteTabExplorer;
import GUI.Tabs.TabExplorer;
import apis.VisualUpdateAPI;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

/**
 * The Delegator class implements the VisualUpdateAPI and uses a delegator design pattern to distribute the
 * functionality of the visual commands to the classes that can update the appropriate parts of the screen.  Hence,
 * the Delegator class has access to the the StackedCanvasPane, TabExplorer, and PaletteTabExplorer where the visual
 * updates occur.  Visual updates happen in a tree kind of manner of delegation and the Delegator class can be seen
 * as the root of this delegation tree
 */
public class Delegator implements VisualUpdateAPI {

    private StackedCanvasPane myStackedCanvasPane;
    private TabExplorer myTabExplorer;
    private PaletteTabExplorer myPaletteTabExplorer;


    public Delegator(StackedCanvasPane canvasPane, TabExplorer tabExplorer, PaletteTabExplorer paletteTabExplorer){
        myStackedCanvasPane = canvasPane;
        myTabExplorer = tabExplorer;
        myPaletteTabExplorer = paletteTabExplorer;
    }


    @Override
    public void addTurtles(int numTurtles) {
        myStackedCanvasPane.addTurtles(numTurtles);
    }

    @Override
    public void setActiveTurtles(List<Integer> activeTurtleIDs) { myStackedCanvasPane.setActiveTurtles(activeTurtleIDs);}

    @Override
    public void addVariable(String name, Double val) {
        myTabExplorer.addVariable(name, val);
    }

    @Override
    public void addMethod(String name, List<String> myVars) {
        myTabExplorer.addUserDefinedCommand(name, myVars);
    }

    @Override
    public void turtleMove(int id, double x, double y) {
        myStackedCanvasPane.turtleMove(id, x, y);
    }

    @Override
    public void turtleTurn(int id, double degrees) {
        myStackedCanvasPane.turtleTurn(id, degrees);
    }

    @Override
    public void setPenUp(int id) {
        myStackedCanvasPane.setPenUp(id);
    }

    @Override
    public void setPenDown(int id) {
        myStackedCanvasPane.setPenDown(id);
    }

    @Override
    public void showTurtle(int id) {
        myStackedCanvasPane.showTurtle(id);
    }

    @Override
    public void hideTurtle(int id) {
        myStackedCanvasPane.hideTurtle(id);
    }

    @Override
    public void setOrientation(int id, double degrees) {
        myStackedCanvasPane.setOrientation(id, degrees);
    }

    @Override
    public void setTowards(int id, double degrees) {
        myStackedCanvasPane.setTowards(id, degrees);
    }

    @Override
    public void setLocation(int id, double x, double y) {
        myStackedCanvasPane.setLocation(id, x, y);
    }

    @Override
    public void goHome(int id) {
        myStackedCanvasPane.goHome(id);
    }

    @Override
    public void clearScreen() {
        myStackedCanvasPane.clearScreen();
        myTabExplorer.clearCommandHistory();
    }

    @Override
    public void setBackgroundColor(int index) {
        myStackedCanvasPane.getBackgroundColorAccess().accept(myPaletteTabExplorer.getMyColorPalette().getContent(index).getFill());
    }

    @Override
    public void setPenColor(int id, int index) {
        myStackedCanvasPane.setPenColor(id, myPaletteTabExplorer.getMyColorPalette().getContent(index).getFill());
    }

    @Override
    public void setPenSize(int id, double pixels) {
        myStackedCanvasPane.setPenSize(id, pixels);
    }

    @Override
    public void setShape(int id, int index) {
        myStackedCanvasPane.setTurtleShape(id,
                myPaletteTabExplorer.getMyTurtlePalette().getContent(index).getClass().getName());
    }

    @Override
    public void setPalette(int index, int r, int b, int g) {
        myPaletteTabExplorer.getMyColorPalette().addPaletteElement(new PaletteElement<>(index,
                new Rectangle(PaletteTabExplorer.COLOR_PALETTE_WIDTH, PaletteTabExplorer.COLOR_PALETTE_HEIGHT,
                        Color.rgb(r, g, b))));
    }

}
