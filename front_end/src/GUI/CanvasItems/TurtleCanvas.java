package GUI.CanvasItems;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.function.Consumer;

public class TurtleCanvas extends Canvas {

    public TurtleCanvas(double v1, double v2){
        super(v1, v2);
    }

//    public void setPreferredSize(double v1, double v2){
//        minWidth(v1);
//        minHeight(v2);
//    }

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

    public Consumer<Paint> getBackgroundColorAccess(){
        return x -> setColor(x);
    }

//    public Consumer<Paint> getPenColorAccess(){
//        return x -> this.getGraphicsContext2D().setStroke(x);
//    }

    public void setColor(Paint color){
        this.getGraphicsContext2D().setFill(color);
        this.getGraphicsContext2D().rect(0, 0, this.getWidth(), this.getHeight());
        this.getGraphicsContext2D().fill();
    }

    public void clearCanvas(){
        getGraphicsContext2D().clearRect(0, 0, getWidth(), getHeight());
    }
}
