package nodes.visuals;

import apis.VisualUpdateAPI;
import nodes.visuals.VisualCommand;

public class VisualTurtleTell extends VisualCommand {
    private int numTurtles;
    public VisualTurtleTell(int turtles){
        numTurtles = turtles;
    }

    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        System.out.println(numTurtles);
        myCanvas.addTurtles(numTurtles);
    }
}
