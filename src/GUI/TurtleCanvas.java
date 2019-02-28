package GUI;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.function.Consumer;

public class TurtleCanvas extends Canvas implements GUIComponent {

    public TurtleCanvas(double v1, double v2){
        super(v1, v2);
    }

    public void setPreferredSize(double v1, double v2){
        minWidth(v1);
        minHeight(v2);
    }

    @Override
    public boolean isResizable()
    {
        return true;
    }

    @Override
    public void resize(double width, double height) {
        super.setWidth(width);
        super.setHeight(height);
//        setPreferredSize(width, height);
    }

    public Consumer<Color> getBackgroundColorAccess(){
        Consumer<Color> changeBackgroundColor = (x) -> {
            setColor(x);
        };
        return changeBackgroundColor;
    }

    public Consumer<Color> getPenColorAccess(){
        Consumer<Color> changePenColor = (x) -> {
            this.getGraphicsContext2D().setStroke(x);
        };
        return changePenColor;
    }

    public void setColor(Paint color){
        this.getGraphicsContext2D().setFill(color);
        this.getGraphicsContext2D().rect(0, 0, this.getWidth(), this.getHeight());
        this.getGraphicsContext2D().fill();
    }

    public void clearCanvas(){
        setColor(Color.TRANSPARENT);
    }
}
