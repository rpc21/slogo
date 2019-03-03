package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;
import turtle.Turtle;

import java.util.ArrayList;
import java.util.List;

public class AskWith extends CommandNode{
    public AskWith(String a){
        super(a);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        CommandNode conditional = super.getChildren().get(0);
        List<Integer> myTrueTurtles = new ArrayList<Integer>();
        myTurtles.setAllInactive();
        for (Turtle t: myTurtles) {
            t.setActive(true);
            if (conditional.evaluate(myVisCommands, myTurtles) != 0)
                myTrueTurtles.add(t.getID());
            t.setActive(false);
        }
        myTurtles.setActiveTurtles(myTrueTurtles);
        double ret = 0;
        for (int command = 1; command < super.getChildren().size(); command++) {
            ret = super.getChildren().get(command).evaluate(myVisCommands, myTurtles);
        }
        return ret;
    }
    /*
     * Ask can have an unlimited number of children
     */
    @Override
    public void addChild(CommandNode c){
        super.addChild(c);
    }

}
