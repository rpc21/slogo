package nodes.structures;
import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import nodes.visuals.VisualActiveTurtles;
import nodes.visuals.VisualTurtleTell;
import turtle.Bale;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class Tell extends CommandNode {
    public Tell(String a){
        super(a);
    }
    /**
     * This CommandNode has one child - a list of turtle ids to be set as active. Should a turtle id be outside of the
     * bounds (less than 1), it will be ignored, and if there are any turtle ids that exceed the current highest one,
     * myTurtles will handle creating that many turtles, up to and including the maximum id in the list. To notify
     * visualization, VisualTurtleTell will invoke the GUI to display the new turtles if needed
     * @return the last turtleID to be set as active
     * @see VisualTurtleTell
     */
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        CommandNode myListNode = super.getChildren().get(0);
        List<Integer> myTurtleIDs = new ArrayList<>();

        int turtleID = 0;
        for (CommandNode turtleInput: myListNode.getChildren()) {
            turtleID = (int)turtleInput.evaluate(myVisCommands, myTurtles);
            if (turtleID > 0) {
                myTurtleIDs.add(turtleID - 1);
            }
        }

        int currentTurtleCount = myTurtles.size();
        myTurtles.makeTurtles(myTurtleIDs);
        myTurtles.setActiveTurtles(myTurtleIDs);

        if (currentTurtleCount != myTurtles.size()) {
            myVisCommands.add(new VisualTurtleTell(myTurtles.size() - currentTurtleCount));
        }
        myVisCommands.add(new VisualActiveTurtles(myTurtleIDs));
        return turtleID;
    }

}
