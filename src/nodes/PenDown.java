package nodes;


import GUI.StackedCanvasPane;

public class PenDown extends VisualCommand {
    public void execute(StackedCanvasPane myCanvas){
        myCanvas.setPenDown();
    }
}
