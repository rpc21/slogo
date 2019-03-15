package nodes.visuals;
import apis.ImmutableVisualCommand;
import apis.VisualUpdateAPI;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class VisualActiveTurtles implements ImmutableVisualCommand {
    private final List<Integer> myActiveIDs;
    public VisualActiveTurtles(List<Integer> ids){
        myActiveIDs = ids;
    }
    /**
     * Invokes canvas to update its current active turtles
     */
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.setActiveTurtles(myActiveIDs);
    }


}
