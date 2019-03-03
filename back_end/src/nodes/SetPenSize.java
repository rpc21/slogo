package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;

public class SetPenSize extends CommandNode {
    public SetPenSize(String n){
        super(n);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        double pixels = super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        myVisCommands.add(new VisualPenSize(pixels));
        return pixels;
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() ==1)
            throw new IllegalArgumentException();
        super.addChild(c);
    }

}
