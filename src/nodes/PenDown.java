package nodes;


import GUI.StackedCanvasPane;
import apis.CanvasAPI;

public class PenDown extends VisualCommand {
    public void execute(CanvasAPI myCanvas){
        myCanvas.setPenDown();
    }
}
