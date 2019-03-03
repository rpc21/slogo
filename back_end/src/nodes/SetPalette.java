package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;

public class SetPalette extends CommandNode {
    private static final int INDEX = 0;
    private static final int RED_VAL = 1;
    private static final int GREEN_VAL = 2;
    private static final int BLUE_VAL = 3;
    private static final int MAX_VAL = 256;
    public SetPalette(String n){
        super(n);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        double currIndex = super.getChildren().get(INDEX).evaluate(myVisCommands, myTurtles);
        double red = super.getChildren().get(RED_VAL).evaluate(myVisCommands, myTurtles);
        double green = super.getChildren().get(BLUE_VAL).evaluate(myVisCommands, myTurtles);
        double blue = super.getChildren().get(GREEN_VAL).evaluate(myVisCommands, myTurtles);
        if (checkRGBValue(red) & checkRGBValue(red) & checkRGBValue(red)) {

        }
        else {
            throw new IllegalArgumentException();
        }
        return currIndex;
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() ==4)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
    private boolean checkRGBValue(double val){
        return !(val < 0) & !(val > MAX_VAL);
    }

}
