package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;


import java.util.List;


public class ConstantNode extends CommandNode{

    private double myValue;

    public ConstantNode(String val){
        super(val);
        myValue = Double.parseDouble(val);
    }
    public ConstantNode(double val){
        super("" + val);
        myValue = val;
    }
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles){
        return myValue;
    }
    @Override
    public void addChild(CommandNode c){
        throw new IllegalArgumentException();
    }

}
