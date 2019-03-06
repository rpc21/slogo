package nodes;

import apis.VisualUpdateAPI;

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
