package nodes;

import java.util.List;

public class Name extends  CommandNode{
    private String myName;
    private static final int NO_EXECUTE_RESULT = 0;
    public Name(String n){
        super(n);
        myName = n;
    }
    @Override
    public double evaluate(List<VisualCommand> myVisCommands){
        return NO_EXECUTE_RESULT;
    }
    @Override
    public void addChild(CommandNode c){
        throw new IllegalArgumentException();
    }
}
