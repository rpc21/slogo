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
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        String commandName = super.getChildren().get(0).toString();
        CommandNode variableNamesList = super.getChildren().get(1);
        List<String> varNames = new ArrayList<>();

        for (CommandNode c: variableNamesList.getChildren()) {
            varNames.add(c.toString());

        }
        String commands = super.getChildren().get(2).toString();
        myUserCreatedItems.addUserCommand(commandName, varNames, commands);

        myVisCommands.add(new VisualAddMethod(commandName,varNames));
        return 1.0;

    }

    @Override
    public boolean isMethodDeclaration() {
        return true;
    }

    @Override
    public boolean needsUserCreated(){ return true;}
}