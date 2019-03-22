package nodes.structures;
import apis.ImmutableVisualCommand;
import exceptions.InvalidCommandException;
import exceptions.external.InvalidInputException;
import nodes.CommandNode;
import parser.UserCreated;
import turtle.Bale;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class For extends CommandNode {
    private static final int ITERATOR = 0;
    private static final int START = 1;
    private static final int END = 2;
    private static final int INCREMENT = 3;
    private UserCreated myUserCreatedItems;

    public For(String a){
        super(a);
    }
    public For(String a, UserCreated user) {
        super(a);
        myUserCreatedItems = user;
    }
    /**
     * This CommandNode has two children - the first is a ListNode of information to initialize the for loop , such as
     * the iterator variable name and its range of values, and the second is a ListNode of commands that will all be invoked
     * over its range of values, all while updating the iterator variable's value at the end of each loop
     * @return result of final command run in list of innerCommands
     * @see ListNode
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        CommandNode initializeList = super.getChildren().get(0);
        CommandNode innerCommands = super.getChildren().get(1);

       String name = initializeList.getChildren().get(ITERATOR).toString();
       double start = initializeList.getChildren().get(START).evaluate(myVisCommands, myTurtles);
       double end = initializeList.getChildren().get(END).evaluate(myVisCommands, myTurtles);
       double increment = initializeList.getChildren().get(INCREMENT).evaluate(myVisCommands, myTurtles);

        myUserCreatedItems.addVariable(name,start);
        validateForLoop(start,end,increment);

        double ret = 0;
        while (start < end) {
            ret = innerCommands.evaluate(myVisCommands, myTurtles);
            start += increment;
            myUserCreatedItems.addVariable(name, start);
        }
        return ret;

    }
    /**
     * This CommandNode needs access to the UserCreated class in order to update the iterator variable in case one of the
     * inner commands requires its value as an argument to some calculation
     */
    @Override
    public boolean needsUserCreated(){ return true;}

    private void validateForLoop(double start, double end, double increment){
        if  ((start < end && increment <= 0) ||  (start > end && increment >= 0))
            throw new IllegalArgumentException();
    }
}
