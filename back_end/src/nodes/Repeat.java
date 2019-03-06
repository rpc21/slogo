package nodes;

import apis.AddVariable;
import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;

public class Repeat extends CommandNode{

    private AddVariable myAddVarFunction;
    private static final String repeatVariable  = "repcount";
    public Repeat(String a) {
        super(a);
    }
    public Repeat(String a, AddVariable add) {
        super(a);
        myAddVarFunction = add;
    }
    /**
     * TODO - Initalize repcount and update with each iteration
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        double ret = 0;
        double iter = 1.0;

        myAddVarFunction.addNewVariable(repeatVariable,iter);
        int numIterations = (int)super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        CommandNode myListNode = super.getChildren().get(1);
        
        for (int i = 0; i < numIterations; i++){
            ret = myListNode.evaluate(myVisCommands,myTurtles);
            myAddVarFunction.addNewVariable(repeatVariable,iter ++);
        }
        return ret;
    }

    @Override
    public void addChild(CommandNode c) {
        super.addChild(c);
    }
}