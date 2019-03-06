package nodes;

import apis.VisualUpdateAPI;

public class VisualPenUp extends VisualCommand {
    private int myID;
    public VisualPenUp(int id){
        myID = id;
    }
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.setPenUp(myID);
    }
}