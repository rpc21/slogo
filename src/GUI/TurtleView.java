package GUI;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class TurtleView extends DisplayView {

    public static final String TURTLE_IMAGE = "turtle1.jpg";
    public static final int TURTLE_HEIGHT = 25;
    public static final int TURTLE_WIDTH = 20;

    private Canvas myCanvas;
    private Pen myPen;
    private GraphicsContext myContext;
    private List<Move> myMoveHistory;

    public TurtleView(){
        super(new Image(TURTLE_IMAGE));
        setFitHeight(TURTLE_HEIGHT);
        setFitWidth(TURTLE_WIDTH);
        myPen = new Pen(true, Color.BLACK, PenStyle.DASHED, 2.0);
        myMoveHistory = new ArrayList<>();
    }

    public TurtleView(Canvas turtleCanvas) {
        this();
        myCanvas = turtleCanvas;
        myContext = myCanvas.getGraphicsContext2D();
    }

    public void makeMove(Move turtleMove){
        updatePen(turtleMove);
        drawPath(turtleMove);
    }

    private void updateTurtlePosition(Move turtleMove) {
        setTranslateX(getTranslateX() + turtleMove.getDisplacement()[0]);
        setTranslateY(getTranslateY() + turtleMove.getDisplacement()[1]);
    }

    private void drawPath(Move turtleMove) {
//        GraphicsContext context = myCanvas.getGraphicsContext2D();
        myContext.setLineWidth(myPen.getMyWidth());
        myContext.setStroke(myPen.getMyColor());
        myContext.beginPath();
        myContext.moveTo(this.getTranslateX() + myCanvas.getWidth()/2, this.getTranslateY() + myCanvas.getHeight()/2);
        updateTurtlePosition(turtleMove);
        myContext.lineTo(this.getTranslateX() + myCanvas.getWidth()/2, this.getTranslateY() + myCanvas.getHeight()/2);
        myContext.stroke();
        myContext.closePath();
    }

    public void drawPath(){
        for (Move move : myMoveHistory){
            makeMove(move);
        }
    }

    private void updatePen(Move turtleMove) {
        myPen.setDown(turtleMove.isPenDown());
        myPen.setMyColor(turtleMove.getPenColor());
        myPen.setMyStyle(turtleMove.getPathStyle());
        myPen.setMyWidth(turtleMove.getPenWidth());
    }

    public void addMove(Move turtleMove){
        myMoveHistory.add(turtleMove);
    }

    public void addAllMoves(List<Move> turtleMoves){
        myMoveHistory.addAll(turtleMoves);
    }

    public void clearMoves(){
        myMoveHistory.clear();
    }
}
