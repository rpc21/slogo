package GUI;

import javafx.scene.paint.Color;

import java.util.Vector;

public class Move {

    private Color penColor;
    private boolean penDown;
    private PenStyle pathStyle;
    private double penWidth;
    private double[] displacement;

    public Move(Color penColor, boolean penDown, PenStyle pathStyle, double penWidth, double[] displacement) {
        this.penColor = penColor;
        this.penDown = penDown;
        this.pathStyle = pathStyle;
        this.penWidth = penWidth;
        this.displacement = displacement;
    }

    public Color getPenColor() {
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

    public double[] getDisplacement() {
        return displacement;
    }
}
