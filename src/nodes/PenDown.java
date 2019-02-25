package nodes;

import nodes.VisualCommand;

public class PenDown extends VisualCommand {
    public void execute(StackedCanvasPane myCanvas){
        myCanvas.setPenDown();
    }
}
