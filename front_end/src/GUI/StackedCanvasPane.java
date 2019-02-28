package GUI;

import apis.CanvasAPI;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.List;
import java.util.function.Consumer;

public class StackedCanvasPane extends StackPane implements CanvasAPI {

    public static final double DEFAULT_CANVAS_WIDTH = 800;
    public static final double DEFAULT_CANVAS_HEIGHT = 400;

    private TurtleCanvas myBackgroundCanvas;
    private TurtleCanvas myDrawingCanvas;
    private DisplayView myCurrentDisplayView;
    private boolean penDown;

    public StackedCanvasPane(){
        super();
        myBackgroundCanvas = createBackgroundCanvas(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT);
        myDrawingCanvas = new TurtleCanvas(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT);
        myCurrentDisplayView = new BasicTurtleView(myDrawingCanvas);
        penDown = true;
        getChildren().addAll(myBackgroundCanvas, myDrawingCanvas, myCurrentDisplayView);
        this.setLayoutX(DEFAULT_CANVAS_WIDTH);
        this.setLayoutY(DEFAULT_CANVAS_HEIGHT);
    }

    private TurtleCanvas createBackgroundCanvas(double width, double height) {
        TurtleCanvas canvas = new TurtleCanvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.rect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.fill();
        return canvas;
    }

    public Consumer<Color> getBackgroundColorAccess(){
        return myBackgroundCanvas.getBackgroundColorAccess();
    }

    public Consumer<Paint> getPenPropertiesAccess(){
        return (x) -> myCurrentDisplayView.getMyPen().setMyColor(x);
    }

    public Consumer<String> getIconAccess(){
        Consumer<String> changeIcon = (x) -> {
            this.getChildren().remove(myCurrentDisplayView);
            myCurrentDisplayView = new DisplayViewFactory().generateDislplayView(x, myCurrentDisplayView);
            this.getChildren().add(myCurrentDisplayView);
        };
        return changeIcon;
    }

    public void addMove(Move move){
        myCurrentDisplayView.addMove(move);
        myCurrentDisplayView.drawPath();
    }

    public void addAllMoves(List<Move> moves){
        myCurrentDisplayView.addAllMoves(moves);
    }

    public void batchUpdateCanvas(){
        myCurrentDisplayView.drawPath();
    }

    public void resizeCanvases(double v, double v1) {
        myBackgroundCanvas.resize(v, v1);
        myDrawingCanvas.resize(v, v1);
    }

    @Override
    public void turtleMove(double x, double y){
        myCurrentDisplayView.turtleMove(x,y);
    }

    @Override
    public void turtleTurn(double degrees){
        myCurrentDisplayView.turn(degrees);
    }

//    @Override
//    public void turnRight(double degrees) {
//        myCurrentDisplayView.turn(degrees);
//    }
//
//    @Override
//    public void turnLeft(double degrees) {
//        myCurrentDisplayView.turn(-degrees);
//    }

    @Override
    public void setPenUp(){
        myCurrentDisplayView.getMyPen().setDown(false);
    }

    @Override
    public void setPenDown(){
        myCurrentDisplayView.getMyPen().setDown(true);
    }

    @Override
    public void showTurtle() {
        myCurrentDisplayView.setVisible(true);
    }

    @Override
    public void hideTurtle() {
        myCurrentDisplayView.setVisible(false);
    }

    @Override
    public void setOrientation(double degrees) {
        myCurrentDisplayView.setRotate(degrees);
    }

    public void setTowards(double x, double y) {
        myCurrentDisplayView.towards(x, y);
    }

    @Override
    public void setLocation(double x, double y) {
        myCurrentDisplayView.setLocation(x, y);
    }

    @Override
    public void goHome() {
        myCurrentDisplayView.goHome();
    }

    @Override
    public void clearScreen() {
        goHome();
        myBackgroundCanvas.setColor(Color.WHITE);
        myDrawingCanvas.clearCanvas();
    }

}
