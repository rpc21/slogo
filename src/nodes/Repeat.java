package nodes;

import java.util.List;

public class Repeat extends CommandNode{
    private static final int REPEAT_COUNT = 0;
    private static final int COMMANDS_TO_EXECUTE = 1;
    public Repeat(String a) {
        super(a);
    }

    @Override
    public double evaluate(List<VisualCommand> myVisCommands) {
        int iterations = (int)(super.getChildren().get(REPEAT_COUNT).evaluate(myVisCommands));
        int i = 1;
        double ret = 0;
        while (i < iterations){
            ret = super.getChildren().get(COMMANDS_TO_EXECUTE).evaluate(myVisCommands);
        }
        return ret;
    }

    /**
     * Adds an addend to this nodes.SumNode's list of Children as main.Parser reads them in
     *
     * @TODO Read in possible Argument issues from a resources file to ensure parameter specifications are satisfied
     */
    @Override
    public void addChild(CommandNode c) {
        if (super.getChildren().size() == 2)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}