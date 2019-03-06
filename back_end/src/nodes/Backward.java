package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;

public class Backward extends CommandNode {
    public Backward(String name){
        super(name);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles)  {
        double pixels = myChildren.get(0).evaluate(myVisCommands, myTurtles);
        for (Integer id: myTurtles.getActiveTurtlesIDs()) {
            VisualTurtleMove myVisMove = new VisualTurtleMove(id, -1.0 * pixels, myTurtles.get(0).getHeading());
            myVisCommands.add(myVisMove);
            myTurtles.get(0).setXCoor(myTurtles.get(0).getXCoor() + myVisMove.getXDelta());
            myTurtles.get(0).setYCoor(myTurtles.get(0).getYCoor() + myVisMove.getYDelta());
        }
        return pixels;
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 1)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}
