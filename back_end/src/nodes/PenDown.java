package nodes;

import apis.CanvasAPI;

public class PenDown extends VisualCommand {
    public void execute(CanvasAPI myCanvas){
        myCanvas.setPenDown();
    }
}
