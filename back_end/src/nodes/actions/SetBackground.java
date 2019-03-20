package nodes.actions;
import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import nodes.visuals.VisualBackgroundColor;
import turtle.Bale;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class SetBackground extends CommandNode {
    public SetBackground(String n){
        super(n);
    }
    /**
     * Evaluates child node to get the new background index and adds a visual command to notify the display
     * to set the background to the color associated with this index
     * @return index of new background color
     * @see VisualBackgroundColor
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        int index = (int)super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        myVisCommands.add(new VisualBackgroundColor(index));
        return index;
    }
}
