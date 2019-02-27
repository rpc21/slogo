package nodes;

import java.util.List;

public class Left extends CommandNode{
    public Left(String name){
        super(name);
    }
    @Override
    public double evaluate(List<VisualCommand> myVisCommands) {
        double degrees = myChildren.get(0).evaluate(myVisCommands);
        myVisCommands.add(new VisualTurtleLeft(degrees));
        return degrees;
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 1)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}
