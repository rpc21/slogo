package nodes;
import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.Arrays;
import java.util.List;

public class Home extends TurtleCommand {
    public Home(String name){
        super(name);
    }
    private static final String methodName= "goHome";
    /**
     * TODO - Figure out how to return all the needed visual commands
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {

        super.setMyTurtleCommands(methodName);
        myVisCommands.addAll(super.invokeTurtles(new Object[]{},myTurtles));
        return 0;
    }
    @Override
    public void addChild(CommandNode c){
        throw new IllegalArgumentException();
    }
}
