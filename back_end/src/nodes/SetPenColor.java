package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;

public class SetPenColor extends CommandNode {
    public SetPenColor(String n){
        super(n);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        int index = (int)super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        for (Integer id: myTurtles.getActiveTurtlesIDs()) {
            myTurtles.get(id).setPenColor(index);
            myVisCommands.add(new VisualPenColor(id,index));
        }
        return index;
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() ==1)
            throw new IllegalArgumentException();
        super.addChild(c);
    }

}
