package nodes.visuals;
import apis.ImmutableVisualCommand;
import apis.VisualUpdateAPI;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 * This class invokes the GUI to respond to commands when needed according to its implementation of the methods
 * in VisualUpdateAPI. As the CommandNode tree is evaluated, different command nodes, along with Turtles, may add in
 * to the list of visual commands to be executed by the GUI
 */
public abstract class VisualCommand implements ImmutableVisualCommand {
    /**
     * Invokes an implemented method of the VisualUpdateAPI in the front end to respond to some change or command in
     * the back end appropriately
     * @see VisualUpdateAPI
     */
    public abstract void execute(VisualUpdateAPI myCanvas);
}
