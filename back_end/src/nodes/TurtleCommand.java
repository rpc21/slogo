package nodes;
import apis.ImmutableVisualCommand;
import exceptions.external.InvalidInputException;
import turtle.Bale;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public abstract class TurtleCommand extends CommandNode{
    private List<String> myTurtleCommands = new ArrayList<>();
    
    public TurtleCommand(String name) {
        super(name);
    }
    
    protected void setMyTurtleCommands(String commandName){
        myTurtleCommands.add(commandName);
    }


    /**
     * This method invokes myTurtles to complete the list of turtle commands currently listed and generate the
     * associated visual commands for the GUI to execute. Clears the list of current turtle commands afterwards
     * in case myTurtles are invoked to act again, because otherwise, the visual commands would stack up from previous
     * runs
     * @return list of visual commands associated with turtle commands
     */
    protected List<ImmutableVisualCommand> invokeTurtles(Object[] myParameters, Bale myTurtles) throws InvalidInputException {
        List<ImmutableVisualCommand> myVisualCommands =  myTurtles.act(myTurtleCommands,myParameters);
        myTurtleCommands.clear();
        return myVisualCommands;
    }

    protected double invokeTurtles(double param, String commandName, List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        setMyTurtleCommands(commandName);
        myVisCommands.addAll(invokeTurtles(new Object[]{param},myTurtles));
        return param;
    }

}
