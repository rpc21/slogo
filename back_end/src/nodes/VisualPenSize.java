package nodes;

import apis.VisualUpdateAPI;

public class VisualPenSize extends VisualCommand {
    private int myID;
    private double myPenPixels;
    public VisualPenSize(int id, double p){
        myID = id;
        myPenPixels = p;
    }
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.setPenSize(myID,myPenPixels);
    }
}