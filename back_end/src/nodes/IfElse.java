package nodes;
import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;

public class IfElse extends CommandNode{
    private static final int IF_STATEMENT= 0;
    private static final int TRUE_PATH = 1;
    private static final int FALSE_PATH = 2;

    public IfElse(String a) {
        super(a);
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        double result = super.getChildren().get(IF_STATEMENT).evaluate(myVisCommands, myTurtles);
        if (result != 0){
            return super.getChildren().get(TRUE_PATH).evaluate(myVisCommands, myTurtles);
        }
        else{
            return super.getChildren().get(FALSE_PATH).evaluate(myVisCommands, myTurtles);
        }
    }

    /**
     * Adds an addend to this nodes.SumNode's list of Children as main.Parser reads them in
     *
     * @TODO Read in possible Argument issues from a resources file to ensure parameter specifications are satisfied
     */
    @Override
    public void addChild(CommandNode c) {
        if (super.getChildren().size() == 3)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}