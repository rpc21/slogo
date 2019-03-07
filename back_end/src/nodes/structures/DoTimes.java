package nodes.structures;

import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import parser.UserCreated;
import turtle.Bale;

import java.util.List;

public class DoTimes extends CommandNode {
    private static final int NUM_ITERATIONS = 0;
    private static final int FIRST_COMMAND = 1;
    private UserCreated myUserCreatedItems;
    public DoTimes(String a) {
        super(a);
    }
    public DoTimes(String a, UserCreated user) {
        super(a);
        myUserCreatedItems = user;
    }
    /**
     * TODO - Initalize specific variable name and update with each iteration
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
    //initialize specific variable name and update with each iteration
        double ret = 0;
        String varName = super.getChildren().get(0).getName();
        myUserCreatedItems.addVariable(varName,1.0);

        int numIterations = (int)super.getChildren().get(1).evaluate(myVisCommands, myTurtles);
        for (int iter = NUM_ITERATIONS; iter < numIterations; iter++){
            for (int currChild = FIRST_COMMAND; currChild < super.getChildren().size(); currChild++)
                ret = super.getChildren().get(currChild).evaluate(myVisCommands, myTurtles);
            myUserCreatedItems.addVariable(varName,NUM_ITERATIONS + 2.0);
        }
        return ret;
    }

    @Override
    public boolean needsName(){return true;}
    @Override
    public void addChild(CommandNode c) {
        super.addChild(c);
    }

    @Override
    public boolean needsUserCreated(){ return true;}
}