package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.Arrays;
import java.util.List;


public abstract class TurtleCommand extends CommandNode{
    private static List<String> myTurtleCommands;
    
    public TurtleCommand(String name) {
        super(name);
    }
    
    protected void setMyTurtleCommands(String commandName){
        myTurtleCommands = Arrays.asList(commandName);
    }
    
    protected List<ImmutableVisualCommand> invokeTurtles(Object[] myParameters, Bale myTurtles){
        List<ImmutableVisualCommand> moveVisCommands = myTurtles.act(myTurtleCommands,myParameters);
        return moveVisCommands;
    }

}
