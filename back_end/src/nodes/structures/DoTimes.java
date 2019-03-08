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
        CommandNode myVariable = super.getChildren().get(0);
        CommandNode myCommands = super.getChildren().get(1);

        String varName = myVariable.getChildren().get(0).getName();
        int numIterations = (int)myVariable.getChildren().get(1).evaluate(myVisCommands, myTurtles);
        myUserCreatedItems.addVariable(varName,1.0);


        for (int iter = 0; iter < numIterations; iter++){
            for (int currChild = 0; currChild < myCommands.getChildren().size(); currChild++)
                ret = super.getChildren().get(currChild).evaluate(myVisCommands, myTurtles);
            myUserCreatedItems.addVariable(varName,iter + 2.0);
        }
        return ret;
    }

    @Override
    public void addChild(CommandNode c) {
        super.addChild(c);
    }

    @Override
    public boolean needsUserCreated(){ return true;}
}