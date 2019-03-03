package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;

public class Right extends CommandNode{
    private static final int WRONG_INPUT = 0;
    public Right(String name){
        super(name);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        double degrees = myChildren.get(0).evaluate(myVisCommands, myTurtles);
        for (Integer id: myTurtles.getActiveTurtlesIDs()) {
            myTurtles.get(id).updateHeading(1.0 * degrees);
            myVisCommands.add(new VisualTurtleTurn(id,1.0 * degrees));
        }
        return degrees;
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 1)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}
