package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;
import turtle.Turtle;

import java.util.List;

public class SetShape extends CommandNode {
    public SetShape(String n){
        super(n);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        int index = (int)super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        for (Turtle t: myTurtles.getActiveTurtles())
            t.setShape(index);
        myVisCommands.add(new VisualBackgroundColor(index));
        return index;
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() ==1)
            throw new IllegalArgumentException();
        super.addChild(c);
    }

}
