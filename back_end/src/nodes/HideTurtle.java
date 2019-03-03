package nodes;
import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;

public class HideTurtle extends CommandNode {
    public HideTurtle(String name){
        super(name);
    }
    @Override public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {

        myVisCommands.add(new VisualHideTurtle());
        myTurtles.get(0).setVisibility(false);
        return 0;
    }
    @Override
    public void addChild(CommandNode c){
        throw new IllegalArgumentException();
    }
}
