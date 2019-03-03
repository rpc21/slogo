package nodes;

import apis.VisualUpdateAPI;

public class VisualPenDown extends VisualCommand {
    private int myID;
    public VisualPenDown(int id){
        myID = id;
    }
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.setPenDown(myID);
    }
}