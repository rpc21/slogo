package nodes.variables;
import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import nodes.visuals.VisualAddVariable;
import parser.UserCreated;
import turtle.Bale;

import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class MakeVariable extends CommandNode {
    private UserCreated myUserCreatedItems;
    private String myVarName;
    public MakeVariable(String a){
        super(a);
        myVarName = a;
    }
    public MakeVariable(String a, UserCreated user) {
        super(a);
        myVarName = a;
        myUserCreatedItems = user;
    }
    /**
     * This make variable node initializes a variable in UserCreated so other commands may access it to evaluate their
     * arguments
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        myVarName = super.getChildren().get(0).toString();
        double varValue = super.getChildren().get(1).evaluate(myVisCommands, myTurtles);
        myUserCreatedItems.addVariable(myVarName,varValue);
        myVisCommands.add(new VisualAddVariable(myVarName,varValue));
        return varValue;
    }
    /**
     * This node requires a name child to know the variable name associated with its value
     */
    @Override
    public boolean needsName(){
        return true;
    }
    /**
     * This node requires access to the UserCreated class in order to initialize the variable properly
     */
    @Override
    public boolean needsUserCreated(){ return true;}

}
