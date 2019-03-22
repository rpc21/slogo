package GUI.Turtle;

import GUI.Commands.CommandExecutable;
import GUI.Commands.Language;
import GUI.Commands.LanguageChangeable;
import GUI.Commands.Move;
import GUI.Pen.Pen;
import GUI.Pen.PenStyle;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.*;
import java.util.function.Consumer;

/**
 * Abstract Super Class for front-end turtles.  The turtles are able to move around the canvas, draw on the canvas,
 * and have a pen that they use for drawing.
 * Authors: Ryan Culhane, Louis Jensen
 */
public abstract class DisplayView extends ImageView implements CommandExecutable, LanguageChangeable {

    private static final String TURTLE_IMAGE = "file:/resources_images/turtle1.jpg";
    private static final int IMAGE_HEIGHT = 25;
    private static final int IMAGE_WIDTH = 20;
    private Canvas myCanvas;
    private Pen myPen;
    private GraphicsContext myContext;
    private List<Move> myMoveHistory;
    private Consumer<String> myCommandAccess;
    private Consumer<DisplayView> myTabAccess;
    private DisplayViewContextMenu myDisplayViewContextMenu;
    private Language myLanguage;
    private int myTurtleId;
    private Set<Integer> myListOfActiveTurtles;
    private boolean isActive;

    /**
     * Default DisplayView constructor
     */
    public DisplayView(){
        this(new Image(TURTLE_IMAGE));
    }

    /**
     * DisplayView constructor
     * @param image image for the DisplayView
     */
    public DisplayView(Image image){
        super(image);
        setFitHeight(IMAGE_HEIGHT);
        setFitWidth(IMAGE_WIDTH);
        myPen = new Pen(true, Color.BLACK, PenStyle.DASHED, 2.0);
        myMoveHistory = new ArrayList<>();
        isActive = true;
        this.managedProperty().bind(this.visibleProperty());
        setRotate(0);
    }

    /**
     * DisplayView constructor
     * @param canvas drawing canvas
     */
    public DisplayView(Canvas canvas){
        this();
        myCanvas = canvas;
        myContext = myCanvas.getGraphicsContext2D();
    }

    /**
     * DisplayView constructor
     * @param displayView displayView to take image from
     * @param canvas drawing canvas
     */
    public DisplayView(DisplayView displayView, Canvas canvas){
        this(displayView.getImage(), canvas);
    }

    /**
     * DisplayView constructor
     * @param image image to be displayed
     * @param canvas drawing canvas
     */
    public DisplayView(Image image, Canvas canvas){
        this(image);
        myCanvas = canvas;
        myContext = myCanvas.getGraphicsContext2D();
    }

    /**
     * DisplayView constructor
     * @param displayView DisplayView to be copied
     */
    public DisplayView(DisplayView displayView){
        this(displayView, displayView.getImage());
    }

    /**
     * DisplayView constructor
     * @param displayView DisplayView to be copied
     * @param image image to be shown for new display view
     */
    public DisplayView(DisplayView displayView, Image image){
        this(image, displayView.myCanvas);
        copyMoveHistoryAndPen(displayView);
        copyPositionAndOrientation(displayView);
        copyAccess(displayView);
        myTurtleId = displayView.myTurtleId;
    }

    private void copyAccess(DisplayView displayView) {
        giveTabAccess(displayView.myTabAccess);
        giveAbilityToRunCommands(displayView.myCommandAccess);
    }

    private void copyPositionAndOrientation(DisplayView displayView) {
        setTranslateX(displayView.getTranslateX());
        setTranslateY(displayView.getTranslateY());
        setRotate(displayView.getRotate());
    }

    private void copyMoveHistoryAndPen(DisplayView displayView) {
        this.myMoveHistory = displayView.myMoveHistory;
        this.myPen = displayView.myPen;
    }

    private void addMove(Move turtleMove){
        myMoveHistory.add(turtleMove);
    }

    private void updatePosition(Move move) {
        setTranslateX(move.getDisplacement().getX());
        setTranslateY(move.getDisplacement().getY());
    }

    /**
     * Draw a move on the drawing canvas based on pen properties of the move
     * @param move move to be drawn
     */
    public void drawPath(Move move) {
        myContext.setLineWidth(move.getPenWidth());
        myContext.setStroke(move.getPenColor());
        myContext.beginPath();
        myContext.moveTo(this.getTranslateX() + myCanvas.getWidth()/2, this.getTranslateY() + myCanvas.getHeight()/2);
        updatePosition(move);
        myContext.lineTo(this.getTranslateX() + myCanvas.getWidth()/2, this.getTranslateY() + myCanvas.getHeight()/2);
        if (myPen.isDown()) {
            myContext.stroke();
        }
        myContext.closePath();
    }

    /**
     * Getter for the pen
     * @return myPen
     */
    public Pen getMyPen() {
        return myPen;
    }

    /**
     * Moves the turtle by x in the x direction and y in the y direction
     * @param deltaX delta x
     * @param deltaY delta y
     */
    public void turtleMove(double deltaX, double deltaY) {
        moveTo(new Point2D(getTranslateX() + deltaX, getTranslateY() + deltaY));
    }

    /**
     * Moves the turtle to the new location
     * @param newLocation next location for the turtle
     */
    private void moveTo(Point2D newLocation){
        Move move = new Move(new Pen(getMyPen()), newLocation);
        addMove(move);
        drawPath(move);
        updateTab();
    }

    /**
     * Sets rotation of turtle to degrees
     * @param degrees new rotation of the turtle image
     */
    public void towards(double degrees) {
        setRotate(degrees);
    }

    /**
     * Moves turtle to location x,y
     * @param x new x position
     * @param y new y position
     */
    public void setLocation(double x, double y) {
        moveTo(new Point2D(x, y));
    }

    /**
     * Moves turtle to 0, 0
     */
    public void goHome() {
        moveTo(new Point2D(0,0));
    }

    /**
     * Turns the turtle by degrees number of degrees
     * @param degrees number of degrees to turn the turtle by
     */
    public void turn(double degrees) {
        setRotate(getRotate() + degrees);
        updateTab();
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
        if (myLanguage == null){
            myLanguage = Language.ENGLISH;
        }
        myDisplayViewContextMenu = new DisplayViewContextMenu(myLanguage, commandAccess);
        setOnContextMenuRequested(e -> myDisplayViewContextMenu.show(this, e.getSceneX(), e.getSceneY()));
    }

    /**
     * Gives DisplayView ability to update the tab that contains information about the display view
     * @param tabAccess tab access
     */
    public void giveTabAccess(Consumer<DisplayView> tabAccess){
        myTabAccess = tabAccess;
        setOnMouseClicked(e -> {
            updateTab();
            if (e.getButton().equals(MouseButton.PRIMARY)){
                doCorrectTell();
                System.out.println(myTurtleId + " this is my turtle ID of click");
            }
        });
    }

    private void doCorrectTell(){
        Integer IDtoTell = myTurtleId + 1;
        runCommand("tell [ " + IDtoTell.toString()+ " ]");
    }

    private void makeBig(){
        this.setFitWidth(IMAGE_WIDTH*1.8);
        this.setFitHeight(IMAGE_HEIGHT*1.8);
    }

    private void makeSmall(){
        this.setFitWidth(IMAGE_WIDTH);
        this.setFitHeight(IMAGE_HEIGHT);
    }

    /**
     * Make turtle active
     */
    public void setActive(){
        isActive = true;
    }

    /**
     * Make turtle inactive
     */
    public void setInActive(){
        isActive = false;
    }

    /**
     * @return if turtle is active
     */
    public boolean isItActive(){
        return isActive;
    }

    /**
     * Update turtle size based on activity status
     */
    public void showSize(){
        if (isActive){
            makeBig();
        } else {
            makeSmall();
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
     * @param language new language that the program is being changed to
     */
    @Override
    public void setLanguage(Language language){
        myLanguage = language;
        if (myDisplayViewContextMenu != null) {
            myDisplayViewContextMenu.setLanguage(language);
        }
    }

    /**
     * Sets the turtle's id
     * @param id new id
     */
    public void setTurtleId(int id){
        myTurtleId = id;
    }

    /**
     * Getter for turtle id
     * @return myTurtleId
     */
    public int getTurtleId(){
        return myTurtleId;
    }

    /**
     * Updates the tab of DisplayView information
     */
    private void updateTab(){
        myTabAccess.accept(this);
    }

    /**
     * Change pen color for turtle
     * @param color new color
     */
    public void setPenColor(Paint color){
        myPen.setMyColor(color);
        updateTab();
    }

    /**
     * Change pen width for this turtle's pen
     * @param pixels new pen width
     */
    public void setPenWidth(double pixels){
        myPen.setMyWidth(pixels);
        updateTab();
    }

    /**
     * Change whether pen is up or down
     * @param isDown whether pen should be down
     */
    public void setPenDown(boolean isDown){
        myPen.setDown(isDown);
        updateTab();
    }
}
