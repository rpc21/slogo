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

    /**
     * Private constructor for the Move class
     */
    private Move(Paint penColor, boolean penDown, PenStyle pathStyle, double penWidth, Point2D displacement) {
        this.penColor = penColor;
        this.penDown = penDown;
        this.pathStyle = pathStyle;
        this.penWidth = penWidth;
        this.displacement = displacement;
    }

    /**
     * Constructor for the Move class
     * @param pen snapshot of the pen when this move is made
     * @param displacement displacement vector for the move that is being made
     */
    public Move(Pen pen, Point2D displacement){
        this(pen.getMyColor(), pen.isDown(), pen.getMyStyle(), pen.getMyWidth(), displacement);
    }

    /**
     * Getter for pen color
     * @return penColor of the move
     */
    public Paint getPenColor() {
        return penColor;
    }

    /**
     * Getter for pen width
     * @return pen width of the move
     */
    public double getPenWidth() {
        return penWidth;
    }

    /**
     * Getter for displacement of the move
     * @return displacement vector of the move
     */
    public Point2D getDisplacement() {
        return displacement;
    }
}
