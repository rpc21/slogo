package nodes.structures;

import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import parser.UserCreated;
import turtle.Bale;

import java.util.ArrayList;
import java.util.List;

public class UserInstruction extends CommandNode {
    private UserCreated myUserCreatedItems;
    private String myUserMethodName;
    public UserInstruction(String n){
        super(n);
        myUserMethodName = n;
    }
    public UserInstruction(String n, UserCreated user){
        super(n);
        myUserCreatedItems = user;
        myUserMethodName = n;
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {

        CommandNode listOfVariableValues = super.getChildren().get(0);
        List<Double> myVariableValues = new ArrayList<>();
        for (CommandNode c: listOfVariableValues.getChildren())
            myVariableValues.add(c.evaluate(myVisCommands,myTurtles));

        myUserCreatedItems.setUpLocalVariables(myUserMethodName,myVariableValues);
        CommandNode listOfCommands = super.getChildren().get(1);
        double ret = 0.0;
        for (CommandNode c : listOfCommands.getChildren())
            ret = c.evaluate(myVisCommands,myTurtles);
        return ret;
    }
    @Override
    public boolean needsUserCreated(){
        return true;
    }
}
