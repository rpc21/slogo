import java.util.ArrayList;
import java.util.Collection;

public abstract class CommandNode {
    Collection<CommandNode> myChildren = new ArrayList<CommandNode>();
    /**
     * evaluates this node's value based upon its particular implementation
     */
    public CommandNode(String name){

    }
    /**
     * evaluates this node's value based upon its particular implementation
     */
    public abstract double evaluate();
    /**
     * returns list of this Node's children - these will be used as arguments to evaluate a CommandNode
     */
    protected Collection<CommandNode> getChildren(){
        return myChildren;
    }
    /**
     * adds a CommandNode to this CommandNode's list of children
     */
    protected void addChild(CommandNode c){
        myChildren.add(c);
    }

}
