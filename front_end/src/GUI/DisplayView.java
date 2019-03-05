package GUI;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class DisplayView extends ImageView implements CommandExecutable, LanguageChangeable{

    public static final String BASIC_TURTLE_NAME = "Basic Turtle Image";
    public static final String ADVANCED_TURTLE_NAME = "Advanced Turtle Image";
    private List<String> possibleImages = List.of(BASIC_TURTLE_NAME, ADVANCED_TURTLE_NAME);

    public static final String TURTLE_IMAGE = "file:/resources_images/turtle1.jpg";
    public static final int IMAGE_HEIGHT = 25;
    public static final int IMAGE_WIDTH = 20;

    private Canvas myCanvas;
    private Pen myPen;
    private GraphicsContext myContext;
    protected List<Move> myMoveHistory;
    private Consumer<String> myCommandAccess;
    private Consumer<DisplayView> myTabAccess;
    private DisplayViewContextMenu myDisplayViewContextMenu;
    private Language myLanguage;
    private int myTurtleId;
//    private int myIndex;

    public DisplayView(){
        this(new Image(TURTLE_IMAGE));
    }

    public DisplayView(Image image){
        super(image);
        setFitHeight(IMAGE_HEIGHT);
        setFitWidth(IMAGE_WIDTH);
//        myIndex = 0;
        myPen = new Pen(true, Color.BLACK, PenStyle.DASHED, 2.0);
        myMoveHistory = new ArrayList<>();
        this.managedProperty().bind(this.visibleProperty());
        setRotate(0);
    }

    public DisplayView(Canvas canvas){
        this();
        myCanvas = canvas;
        myContext = myCanvas.getGraphicsContext2D();
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
//        myIndex = displayView.myIndex;
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

    public void addMove(Move turtleMove){
        myMoveHistory.add(turtleMove);
    }
//
//    public void addAllMoves(List<Move> turtleMoves){
//        myMoveHistory.addAll(turtleMoves);
//    }
//
//    public void clearMoves(){
//        myMoveHistory.clear();
//    }
//
//    public void makeMove(Move move){
//        updatePen(move);
//        drawPath(move);
//    }

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
//        updateTab();
    }

//    public void drawPath(){
//        for (Move move : myMoveHistory){
//            makeMove(move);
//        }
//    }

//    private void updatePen(Move move) {
//        myPen.setDown(move.isPenDown());
//        myPen.setMyColor(move.getPenColor());
//        myPen.setMyStyle(move.getPathStyle());
//        myPen.setMyWidth(move.getPenWidth());
//    }

    public List<String> getPossibleImages() {
        return possibleImages;
    }

    public Pen getMyPen() {
        return myPen;
    }

    public void turtleMove(double deltaX, double deltaY) {
        System.out.println(getTranslateX() + " " + deltaX + "");
        moveTo(new Point2D(getTranslateX() + deltaX, getTranslateY() + deltaY));
        System.out.println(getTranslateY());
        updateTab();
    }

    private void moveTo(Point2D newLocation){
        System.out.println("####" +  newLocation);
        Move move = new Move(new Pen(getMyPen()), newLocation);
        addMove(move);
        drawPath(move);
    }

    public void towards(double degrees) {
        setRotate(degrees);
        System.out.println("VISUALIZATION :" + getRotate());
    }

    public void setLocation(double x, double y) {
        moveTo(new Point2D(x, y));
    }

    public void goHome() {
        moveTo(new Point2D(0,0));
        setRotate(0);
    }

    public void turn(double degrees) {
        setRotate(getRotate() + degrees);
        updateTab();
    }

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
        setOnMouseClicked(e -> updateTab());
    }

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

    public void setPenColor(Color color){
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
