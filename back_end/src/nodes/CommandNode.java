package nodes;
import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import turtle.Bale;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public abstract class CommandNode {
    private List<CommandNode> myChildren = new ArrayList<>();
    private String myName;
    private static final String COMMAND_ARGUMENTS =  "commands/Arguments";
    private ResourceBundle myResources;
    private int maxChildren;
    /**
     * Reads in maximum number of children this CommandNode can have from the resources file, if not available, sets
     * number of permitted children to 0 for error checking purposes
     */
    public CommandNode(String name){
        myName = name;
        myResources = ResourceBundle.getBundle(COMMAND_ARGUMENTS);
        if (myResources.containsKey(name)) {
            maxChildren = getMaxChildren();
        }
        else {
            maxChildren = 0;
        }
    }
    private int getMaxChildren(){
        DecimalFormat df = new DecimalFormat();
        Number num = df.parse(myResources.getString(myName), new ParsePosition(0));
        return num.intValue();
    }
    /**
     * evaluates this node's value based upon its particular implementation
     * @param myVisCommands is a collection of visual commands that any command node may add to as the tree of commands
     *                      is being built.
     * @param myTurtles is a collection of Turtles that handles setting particular sets of turtles active and requesting
     *                  individual Turtles to return a visual command associated with its ID if necessary
     * @return result of the evaluation of a command, as every command in this IDE returns a value
     * @exception InvalidInputException is thrown when a user stores inputs an improper parameter with a particular command
     *
     */
    public abstract double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException;
    /**
     * @return list of this Node's children - these will be used as arguments to evaluate a particular node
     */
    public List<CommandNode> getChildren(){
        return myChildren;
    }

    /**
     * @param child is a direct child of this CommandNode to be added to its current list of children
     */
    public void addChild(CommandNode child){
        if (myChildren.size() >= maxChildren)
            throw new IllegalArgumentException();
        myChildren.add(child);
    }

    /**
     * @return whether or not this node will need to know its name later on in order to evaluate itself
     */
    public boolean needsName(){
        return false;
    }

    /**
     * @return whether or not this node will need access to the user's current list of variables or methods in order
     *         to get or store new values
     */
    public boolean needsUserCreated(){ return false;}
    /**
     * @return whether or not this
     */
    public boolean isMethodDeclaration() {return false;}

    /**
     * @return the name associated with this node
     */
    @Override
    public String toString(){
        return myName;
    }

}
