package nodes.arithmetics;
import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import turtle.Bale;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class ArcTangent extends CommandNode {
    public ArcTangent(String commandName) {
        super(commandName);
    }
    /**
     * @return ArcTangent value of child node. Converted to degrees, as all other trig operations in this IDE
     * use degrees, so this may help any confusion
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
            return Math.toDegrees(Math.atan(super.getChildren().get(0).evaluate(myVisCommands, myTurtles)));
    }

}
