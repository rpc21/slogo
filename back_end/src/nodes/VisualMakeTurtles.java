package nodes;

import apis.VisualUpdateAPI;

public class VisualMakeTurtles extends VisualCommand {
    private int numberOfNewTurtles;
    public VisualMakeTurtles(int numTurtles){
        numberOfNewTurtles = numTurtles;
    }
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        //need to tell visualization to make x amount of new turtles
    }
}
