package nodes;

import GUI.StackedCanvasPane;
import apis.CanvasAPI;

public abstract class VisualCommand {
    public abstract void execute(CanvasAPI myCanvas);
}
