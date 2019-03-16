package GUI.CanvasItems;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Paint;

import java.util.function.Consumer;

/**
 * Canvases to be used in SLogo.  These canvases are resizable and their background color can be accessed and the
 * canvases can be cleared.  Isolates important functionality of the canvases into a wrapper class.
 */
public class TurtleCanvas extends Canvas {

    /**
     * Constructor for TurtleCanvas
     *
     * @param v1 width of Canvas
     * @param v2 height of Canvas
     */
    TurtleCanvas(double v1, double v2) {
        super(v1, v2);
    }

    /**
     * Makes the canvas resizable
     * @return true always so it is resizable
     */
    @Override
    public boolean isResizable() {
        return true;
    }

    /**
     * Overrides the resize method of Canvas to make canvas resizable
     * @param width width to be resized to
     * @param height height to be resized to
     */
    @Override
    public void resize(double width, double height) {
        super.setWidth(width);
        super.setHeight(height);
    }

    /**
     * Returns consumer that accepts a new Paint for the background color of the canvas
     * @return consumer that accepts a new Paint for the background color of the canvas
     */
    Consumer<Paint> getBackgroundColorAccess() {
        return this::setColor;
    }

    /**
     * Sets the color of the canvas to color
     * @param color color to which you want to set the color of the canvas
     */
    public void setColor(Paint color) {
        this.getGraphicsContext2D().setFill(color);
        this.getGraphicsContext2D().rect(0, 0, this.getWidth(), this.getHeight());
        this.getGraphicsContext2D().fill();
    }

    void clearCanvas() {
        getGraphicsContext2D().clearRect(0, 0, getWidth(), getHeight());
    }
}
