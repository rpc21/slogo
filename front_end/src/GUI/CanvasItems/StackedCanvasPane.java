package GUI.CanvasItems;

import GUI.Commands.CommandExecutable;
import GUI.Commands.Language;
import GUI.Commands.LanguageChangeable;
import GUI.Turtle.BasicTurtleView;
import GUI.Turtle.DisplayView;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Organizational class that contains all elements related to the canvas.  There is a background canvas that can have
 * its color changed when the user wants to change the color of the background of the canvas.  Stacked on top of the
 * background canvas is a blank canvas that the turtles actually draw on.  Stacked on top of the canvases is a list
 * of DisplayViews (turtles) that can be moved around the screen drawing lines and can be hidden or shown.  The
 * StackedCanvasPane handles making the turtles and changing the background color and changing the screen and will
 * delegate turtle moves to the specific DisplayViews in its list of DisplayViews.
 */
public class StackedCanvasPane extends StackPane implements CommandExecutable, LanguageChangeable {

    private static final double DEFAULT_CANVAS_WIDTH = 800;
    private static final double DEFAULT_CANVAS_HEIGHT = 450;

    private TurtleCanvas myBackgroundCanvas;
    private TurtleCanvas myDrawingCanvas;
    private List<DisplayView> myTurtles;
    private Consumer<String> myCommandAccess;
    private Language myLanguage;
    private Consumer<DisplayView> myTabAccess;
    private Map<Integer, DisplayView> myListOfTurtles;
    private List<Integer> myActiveTurtles;
    private int IDcounter;

    /**
     * Constructor for StackedCanvasPane creates the background canvas, the drawing canvas, initializes the list of
     * turtles and list of active turtles and makes one turtle centered in the middle of the canvas
     */
    public StackedCanvasPane(){
        super();
        myTurtles = new ArrayList<>();
        myBackgroundCanvas = createBackgroundCanvas();
        myDrawingCanvas = new TurtleCanvas(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT);
        myListOfTurtles = new HashMap<>();
        myActiveTurtles = new ArrayList<>();
        IDcounter = 0;
        getChildren().addAll(myBackgroundCanvas, myDrawingCanvas);
        makeTurtle();
        this.setLayoutX(DEFAULT_CANVAS_WIDTH);
        this.setLayoutY(DEFAULT_CANVAS_HEIGHT);
    }

    private TurtleCanvas createBackgroundCanvas() {
        TurtleCanvas canvas = new TurtleCanvas(StackedCanvasPane.DEFAULT_CANVAS_WIDTH, StackedCanvasPane.DEFAULT_CANVAS_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.rect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.fill();
        return canvas;
    }

    private void makeTurtle(){
        IDcounter++;
        DisplayView newTurtle = new BasicTurtleView(myDrawingCanvas);
        myListOfTurtles.put(IDcounter, newTurtle);
        configureNewTurtle(newTurtle);
        getChildren().add(newTurtle);
        for (int key : myListOfTurtles.keySet()){
            myListOfTurtles.get(key).setInActive();
        }
        newTurtle.setActive();
        newTurtle.showSize();
        myTurtles.add(newTurtle);
    }

    private void configureNewTurtle(DisplayView newTurtle) {
        newTurtle.setLanguage(myLanguage);
        newTurtle.giveAbilityToRunCommands(myCommandAccess);
        newTurtle.setTurtleId(myTurtles.size());
        newTurtle.giveTabAccess(myTabAccess);
    }

    /**
     * Creates numTurtles new turtles and places them in the center of the canvas
     * @param numTurtles number of new turtles to create
     */
    public void addTurtles(int numTurtles) {
        for (int i = 0; i < numTurtles; i++){
            makeTurtle();
        }
    }

    public void setActiveTurtles(List<Integer> activeTurtleIDs) {
        myActiveTurtles = activeTurtleIDs;
        System.out.println(myActiveTurtles + " these are the active turtles");
        for (int key : myListOfTurtles.keySet()){
            myListOfTurtles.get(key).setInActive();
        }
        for (Integer turtleID : activeTurtleIDs){
            myListOfTurtles.get(turtleID+1).setActive();
        }
        for (int key : myListOfTurtles.keySet()){
            myListOfTurtles.get(key).showSize();
        }
    }

    /**
     * Returns a consumer that allows you to change color of background canvas
     * @return a consumer that takes in a Paint and changes the background canvas color to be that paint color
     */
    public Consumer<Paint> getBackgroundColorAccess(){
        return myBackgroundCanvas.getBackgroundColorAccess();
    }

    /**
     * Delegates to the display view with id id to move to position x, y
     * @param id turtle to be moved
     * @param x x move
     * @param y y move
     */
    public void turtleMove(int id, double x, double y){
        myTurtles.get(id).turtleMove(x,y);
    }

    /**
     * Delegate to the DisplayView with id id to turn by degrees degrees
     * @param id turtle to be moved
     * @param degrees number of degrees to turn
     */
    public void turtleTurn(int id, double degrees) {
        myTurtles.get(id).turn(degrees);
    }

    /**
     * Makes turtle with id raise its pen
     * @param id turtle to raise its pen
     */
    public void setPenUp(int id){
        myTurtles.get(id).setPenDown(false);
    }

    /**
     * Have the turtle with turtle id id set its pen down
     * @param id turtle id of the turtle to put its pen down
     */
    public void setPenDown(int id){
        myTurtles.get(id).setPenDown(true);
    }

    /**
     * Make the turtle with turtle id id appear on the screen
     * @param id turtle id of the turtle to be shown
     */
    public void showTurtle(int id) {
        myTurtles.get(id).setVisible(true);
    }

    /**
     * Make the turtle with turtle id id be hidden from the screen
     * @param id turtle id of the turtle to be hidden
     */
    public void hideTurtle(int id) {
        myTurtles.get(id).setVisible(false);
    }

    /**
     * Make the turtle with turtle id id face the direction specified by degrees
     * @param id turtle id of the turtle whose orientation is to be set
     * @param degrees orientation that the turtle should have
     */
    public void setOrientation(int id, double degrees) {
        myTurtles.get(id).setRotate(degrees);
    }

    /**
     *
     * @param id
     * @param degrees
     */
    public void setTowards(int id, double degrees) {
        myTurtles.get(id).towards(degrees);
    }

    /**
     * Change the location of the turtle with id id to position x, y on the canvas
     * @param id turtle id of the turtle to be moved
     * @param x resulting x position of the turtle
     * @param y resulting y position of the turtle
     */
    public void setLocation(int id, double x, double y) {
        myTurtles.get(id).setLocation(x, y);
    }

    /**
     *
     * @param id
     */
    public void goHome(int id) {
        myTurtles.get(id).goHome();
    }

    /**
     * Reset the canvas to white, erase all lines that are drawn, move all the turtles back to the center and have
     * them face due north
     */
    public void clearScreen() {
        myBackgroundCanvas.setColor(Color.WHITE);
        myDrawingCanvas.clearCanvas();
        for (DisplayView turtle: myTurtles){
            getChildren().remove(turtle);
        }
        myTurtles.clear();
        IDcounter = 0;
        makeTurtle();
    }

    /**
     * Set the pen color of turtle with id id to color color
     * @param id id of turtle to change pen color
     * @param color color to change pen to
     */
    public void setPenColor(int id, Paint color) {
        myTurtles.get(id).setPenColor(color);
    }

    /**
     * Change the pen size of the turtle with id to be pixels in size
     * @param id id of the turtle whose pen is to be changed
     * @param pixels new width of the pen
     */
    public void setPenSize(int id, double pixels) {
        myTurtles.get(id).setPenWidth(pixels);
    }

    /**
     * Change the icon of turtle with turtle id id to be the icon corresponding to index in the icon palette
     * @param id id of the turtle whose icon is to be changed
     * @param content class name of the DisplayView to change to
     */
    public void setTurtleShape(int id, String content) {
        DisplayView turtleToRemove = myTurtles.get(id);
        System.out.println(content);
        DisplayView turtle = getDisplayView(content, turtleToRemove);
        this.getChildren().remove(turtleToRemove);
        this.getChildren().add(turtle);
        myTurtles.remove(id);
        myTurtles.add(id, turtle);
    }

    private DisplayView getDisplayView(String content, DisplayView turtleToRemove) {
        try {
            var clazz = Class.forName(content);
            return (DisplayView) clazz.getDeclaredConstructor(DisplayView.class).newInstance(turtleToRemove);
        }
        catch (Exception e) {
            return new BasicTurtleView();
        }
    }

    /**
     * Gives the class the ability to run commands by passing a consumer that takes a String and passes
     * the command to the parser to be run through the backend and have its effects displayed on the front end as
     * well as stored in the backend
     * @param commandAccess a consumer that feeds the command to the parser to run the command through the backend.
     */
    @Override
    public void giveAbilityToRunCommands(Consumer<String> commandAccess) {
        myCommandAccess = commandAccess;
        for (DisplayView turtle: myTurtles){
            turtle.giveAbilityToRunCommands(commandAccess);
        }
    }

    /**
     * Method that calls the accept method on the consumer that was passed in the giveAbilityToRunCommands method
     * @param command the command to be run
     */
    @Override
    public void runCommand(String command) {
        myCommandAccess.accept(command);
    }

    /**
     * Change the language dependent features of the class to accommodate the new language
     * @param newLanguage new language that the program is being changed to
     */
    @Override
    public void setLanguage(Language newLanguage) {
        myLanguage = newLanguage;
        for (DisplayView turtle : myTurtles){
            turtle.setLanguage(newLanguage);
        }
    }

    /**
     * Give DisplayView ability to update the tab to view its current properties
     * @param tabAccess consumer that accepts a DisplayView and extracts and displays relevant information about the
     *                  DisplayView
     */
    public void grantTabAccess(Consumer<DisplayView> tabAccess){
        myTabAccess = tabAccess;
        for (DisplayView turtle : myTurtles){
            turtle.giveTabAccess(tabAccess);
        }
    }

}
