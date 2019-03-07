package nodes.visuals;

import apis.ImmutableVisualCommand;
import apis.VisualUpdateAPI;

import java.util.List;

public class VisualActiveTurtles implements ImmutableVisualCommand {
    List<Integer> myActiveIDs;
    public VisualActiveTurtles(List<Integer> ids){
        myActiveIDs = ids;
    }
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.setActiveTurtles(myActiveIDs);
    }


}
