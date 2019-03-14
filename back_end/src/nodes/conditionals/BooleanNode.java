package nodes.conditionals;
import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import turtle.Bale;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
abstract class BooleanNode extends CommandNode {
    BooleanNode(String name){
        super(name);
    }
    double getFirstExpression(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        return super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
    }
    double getSecondExpression(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        return super.getChildren().get(1).evaluate(myVisCommands, myTurtles);
    }
}
