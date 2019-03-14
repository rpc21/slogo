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
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        CommandNode conditional = super.getChildren().get(0);
        List<Integer> myOldActiveTurtles = myTurtles.getActiveTurtleIDs();
        List<Integer> myTurtleIDs = new ArrayList<>();

        for (Integer id: myTurtles.getAllIDs()) {
            myTurtles.setActiveTurtles(id);
            myTurtles.setMyActiveID(id);
            if (conditional.evaluate(myVisCommands,myTurtles) != 0.0 ) {
                myTurtleIDs.add(id);
            }
        }

        myTurtles.setActiveTurtles(myTurtleIDs);
        double ret = 0;
        CommandNode innerCommands = super.getChildren().get(1);
        for (CommandNode command: innerCommands.getChildren())
            ret = command.evaluate(myVisCommands,myTurtles);

        myTurtles.setActiveTurtles(myOldActiveTurtles);
        return ret;
    }

}
