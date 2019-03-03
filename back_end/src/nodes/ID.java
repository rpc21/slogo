package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;

public class ID extends CommandNode{
    public ID(String n){
        super(n);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        return myTurtles.getActiveID();
    }
    @Override
    public void addChild(CommandNode c){
        throw new IllegalArgumentException();
    }
}
