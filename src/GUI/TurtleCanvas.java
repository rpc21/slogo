package GUI;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Control;
import javafx.scene.paint.Color;

import java.util.function.Consumer;

public class TurtleCanvas extends Canvas implements GUIComponent {

    public TurtleCanvas(double v1, double v2){
        super(v1, v2);
    }

    @Override
    public double minHeight(double width)
    {
        return 64;
    }

    @Override
    public double maxHeight(double width)
    {
        return 1000;
    }

    @Override
    public double prefHeight(double width)
    {
        return minHeight(width);
    }

    @Override
    public double minWidth(double height)
    {
        return 0;
    }

    @Override
    public double maxWidth(double height)
    {
        return 10000;
    }

    @Override
    public boolean isResizable()
    {
        return true;
    }

    @Override
    public void resize(double width, double height) {

    }

    public Consumer<Color> getBackgroundColorAccess(){
        Consumer<Color> changeBackgroundColor = (x) -> {
            this.getGraphicsContext2D().setFill(x);
            this.getGraphicsContext2D().rect(0, 0, this.getWidth(), this.getHeight());
            this.getGraphicsContext2D().fill();
        };
        return changeBackgroundColor;
    }

    public Consumer<Color> getPenColorAccess(){
        Consumer<Color> changePenColor = (x) -> {
            this.getGraphicsContext2D().setStroke(x);
        };
        return changePenColor;
    }
}
