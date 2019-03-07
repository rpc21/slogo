package nodes;

import apis.AddVariable;
import apis.ImmutableVisualCommand;
import parser.UserCreated;
import turtle.Bale;

import java.util.List;

public class For extends CommandNode{
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
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        String name = super.getChildren().get(ITERATOR).getName();
        double start = super.getChildren().get(START).evaluate(myVisCommands,myTurtles);
        myUserCreatedItems.addVariable(name,start);

        double end = super.getChildren().get(END).evaluate(myVisCommands,myTurtles);
        double increment = super.getChildren().get(INCREMENT).evaluate(myVisCommands,myTurtles);

        //need to verify that these values will not cause an infinite loop
        if ( (start < end && increment <= 0) ||  (start > end && increment >= 0)) {
            throw new IllegalArgumentException();
        }

        double ret = 0;
        while (start < end) {
            ret = super.getChildren().get(LISTNODE).evaluate(myVisCommands,myTurtles);
            start += increment;
            myUserCreatedItems.addVariable(name,start);
        }
        return ret;
    }
    @Override
    public boolean needsName(){return true;}
    @Override
    public boolean needsUserCreated(){ return true;}
}
