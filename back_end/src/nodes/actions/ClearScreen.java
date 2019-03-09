package nodes.actions;

import apis.ImmutableVisualCommand;
import nodes.CommandNode;
import nodes.visuals.VisualClearScreen;
import turtle.Bale;
import turtle.Turtle;

import java.util.List;

public class ClearScreen extends CommandNode {
    public ClearScreen(String name){
        super(name);
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        double currXCoor = myTurtles.getXCoor();
        double currYCoor = myTurtles.getYCoor();
        myTurtles.clear();
        myTurtles.add(new Turtle(0));
        myVisCommands.add(new VisualClearScreen());
        return Math.sqrt(Math.pow(currXCoor,2) + Math.pow(currYCoor,2));
    }
    @Override
    public void addChild(CommandNode c){
        throw new IllegalArgumentException();
    }
}
