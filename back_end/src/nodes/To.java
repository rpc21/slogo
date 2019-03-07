package nodes;

import apis.ImmutableVisualCommand;
import parser.UserCreated;
import turtle.Bale;

import java.util.List;


public class To extends CommandNode {
    private UserCreated myUserCreatedItems;
    public To(String commandName) {
        super(commandName);
    }
    public To(String a, UserCreated user) {
        super(a);
        myUserCreatedItems = user;
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        return 1.0;
    }
    /**
     * Adds an addend to this nodes.SumNode's list of Children as main.Parser reads them in
     * @TODO Read in possible Argument issues from a resources file to ensure parameter specifications are satisfied
     */
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 1) {
            throw new IllegalArgumentException();
        }
        super.addChild(c);
    }
    @Override
    public boolean needsName(){
        return true;
    }

    @Override
    public boolean needsUserCreated(){ return true;}
}