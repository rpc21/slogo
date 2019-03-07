package GUI.CanvasItems;

import GUI.GUI.GUIComponent;
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

    public Consumer<Paint> getBackgroundColorAccess(){
        Consumer<Paint> changeBackgroundColor = (x) -> {
            setColor(x);
        };
        return changeBackgroundColor;
    }

    public Consumer<Paint> getPenColorAccess(){
        Consumer<Paint> changePenColor = (x) -> {
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
        getGraphicsContext2D().clearRect(0, 0, getWidth(), getHeight());
    }
}
