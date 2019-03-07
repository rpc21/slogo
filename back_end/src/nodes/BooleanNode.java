package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;



public abstract class BooleanNode extends CommandNode {

    public BooleanNode(String name){
        super(name);
    }


    protected double getFirstExpression(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles){
        return super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
    }
    protected double getSecondExpression(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles){
        return super.getChildren().get(1).evaluate(myVisCommands, myTurtles);
    }

    @Override
    public abstract double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles);

    @Override
    public void addChild(CommandNode c) {
        super.addChild(c);
    }
}
