package nodes;
import apis.VisualUpdateAPI;
import apis.ImmutableVisualCommand;

public abstract class VisualCommand implements ImmutableVisualCommand {
    public abstract void execute(VisualUpdateAPI myCanvas);
}
