package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.ArrayList;
import java.util.List;


public abstract class TurtleCommand extends CommandNode{
    private List<String> myTurtleCommands = new ArrayList<>();
    
    public TurtleCommand(String name) {
        super(name);
    }
    
    protected void setMyTurtleCommands(String commandName){
        myTurtleCommands.add(commandName);
    }
    protected void setMyTurtleCommands(List<String> commandNames){
        myTurtleCommands.addAll(commandNames);
    }
    
    protected List<ImmutableVisualCommand> invokeTurtles(Object[] myParameters, Bale myTurtles){
        List<ImmutableVisualCommand> moveVisCommands = myTurtles.act(myTurtleCommands,myParameters);
        return moveVisCommands;
    }

}
