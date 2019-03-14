package nodes.visuals;
import apis.VisualUpdateAPI;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class VisualTurtleTell extends VisualCommand {
    private int numTurtles;
    public VisualTurtleTell(int turtles){
        numTurtles = turtles;
    }
    /**
     * Invokes front end to create numTurtles to be displayed
     * @see VisualUpdateAPI
     */
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.addTurtles(numTurtles);
    }
}
