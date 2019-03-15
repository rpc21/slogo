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

public abstract class DisplayView extends ImageView implements CommandExecutable, LanguageChangeable {

//    private static final String BASIC_TURTLE_NAME = "Basic Turtle View";
//    private static final String ADVANCED_TURTLE_NAME = "Advanced Turtle View";
//    private static final String CUTE_TURTLE_NAME = "Cute Turtle View";
//    private static final String GITLAB_TURTLE_VIEW = "GitLab View";
//    public static final List<String> POSSIBLE_IMAGES = List.of(BASIC_TURTLE_NAME, ADVANCED_TURTLE_NAME,
//            CUTE_TURTLE_NAME,
//            GITLAB_TURTLE_VIEW);

    private static final String TURTLE_IMAGE = "file:/resources_images/turtle1.jpg";
    private static final int IMAGE_HEIGHT = 25;
    private static final int IMAGE_WIDTH = 20;

    private Canvas myCanvas;
    private Pen myPen;
    private GraphicsContext myContext;
    protected List<Move> myMoveHistory;
    private Consumer<String> myCommandAccess;
    private Consumer<DisplayView> myTabAccess;
    private DisplayViewContextMenu myDisplayViewContextMenu;
    private Language myLanguage;
    private int myTurtleId;
    private Set<Integer> myListOfActiveTurtles;
//    private int myIndex;

    private boolean isActive;
    public DisplayView(){
        this(new Image(TURTLE_IMAGE));
    }

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

    public DisplayView(Canvas canvas){
        this();
        myCanvas = canvas;
        myContext = myCanvas.getGraphicsContext2D();
    }

    public void passListOfTurtles(List<Integer> listOfTurtles){
       // myListOfTurtles = listOfTurtles;
        myListOfActiveTurtles = new TreeSet<Integer>();
        myListOfActiveTurtles.clear();
        myListOfActiveTurtles.addAll(listOfTurtles);
    }

    public DisplayView(DisplayView displayView, Canvas canvas){
        this(displayView.getImage(), canvas);
    }

    public DisplayView(Image image, Canvas canvas){
        this(image);
        myCanvas = canvas;
        myContext = myCanvas.getGraphicsContext2D();
    }

    public DisplayView(DisplayView displayView){
        this(displayView, displayView.getImage());
    }

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

//    public List<String> getPossibleImages() {
//        return Collections.unmodifiableList(POSSIBLE_IMAGES);
//    }

    public Pen getMyPen() {
        return myPen;
    }

    public void turtleMove(double deltaX, double deltaY) {
        System.out.println(getTranslateX() + " " + deltaX + "");
        moveTo(new Point2D(getTranslateX() + deltaX, getTranslateY() + deltaY));
        System.out.println(getTranslateY());
    }

    private void moveTo(Point2D newLocation){
        System.out.println("####" +  newLocation);
        Move move = new Move(new Pen(getMyPen()), newLocation);
        addMove(move);
        drawPath(move);
        updateTab();
    }

    public void towards(double degrees) {
        setRotate(degrees);
        System.out.println("VISUALIZATION :" + getRotate());
    }

    public void setLocation(double x, double y) {
        System.out.println("**" + x + " " + y);
        moveTo(new Point2D(x, y));
    }

    public void goHome() {
        moveTo(new Point2D(0,0));
    }

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

    public void makeBig(){
        this.setFitWidth(IMAGE_WIDTH*1.8);
        this.setFitHeight(IMAGE_HEIGHT*1.8);
    }

    public void makeSmall(){
        this.setFitWidth(IMAGE_WIDTH);
        this.setFitHeight(IMAGE_HEIGHT);
    }

    public void setActive(){
        isActive = true;
    }

    public void setInActive(){
        isActive = false;
    }

    public boolean isItActive(){
        return isActive;
    }

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

    @Override
    public void setLanguage(Language language){
        myLanguage = language;
        if (myDisplayViewContextMenu != null) {
            myDisplayViewContextMenu.setLanguage(language);
        }
    }

    public void setTurtleId(int id){
        myTurtleId = id;
    }

    public int getTurtleId(){
        return myTurtleId;
    }

    private void updateTab(){
        myTabAccess.accept(this);
    }

    public void setPenColor(Paint color){
        myPen.setMyColor(color);
        updateTab();
    };

    public void setPenWidth(double pixels){
        myPen.setMyWidth(pixels);
        updateTab();
    };

    public void setPenDown(boolean isDown){
        myPen.setDown(isDown);
        updateTab();
    };
}
