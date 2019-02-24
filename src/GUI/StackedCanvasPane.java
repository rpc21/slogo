package GUI;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.function.Consumer;

public class StackedCanvasPane extends StackPane {

    public static final double DEFAULT_CANVAS_WIDTH = 700D;
    public static final double DEFAULT_CANVAS_HEIGHT = 450D;

    private TurtleCanvas myBackgroundCanvas;
    private TurtleCanvas myDrawingCanvas;
    private DisplayView myCurrentDisplayView;

    public StackedCanvasPane(){
        super();
        myBackgroundCanvas = createBackgroundCanvas(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT);
        myDrawingCanvas = new TurtleCanvas(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT);
        myCurrentDisplayView = new BasicTurtleView(myDrawingCanvas);
        getChildren().addAll(myBackgroundCanvas, myDrawingCanvas, myCurrentDisplayView);
    }

    private TurtleCanvas createBackgroundCanvas(double width, double height) {
        TurtleCanvas canvas = new TurtleCanvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.rect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.fill();
        return canvas;
    }

    public Consumer<Color> getBackgroundColorAccess(){
        return myBackgroundCanvas.getBackgroundColorAccess();
    }

    public Consumer<Color> getPenPropertiesAccess(){
        return myDrawingCanvas.getPenColorAccess();
    }

    public Consumer<String> getIconAccess(){
        Consumer<String> changeIcon = (x) -> {
            this.getChildren().remove(myCurrentDisplayView);
            myCurrentDisplayView = new DisplayViewFactory().generateDislplayView(x, myCurrentDisplayView);
            this.getChildren().add(myCurrentDisplayView);
        };
        return changeIcon;
    }

    public void addMove(Move move){
        myCurrentDisplayView.addMove(move);
        myCurrentDisplayView.drawPath();
    }

    public void addAllMoves(List<Move> moves){
        myCurrentDisplayView.addAllMoves(moves);
    }

    public void batchUpdateCanvas(){
        myCurrentDisplayView.drawPath();
    }
}
