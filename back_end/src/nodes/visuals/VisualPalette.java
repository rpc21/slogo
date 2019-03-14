package nodes.visuals;

import apis.VisualUpdateAPI;

public class VisualPalette extends VisualCommand{
    private int myIndex;
    private int myRed;
    private int myGreen;
    private int myBlue;

    public VisualPalette(int index, int r, int g, int b){
        myIndex = index;
        myRed = r;
        myGreen = g;
        myBlue = b;
    }

    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.setPalette(myIndex,myRed,myBlue,myGreen);
    }

}
