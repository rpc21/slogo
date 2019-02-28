package nodes;

import apis.ImmutableVisualCommand;

import java.util.List;

public class Make extends CommandNode{
    public Make(String n){
        super(n);
    }
    /**
     * TODO - Use lambda to Parser's addVariable method to add this variable as a possible variable
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands) {
        String varName = myChildren.get(0).getName();
        double varValue = myChildren.get(1).evaluate(myVisCommands);
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
