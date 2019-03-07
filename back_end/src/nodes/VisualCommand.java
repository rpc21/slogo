package nodes;

import apis.ImmutableVisualCommand;
import apis.VisualUpdateAPI;

public abstract class VisualCommand implements ImmutableVisualCommand {
    public abstract void execute(VisualUpdateAPI myCanvas);
}
