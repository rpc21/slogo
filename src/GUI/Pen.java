package GUI;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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

    public void setMyStyle(PenStyle myStyle) {
        this.myStyle = myStyle;
    }

    public double getMyWidth() {
        return myWidth;
    }

    public void setMyWidth(double myWidth) {
        this.myWidth = myWidth;
    }
}
