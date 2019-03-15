package nodes.visuals;
import apis.VisualUpdateAPI;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class VisualPalette extends VisualCommand{
    private final int myIndex;
    private final int myRed;
    private final int myGreen;
    private final int myBlue;

    public VisualPalette(int index, int r, int g, int b){
        myIndex = index;
        myRed = r;
        myGreen = g;
        myBlue = b;
    }
    /**
     * Invokes front end to set the color associated with myIndex to have myRed, myGreen, and myBlue RGB values
     * @see VisualUpdateAPI
     */
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.setPalette(myIndex,myRed,myBlue,myGreen);
    }

}
