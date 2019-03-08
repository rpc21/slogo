package nodes.structures;

import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import parser.UserCreated;
import turtle.Bale;

import java.util.List;

public class For extends CommandNode {
    private static final int ITERATOR = 0;
    private static final int START = 1;
    private static final int END = 2;
    private static final int INCREMENT = 3;
    private static final int LISTNODE = 4;
    private UserCreated myUserCreatedItems;

    public For(String a){
        super(a);
    }
    public For(String a, UserCreated user) {
        super(a);
        myUserCreatedItems = user;
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        CommandNode initalizeList = super.getChildren().get(0);
        CommandNode innerCommands = super.getChildren().get(1);
        String name = initalizeList.getChildren().get(ITERATOR).getName();
        double start = initalizeList.getChildren().get(START).evaluate(myVisCommands,myTurtles);
        double end = initalizeList.getChildren().get(END).evaluate(myVisCommands,myTurtles);
        double increment = initalizeList.getChildren().get(INCREMENT).evaluate(myVisCommands,myTurtles);
        myUserCreatedItems.addVariable(name,start);

        //need to verify that these values will not cause an infinite loop
        if ( (start < end && increment <= 0) ||  (start > end && increment >= 0)) {
            throw new IllegalArgumentException();
        }

        double ret = 0;
        while (start < end) {
            for (CommandNode loopCommand: innerCommands.getChildren()) {
                ret = loopCommand.evaluate(myVisCommands, myTurtles);
            }
            start += increment;
            myUserCreatedItems.addVariable(name,start);
        }
        return ret;
    }
    @Override
    public boolean needsUserCreated(){ return true;}
}
