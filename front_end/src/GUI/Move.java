package GUI;

import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;

public class Move {

    private Paint penColor;
    private boolean penDown;
    private PenStyle pathStyle;
    private double penWidth;
    private Point2D displacement;

    public Move(Paint penColor, boolean penDown, PenStyle pathStyle, double penWidth, Point2D displacement) {
        this.penColor = penColor;
        this.penDown = penDown;
        this.pathStyle = pathStyle;
        this.penWidth = penWidth;
        this.displacement = displacement;
    }

    public Move(Pen pen, Point2D displacement){
        this(pen.getMyColor(), pen.isDown(), pen.getMyStyle(), pen.getMyWidth(), displacement);
    }

    public Move(Pen pen, double[] displacement){
        this(pen, new Point2D(displacement[0], displacement[1]));
    }

    public Paint getPenColor() {
        return penColor;
    }

    public boolean isPenDown() {
        return penDown;
    }

    public PenStyle getPathStyle() {
        return pathStyle;
    }

    public double getPenWidth() {
        return penWidth;
    }

    public Point2D getDisplacement() {
        return displacement;
    }
}
