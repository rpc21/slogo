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

    /**
     * Constructor for Delegator
     * @param canvasPane current StackedCanvasPane on the screen
     * @param tabExplorer current TabExplorer user can interact with
     * @param paletteTabExplorer current PalleteTabExplorer user can interact with
     */
    public Delegator(StackedCanvasPane canvasPane, TabExplorer tabExplorer, PaletteTabExplorer paletteTabExplorer){
        myStackedCanvasPane = canvasPane;
        myTabExplorer = tabExplorer;
        myPaletteTabExplorer = paletteTabExplorer;
    }

    /**
     * Create numTurtles number of turtles and add them to the canvas at position 0,0
     * All the turtles that are created are assigned an id that allows the user to access that turtle
     * and give instructions specific to the turtle
     * @param numTurtles number of turtles to be added
     */
    @Override
    public void addTurtles(int numTurtles) {
        myStackedCanvasPane.addTurtles(numTurtles);
    }

    /**
     * Give the turtles with the IDs in the the activeTurtleIDs list the appearance of being active
     * @param activeTurtleIDs list of IDs of the turtles that should have the appearance of being active
     */
    @Override
    public void setActiveTurtles(List<Integer> activeTurtleIDs) { myStackedCanvasPane.setActiveTurtles(activeTurtleIDs);}

    /**
     * Add a variable to the variable explorer that the user can click on and change
     * @param name the name of the variable
     * @param val the value stored in the variable name
     */
    @Override
    public void addVariable(String name, Double val) {
        myTabExplorer.addVariable(name, val);
    }

    /**
     * Add a method to the list of user defined methods.  Include the name of the methods as well as the names of the
     * parameters of the method
     * @param name name of the user defined method
     * @param myVars list of the parameters for the method
     */
    @Override
    public void addMethod(String name, List<String> myVars) {
        myTabExplorer.addUserDefinedCommand(name, myVars);
    }

    /**
     * Makes the turtle with id id move by x, y
     * @param id turtle to move
     * @param x x move
     * @param y y move
     */
    @Override
    public void turtleMove(int id, double x, double y) {
        myStackedCanvasPane.turtleMove(id, x, y);
    }

    /**
     * Make the turtle with id id turn degrees number of degrees
     * @param id turtle id of the turtle to turn
     * @param degrees number of degrees the turtle should change
     */
    @Override
    public void turtleTurn(int id, double degrees) {
        myStackedCanvasPane.turtleTurn(id, degrees);
    }

    /**
     * Have the turtle with turtle id id raise its pen
     * @param id turtle id of the turtle to raise its pen
     */
    @Override
    public void setPenUp(int id) {
        myStackedCanvasPane.setPenUp(id);
    }

    /**
     * Have the turtle with turtle id id set its pen down
     * @param id turtle id of the turtle to put its pen down
     */
    @Override
    public void setPenDown(int id) {
        myStackedCanvasPane.setPenDown(id);
    }

    /**
     * Make the turtle with turtle id id appear on the screen
     * @param id turtle id of the turtle to be shown
     */
    @Override
    public void showTurtle(int id) {
        myStackedCanvasPane.showTurtle(id);
    }

    /**
     * Make the turtle with turtle id id be hidden from the screen
     * @param id turtle id of the turtle to be hidden
     */
    @Override
    public void hideTurtle(int id) {
        myStackedCanvasPane.hideTurtle(id);
    }

    /**
     * Make the turtle with turtle id id face the direction specified by degrees
     * @param id turtle id of the turtle whose orientation is to be set
     * @param degrees orientation that the turtle should have
     */
    @Override
    public void setOrientation(int id, double degrees) {
        myStackedCanvasPane.setOrientation(id, degrees);
    }

    /**
     * Sets the turtle to turn by degrees degrees
     * @param id id of the turtle to change
     * @param degrees degrees to turn by
     */
    @Override
    public void setTowards(int id, double degrees) {
        myStackedCanvasPane.setTowards(id, degrees);
    }

    /**
     * Change the location of the turtle with id id to position x, y on the canvas
     * @param id turtle id of the turtle to be moved
     * @param x resulting x position of the turtle
     * @param y resulting y position of the turtle
     */
    @Override
    public void setLocation(int id, double x, double y) {
        myStackedCanvasPane.setLocation(id, x, y);
    }

    /**
     * Tells turtle id to go home
     * @param id id of turtle to go home
     */
    @Override
    public void goHome(int id) {
        myStackedCanvasPane.goHome(id);
    }

    /**
     * Reset the canvas to white, erase all lines that are drawn, move all the turtles back to the center and have
     * them face due north
     */
    @Override
    public void clearScreen() {
        myStackedCanvasPane.clearScreen();
        myTabExplorer.clearCommandHistory();
    }

    /**
     * Change the background color of the canvas to be the color corresponding to index in the color palette
     * @param index index of the color in the color palette
     */
    @Override
    public void setBackgroundColor(int index) {
        myStackedCanvasPane.getBackgroundColorAccess().accept(myPaletteTabExplorer.getMyColorPalette().getContent(index).getFill());
    }

    /**
     * Change the pen color of the turtle with id to be the color corresponding to index in the color palette
     * @param id id of the turtle whose pen is to be changed
     * @param index index of the color in the color palette
     */
    @Override
    public void setPenColor(int id, int index) {
        myStackedCanvasPane.setPenColor(id, myPaletteTabExplorer.getMyColorPalette().getContent(index).getFill());
    }

    /**
     * Change the pen size of the turtle with id to be pixels in size
     * @param id id of the turtle whose pen is to be changed
     * @param pixels new width of the pen
     */
    @Override
    public void setPenSize(int id, double pixels) {
        myStackedCanvasPane.setPenSize(id, pixels);
    }

    /**
     * Change the icon of turtle with turtle id id to be the icon corresponding to index in the icon palette
     * @param id id of the turtle whose icon is to be changed
     * @param index index of the icon that the turtle is to be changed to in the icon palette
     */
    @Override
    public void setShape(int id, int index) {
        myStackedCanvasPane.setTurtleShape(id,
                myPaletteTabExplorer.getMyTurtlePalette().getContent(index).getClass().getName());
    }

    /**
     * Set the index index of the color palette to be the color with RBG value (r,b,g)
     * @param index index in the color palette to be set
     * @param r red content (0-255) of the color to add
     * @param b blue content (0-255) of the color to add
     * @param g green content (0-255) of the color to add
     */
    @Override
    public void setPalette(int index, int r, int b, int g) {
        myPaletteTabExplorer.getMyColorPalette().addPaletteElement(new PaletteElement<>(index,
                new Rectangle(PaletteTabExplorer.COLOR_PALETTE_WIDTH, PaletteTabExplorer.COLOR_PALETTE_HEIGHT,
                        Color.rgb(r, g, b))));
    }

}
