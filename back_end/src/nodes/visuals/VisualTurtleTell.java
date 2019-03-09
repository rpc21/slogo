package nodes.visuals;

import apis.VisualUpdateAPI;

public class VisualTurtleTell extends VisualCommand {
    private int numTurtles;
    public VisualTurtleTell(int turtles){
        numTurtles = turtles;
    }

    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.addTurtles(numTurtles);
    }
}
