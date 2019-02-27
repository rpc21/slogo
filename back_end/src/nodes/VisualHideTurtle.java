package nodes;
        import apis.CanvasAPI;

public class VisualHideTurtle extends VisualCommand {
    public void execute(CanvasAPI myCanvas){
        myCanvas.hideTurtle();
    }
}
