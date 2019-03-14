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
public class Ask extends CommandNode {
    public Ask(String a){
        super(a);
    }
    /**
     * This CommandNode has two children - list of turtles to be set active and a list of commands for those turtles  to complete.
     * This method first collects the ids of the asked turtles and then sets those turtles as active in order for the
     * proper visual commands to be constructed. This method then evaluates those commands and sets the original list
     * of active turtles to be active again
     * @return result of final command evaluated
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        CommandNode myTurtleList = super.getChildren().get(0);
        CommandNode myCommandList = super.getChildren().get(1);

        List<Integer> myOldActiveTurtles = myTurtles.getActiveTurtleIDs();
        List<Integer> myTurtleIDs = new ArrayList<>();


        for (CommandNode turtleInput: myTurtleList.getChildren()) {
            int turtleID = (int)turtleInput.evaluate(myVisCommands, myTurtles);
            if (turtleID > 0 & turtleID <= myTurtles.size())
                myTurtleIDs.add(turtleID - 1);
        }

        myTurtles.setActiveTurtles(myTurtleIDs);
        double ret = myCommandList.evaluate(myVisCommands,myTurtles);
        myTurtles.setActiveTurtles(myOldActiveTurtles);

        return ret;
    }

}
