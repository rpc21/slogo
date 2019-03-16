package GUI.Pen;

import javafx.scene.paint.Paint;

/**
 * The Pen class is a container for all the information about a pen at a given point in time. Each DisplayView
 * contains a pen with properties that can be queried and updated.
 */
public class Pen {

    private boolean down;
    private Paint myColor;
    private PenStyle myStyle;
    private double myWidth;

    /**
     * Pen constructor
     * @param down boolean of if pen is down
     * @param myColor color of the pen
     * @param myStyle style of the pen
     * @param myWidth width of the pen
     */
    public Pen(boolean down, Paint myColor, PenStyle myStyle, double myWidth) {
        this.down = down;
        this.myColor = myColor;
        this.myStyle = myStyle;
        this.myWidth = myWidth;
    }

    /**
     * Pen constructor to make a copy of another pen
     * @param pen pen to be copied
     */
    public Pen(Pen pen){
        this.down = pen.down;
        this.myColor = pen.myColor;
        this.myStyle = pen.myStyle;
        this.myWidth = pen.myWidth;
    }

    /**
     * Getter for if pen is down
     * @return if pen is down
     */
    public boolean isDown() {
        return down;
    }

    /**
     * Setter for whether pen is down
     * @param down whether pen should be down
     */
    public void setDown(boolean down) {
        this.down = down;
    }

    /**
     * Getter for pen color
     * @return color of the pen
     */
    public Paint getMyColor() {
        return myColor;
    }

    /**
     * Setter for pen color
     * @param myColor color to be set
     */
    public void setMyColor(Paint myColor) {
        this.myColor = myColor;
    }

    /**
     * Getter for pen style
     * @return the pen style
     */
    public PenStyle getMyStyle() {
        return myStyle;
    }

    /**
     * Getter for pen width
     * @return pen width
     */
    public double getMyWidth() {
        return myWidth;
    }

    /**
     * Setter for pen width
     * @param myWidth width to be set to
     */
    public void setMyWidth(double myWidth) {
        this.myWidth = myWidth;
    }
}
