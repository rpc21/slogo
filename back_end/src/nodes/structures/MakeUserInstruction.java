package nodes.structures;
import apis.ImmutableVisualCommand;
import nodes.CommandNode;
import nodes.visuals.VisualAddMethod;
import parser.UserCreated;
import turtle.Bale;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */

public class MakeUserInstruction extends CommandNode {
    private UserCreated myUserCreatedItems;
    public MakeUserInstruction(String commandName) {
        super(commandName);
    }
    public MakeUserInstruction(String a, UserCreated user) {
        super(a);
        myUserCreatedItems = user;
    }
    /**
     * This CommandNode has three children - the method name associated with this user instruction, the list of string
     * variable names associated with this user instruction, and the commands associated with this user instruction,
     * which will be stored as a string so it may be invoked later on
     * @return 1.0 if user command was properly made, 0.0 otherwise
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        String commandName = super.getChildren().get(0).toString();
        CommandNode variableNamesList = super.getChildren().get(1);

        List<String> varNames = new ArrayList<>();
        for (CommandNode c: variableNamesList.getChildren()) {
            varNames.add(c.toString());
        }
        String commands = super.getChildren().get(2).toString();
        try {
            myUserCreatedItems.addUserCommand(commandName, varNames, commands);
        }
        catch (NullPointerException e){
            return 0.0;
        }
        myVisCommands.add(new VisualAddMethod(commandName,varNames));
        return 1.0;

    }
    /**
     * This CommandNode is a method declaration and is unique in that it does not actually evaluate its children
     */
    @Override
    public boolean isMethodDeclaration() {
        return true;
    }
    /**
     * This CommandNode needs access to the user created class to store information about this user instruction's
     * variable arguments and commands
     */
    @Override
    public boolean needsUserCreated(){ return true;}
}