package nodes;

import apis.AddVariable;
import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;

public class MakeVariable extends CommandNode{
    private AddVariable myAddVarFunction;
    public MakeVariable(String a){
        super(a);
    }
    public MakeVariable(String a, AddVariable add){
        super(a);
        System.out.println(a);
        myAddVarFunction = add;
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles){
        String varName = super.getChildren().get(0).getName();
        System.out.println("%%%%%" + varName);
        double varValue = super.getChildren().get(1).evaluate(myVisCommands, myTurtles);
        myAddVarFunction.addNewVariable(varName,varValue);
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

}
