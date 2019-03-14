/*
 * @author Anna Darwish
 * @version 3/13/2019
 */
package nodes;

import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
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


    protected List<ImmutableVisualCommand> invokeTurtles(Object[] myParameters, Bale myTurtles) throws InvalidInputException {
        List<ImmutableVisualCommand> myVisualCommands =  myTurtles.act(myTurtleCommands,myParameters);
        myTurtleCommands.clear();
        return myVisualCommands;
    }

}
