package nodes.visuals;
import apis.VisualUpdateAPI;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class VisualAddVariable extends VisualCommand {
    private String myName;
    private Double myValue;
    public VisualAddVariable(String name, Double value){
        myName = name;
        myValue = value;
    }
    /**
     * Invokes canvas to display new user created variable and its value
     */
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.addVariable(myName,myValue);
    }
}
