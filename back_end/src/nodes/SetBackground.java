package nodes;
import apis.ImmutableVisualCommand;
import turtle.Turtle;
import java.util.List;

public class SetBackground extends CommandNode{
    public SetBackground(String n){
        super(n);
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Turtle myTurtle) {
        int index = (int)super.getChildren().get(0).evaluate(myVisCommands, myTurtle);
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
