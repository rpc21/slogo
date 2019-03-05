package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;

public class For extends CommandNode{
    private static final int ITERATOR = 0;
    private static final int END = 1;
    private static final int INCREMENT = 2;
    public For(String a){
        super(a);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        int start = (int)super.getChildren().get(ITERATOR).evaluate(myVisCommands,myTurtles);
        int end = (int)super.getChildren().get(END).evaluate(myVisCommands,myTurtles);
        int increment = (int)super.getChildren().get(INCREMENT).evaluate(myVisCommands,myTurtles);

        //need to verify that these values will not cause an infinite loop
        if ( (start < end & increment <= 0) |  (start > end & increment >= 0))
            throw new IllegalArgumentException();
        double ret = 0;
        while (start < end) {
            for (int exec = 3; exec < super.getChildren().size(); exec++) {
                ret = super.getChildren().get(exec).evaluate(myVisCommands,myTurtles);
            }
            start += increment;
        }
        return ret;
    }
}
