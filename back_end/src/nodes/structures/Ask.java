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
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        CommandNode myTurtleList = super.getChildren().get(0);
        CommandNode myCommandList = super.getChildren().get(1);

        List<Integer> myOldActiveTurtles = myTurtles.getActiveTurtleIDs();
        List<Integer> myTurtleIDs = new ArrayList<>();

        double ret = 0.0;

        for (CommandNode c: myTurtleList.getChildren()) {
            ret = c.evaluate(myVisCommands, myTurtles);
            if ((int)ret > 0 & (int)ret <= myTurtles.size())
                myTurtleIDs.add((int)ret - 1);
        }

        myTurtles.setActiveTurtles(myTurtleIDs);
        for (CommandNode c: myCommandList.getChildren()) {
            ret = c.evaluate(myVisCommands, myTurtles);
        }

        myTurtles.setActiveTurtles(myOldActiveTurtles);
        return ret;
    }

}
