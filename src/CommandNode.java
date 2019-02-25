import java.util.ArrayList;
import java.util.List;

public abstract class CommandNode {
    public static final int ONE = 1;
    public static final int ZERO = 0;
    List<CommandNode> myChildren = new ArrayList<>();
    /**
     * evaluates this node's value based upon its particular implementation
     */
    public CommandNode(String name){

    }
    /**
     * evaluates this node's value based upon its particular implementation
     */
    public abstract double evaluate(List<VisualCommand> myVisCommands);
    /**
     * returns list of this Node's children - these will be used as arguments to evaluate a CommandNode
     */
    protected List<CommandNode> getChildren(){
        return myChildren;
    }
    /**
     * adds a CommandNode to this CommandNode's list of children
     */
    protected void addChild(CommandNode c){
        myChildren.add(c);
    }

}
