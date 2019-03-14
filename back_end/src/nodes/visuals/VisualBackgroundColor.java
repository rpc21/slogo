package nodes.visuals;
import apis.VisualUpdateAPI;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class VisualBackgroundColor extends VisualCommand {
    private int myIndex;
    public VisualBackgroundColor(int index){
        myIndex = index;
    }
    /**
     * Invokes canvas to change its background color
     */
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.setBackgroundColor(myIndex);
    }
}
