package nodes;

import apis.ImmutableVisualCommand;
import parser.UserCreated;
import turtle.Bale;

import java.util.List;

public class MakeVariable extends CommandNode{
    private UserCreated myUserCreatedItems;
    public MakeVariable(String a){
        super(a);
    }
    public MakeVariable(String a, UserCreated user) {
        super(a);
        myUserCreatedItems = user;
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles){
        String varName = super.getChildren().get(0).getName();
        double varValue = super.getChildren().get(1).evaluate(myVisCommands, myTurtles);
        myUserCreatedItems.addVariable(varName,varValue);
        myVisCommands.add(new VisualAddVariable(varName,varValue));
        return varValue;
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

    @Override
    public boolean needsUserCreated(){ return true;}

}
