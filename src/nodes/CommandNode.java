package nodes;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandNode {
    public static final int ONE = 1;
    public static final int ZERO = 0;
    List<CommandNode> myChildren = new ArrayList<>();
    String myName;
    /**
     * evaluates this node's value based upon its particular implementation
     */
    public CommandNode(String name){
        myName = name;
    }
    /**
     * evaluates this node's value based upon its particular implementation
     */
    public abstract double evaluate(List<VisualCommand> myVisCommands);
    /**
     * returns list of this Node's children - these will be used as arguments to evaluate a nodes.CommandNode
     */
    protected List<CommandNode> getChildren(){
        return myChildren;
    }
    /**
     * adds a nodes.CommandNode to this nodes.CommandNode's list of children
     */
    public void addChild(CommandNode c){
        myChildren.add(c);
    }

    public boolean needsName(){
        return false;
    }
    protected String getName(){
        return myName;
    }


}
