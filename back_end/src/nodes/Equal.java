package nodes;
import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;


public class Equal extends BooleanNode{

    public Equal(String a) {
        super(a);
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        if (super.getFirstExpression(myVisCommands, myTurtles) ==
                super.getSecondExpression(myVisCommands, myTurtles)) {
            return ONE;
        }
        return ZERO;
    }


    @Override
    public void addChild(CommandNode c) {
        if (super.getChildren().size() == 2) {
            throw new IllegalArgumentException();
        }
        super.addChild(c);
    }
}
