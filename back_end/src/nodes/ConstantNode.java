package nodes;

import apis.ImmutableVisualCommand;
import turtle.Turtle;


import java.util.List;


public class ConstantNode extends CommandNode{

    private double myValue;

    public ConstantNode(String val){
        super(val);
        myValue = Double.parseDouble(val);
    }
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Turtle myTurtle){
        return myValue;
    }

}
