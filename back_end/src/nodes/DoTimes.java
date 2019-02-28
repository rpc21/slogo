package nodes;

import apis.ImmutableVisualCommand;

import java.util.List;

public class DoTimes extends CommandNode{
    private static final int NUM_ITERATIONS = 0;
    private static final int FIRST_COMMAND = 1;
    public DoTimes(String a) {
        super(a);
    }

    /**
     * TODO - Initalize specific variable name and update with each iteration
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands) {
        //initialize specific variable name and update with each iteration
        double ret = 0;
        int numIterations = (int)super.getChildren().get(1).evaluate(myVisCommands);
        for (int iter = NUM_ITERATIONS; iter < numIterations; iter++){
            for (int currChild = FIRST_COMMAND; currChild < super.getChildren().size(); currChild++)
                ret = super.getChildren().get(currChild).evaluate(myVisCommands);
            //update specific variable name
        }
        return ret;
    }

    @Override
    public void addChild(CommandNode c) {
        super.addChild(c);
    }
}