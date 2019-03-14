package nodes.structures;
import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import turtle.Bale;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */

public class AskWith extends CommandNode {
    public AskWith(String a){
        super(a);
    }
    /**
     * This CommandNode has two children - a conditional and a list of commands for the turtles that fulfill the first
     * conditional to complete. This method first has to check each turtle separately to assess whether or not they should
     * participate in the list of commands and then set those turtles as active in order for the proper visual commands to be
     * constructed. This method then evaluates those commands and sets the original list of active turtles to be active
     * again
     * @return result of final command evaluated
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        CommandNode conditional = super.getChildren().get(0);
        List<Integer> myOldActiveTurtles = myTurtles.getActiveTurtleIDs();
        List<Integer> myTurtleIDs = new ArrayList<>();

        for (Integer id: myTurtles.getAllIDs()) {
            myTurtles.setActiveTurtles(id);
            if (conditional.evaluate(myVisCommands,myTurtles) != 0.0 ) {
                myTurtleIDs.add(id);
            }
        }

        myTurtles.setActiveTurtles(myTurtleIDs);

        CommandNode innerCommands = super.getChildren().get(1);
        double ret = innerCommands.evaluate(myVisCommands,myTurtles);

        myTurtles.setActiveTurtles(myOldActiveTurtles);
        return ret;
    }

}
