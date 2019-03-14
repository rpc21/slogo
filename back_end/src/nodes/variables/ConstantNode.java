package nodes.variables;
import apis.ImmutableVisualCommand;
import nodes.CommandNode;
import turtle.Bale;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class ConstantNode extends CommandNode {
    private double myValue;
    public ConstantNode(String val){
        super(val);
        myValue = Double.parseDouble(val);
    }
    public ConstantNode(double val){
        super("" + val);
        myValue = val;
    }
    /**
     * This is simply a constant that will usually be featured as an argument or child of another CommandNode
     */
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles){
        return myValue;
    }
}
