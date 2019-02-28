package nodes;

import apis.ImmutableVisualCommand;

import java.util.List;

public class Right extends CommandNode{
    private static final int WRONG_INPUT = 0;
    public Right(String name){
        super(name);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands) {
        double degrees = myChildren.get(0).evaluate(myVisCommands);
        myVisCommands.add(new VisualTurtleRight(degrees));
        return degrees;
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 1)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}
