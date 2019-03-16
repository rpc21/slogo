package GUI.Commands;

import GUI.Pen.Pen;
import GUI.Pen.PenStyle;
import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;

/**
 * The Move class bundles all the information associated with a turtle move such as the pen properties associated
 * with that move and the displacement vector of the turtle's move.  This class was designed to be flexible to
 * adapt to extensions involving the pen style as well as well as potentially undo-ing moves.  This functionality
 * went un-used in the extensions.
 */
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

    public Paint getPenColor() {
        return penColor;
    }

    public double getPenWidth() {
        return penWidth;
    }

    public Point2D getDisplacement() {
        return displacement;
    }
}
