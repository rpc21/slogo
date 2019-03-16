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

    public Pen(boolean down, Paint myColor, PenStyle myStyle, double myWidth) {
        this.down = down;
        this.myColor = myColor;
        this.myStyle = myStyle;
        this.myWidth = myWidth;
    }

    public Pen(Pen pen){
        this.down = pen.down;
        this.myColor = pen.myColor;
        this.myStyle = pen.myStyle;
        this.myWidth = pen.myWidth;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public Paint getMyColor() {
        return myColor;
    }

    public void setMyColor(Paint myColor) {
        this.myColor = myColor;
    }

    public PenStyle getMyStyle() {
        return myStyle;
    }

    public double getMyWidth() {
        return myWidth;
    }

    public void setMyWidth(double myWidth) {
        this.myWidth = myWidth;
    }
}
