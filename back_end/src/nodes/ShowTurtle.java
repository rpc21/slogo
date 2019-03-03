package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;

public class ShowTurtle extends CommandNode {
    public ShowTurtle(String name){
        super(name);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        myVisCommands.add(new VisualShowTurtle());
        myTurtles.get(0).setVisibility(true);
        return 0;
    }
    @Override
    public void addChild(CommandNode c){
        throw new IllegalArgumentException();
    }
}
