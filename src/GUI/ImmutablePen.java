package GUI;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ImmutablePen {

    private boolean down;
    private Paint myColor;
    private PenStyle myStyle;
    private double myWidth;

    public ImmutablePen(Pen pen){
        down = pen.isDown();
        myColor = pen.getMyColor();
        myStyle = pen.getMyStyle();
        myWidth = pen.getMyWidth();
    }

    public boolean isDown() {
        return down;
    }

    public Paint getMyColor() {
        return myColor;
    }

    public PenStyle getMyStyle() {
        return myStyle;
    }

    public double getMyWidth() {
        return myWidth;
    }
}
