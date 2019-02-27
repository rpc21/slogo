package nodes;

import java.util.List;

public class SetHeading extends CommandNode {
    public SetHeading(String name){
        super(name);
    }
    /**
     * TODO - Use immutable turtle state to calculate degrees moved, rather than the new heading
     */
    @Override
    public double evaluate(List<VisualCommand> myVisCommands) {
        double degrees = super.getChildren().get(0).evaluate(myVisCommands);
        VisualTurtleHeading myHeading = new VisualTurtleHeading(degrees);
        myVisCommands.add(new VisualTurtleHeading(degrees));

        return degrees;
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 1)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}
