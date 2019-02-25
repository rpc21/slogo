

import javafx.scene.paint.Color;

import java.util.Vector;

public class TurtleMovement {

    private Color penColor;
    private boolean penDown;
    private double penWidth;
    private double[] displacement;

    public TurtleMovement(Color penColor, boolean penDown, double penWidth, double[] displacement) {
        this.penColor = penColor;
        this.penDown = penDown;
        this.penWidth = penWidth;
        this.displacement = displacement;
    }

    public Color getPenColor() {
        return penColor;
    }

    public boolean isPenDown() {
        return penDown;
    }

    public double getPenWidth() {
        return penWidth;
    }

    public double[] getDisplacement() {
        return displacement;
    }
}
