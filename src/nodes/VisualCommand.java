package nodes;
import apis.CanvasAPI;
import apis.ImmutableVisualCommand;

public abstract class VisualCommand implements ImmutableVisualCommand {
    public abstract void execute(CanvasAPI myCanvas);
}
