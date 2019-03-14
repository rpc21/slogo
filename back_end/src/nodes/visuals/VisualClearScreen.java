package nodes.visuals;
import apis.VisualUpdateAPI;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class VisualClearScreen extends VisualCommand {
    /**
     * Invokes canvas to clear its screen
     */
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.clearScreen();
    }
}