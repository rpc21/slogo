package nodes.visuals;

import apis.VisualUpdateAPI;

public class VisualPenColor extends VisualCommand {
    private int myID;
    private int myIndex;
    public VisualPenColor(int id, int index){
        myID = id;
        myIndex = index;
    }
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.setPenColor(myID, myIndex);
    }
}