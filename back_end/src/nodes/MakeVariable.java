package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;

public class MakeVariable extends CommandNode{
    public MakeVariable(String a){
        super(a);
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles){
        return super.getChildren().get(1).evaluate(myVisCommands, myTurtles);
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 2)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
    @Override
    public boolean needsName(){
        return true;
    }

}
