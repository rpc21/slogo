package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;

public class SetHeading extends CommandNode {
    public SetHeading(String name){
        super(name);
    }
    /**
     * TODO - Use immutable turtle state to calculate degrees moved, rather than the new heading
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        double degrees = super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        double currHeading = 0;
        for (Integer id: myTurtles.getActiveTurtlesIDs()) {
            VisualTurtleHeading myHeading = new VisualTurtleHeading(id,degrees);
            currHeading = myTurtles.get(id).getHeading();
            myTurtles.get(id).setHeading(degrees);
            myVisCommands.add(new VisualTurtleHeading(id,degrees));
        }
        return Math.abs(currHeading - degrees);
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 1)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}
