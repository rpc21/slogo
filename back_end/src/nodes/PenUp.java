package nodes;

import apis.CanvasAPI;
import nodes.VisualCommand;

public class PenUp extends VisualCommand {
    public void execute(CanvasAPI myCanvas){
        myCanvas.setPenUp();
    }
}
