package nodes;

import apis.CanvasAPI;

public class VisualPenSize extends VisualCommand {
    double myPenPixels;
    public VisualPenSize(double p){
        myPenPixels = p;
    }
    @Override
    public void execute(CanvasAPI myCanvas) {

    }
}