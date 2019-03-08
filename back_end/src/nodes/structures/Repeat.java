package nodes.structures;

import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import parser.UserCreated;
import turtle.Bale;

import java.util.List;

public class Repeat extends CommandNode {

    private UserCreated myUserCreatedItems;
    private static final String repeatVariable  = ":repcount";
    public Repeat(String a) {
        super(a);
    }
    public Repeat(String a, UserCreated user) {
        super(a);
       myUserCreatedItems = user;
       user.addVariable(repeatVariable,1.0);
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        double ret = 0;
        double iter = 1;

        int numIterations = (int)super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        CommandNode myListNode = super.getChildren().get(1);

        for (int i = 0; i < numIterations; i++){
            ret = myListNode.evaluate(myVisCommands,myTurtles);
            iter += 1;
            myUserCreatedItems.addVariable(repeatVariable,iter);
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