package nodes.actions;
import apis.ImmutableVisualCommand;
import exceptions.external.InvalidInputException;
import nodes.CommandNode;
import nodes.visuals.VisualPalette;
import turtle.Bale;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class SetPalette extends CommandNode {
    private static final int INDEX = 0;
    private static final int RED_VAL = 1;
    private static final int GREEN_VAL = 2;
    private static final int BLUE_VAL = 3;
    private static final int MAX_VAL = 256;
    public SetPalette(String n){
        super(n);
    }
    /**
     * Evaluates children nodes to get the new r,g, and b values to be associated with a particular palette index
     * and instantiates a VisualPalette Command to inform visualization to display this change
     * @return index of color that was changes
     * @see VisualPalette
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        int currIndex = (int)super.getChildren().get(INDEX).evaluate(myVisCommands, myTurtles);
        int red = (int)super.getChildren().get(RED_VAL).evaluate(myVisCommands, myTurtles);
        int green = (int)super.getChildren().get(BLUE_VAL).evaluate(myVisCommands, myTurtles);
        int blue = (int)super.getChildren().get(GREEN_VAL).evaluate(myVisCommands, myTurtles);
        if (!(checkRGBValue(red) && checkRGBValue(green) && checkRGBValue(blue))) {
            throw new IllegalArgumentException();
        }
        myVisCommands.add(new VisualPalette(currIndex,red,green,blue));
        return currIndex;
    }
    private boolean checkRGBValue(double val){
        return !(val < 0) && !(val > MAX_VAL);
    }

}
