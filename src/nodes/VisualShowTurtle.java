package nodes;
import apis.CanvasAPI;

public class VisualShowTurtle extends VisualCommand {
    public void execute(CanvasAPI myCanvas){
        myCanvas.hideTurtle();
    }
}
