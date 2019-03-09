package nodes.visuals;

import apis.VisualUpdateAPI;

public class VisualBackgroundColor extends VisualCommand {
    private int myIndex;
    public VisualBackgroundColor(int index){
        myIndex = index;
    }
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.setBackgroundColor(myIndex);
    }
}
