package nodes;

import apis.ImmutableVisualCommand;
import main.Turtle;

import java.util.List;

public class Home extends CommandNode {
    public Home(String name){
        super(name);
    }
    /**
     * TODO - Use immutable turtle state to get current coordinates to return distance moved to go home
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Turtle myTurtle) {
        myTurtle.setXCoor(0);
        myTurtle.setYCoor(0);
        myVisCommands.add(new VisualHomeTurtle());
        return 0;
    }
    @Override
    public void addChild(CommandNode c){
        throw new IllegalArgumentException();
    }
}
