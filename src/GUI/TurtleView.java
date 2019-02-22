package GUI;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class TurtleView extends DisplayView {

    public static final String TURTLE_IMAGE = "turtle1.jpg";
    public static final int TURTLE_HEIGHT = 25;
    public static final int TURTLE_WIDTH = 20;

    private Canvas myCanvas;
    private Pen myPen;

    public TurtleView(){
        super(new Image(TURTLE_IMAGE));
        setFitHeight(TURTLE_HEIGHT);
        setFitWidth(TURTLE_WIDTH);
        myPen = new Pen(true, Color.BLACK, PenStyle.DASHED, 2.0);
    }

    public TurtleView(Canvas turtleCanvas) {
        this();
        myCanvas = turtleCanvas;
    }

    public void makeMove(Move turtleMove){
        updatePen(turtleMove);
        GraphicsContext context = myCanvas.getGraphicsContext2D();
        context.setLineWidth(myPen.getMyWidth());
        context.setStroke(myPen.getMyColor());
        context.beginPath();
        context.moveTo(turtleMove.getDisplacement()[0], turtleMove.getDisplacement()[1]);
        context.closePath();
        context.stroke();
    }

    private void updatePen(Move turtleMove) {
        myPen.setDown(turtleMove.isPenDown());
        myPen.setMyColor(turtleMove.getPenColor());
        myPen.setMyStyle(turtleMove.getPathStyle());
        myPen.setMyWidth(turtleMove.getPenWidth());
    }

}
