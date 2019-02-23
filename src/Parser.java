
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private CommandFactory myCommandFactory;

    public Parser() {
        myCommandFactory = new CommandFactory();
    }

    public List<CommandNode> parse(String input) { // todo: throw invalidcommandexception and invalidnumberinputs exception
        List<CommandNode> topLevelCommands = new ArrayList<>();

        CommandNode root = myCommandFactory.makeCommand(input.split(" ")[0]);
        CommandNode subRoot = myCommandFactory.makeCommand(input.split(" ")[1]);
        root.addChild(subRoot);
        subRoot.addChild(myCommandFactory.makeCommand(input.split(" ")[2]));
        subRoot.addChild(myCommandFactory.makeCommand(input.split(" ")[3]));
        root.addChild(myCommandFactory.makeCommand(input.split(" ")[4]));
        topLevelCommands.add(root);
        return topLevelCommands;
    }

    // purpose: take in all of the commands and divide them up by their top level commands
    private String[] separateTopLevelCommands(String input) {
        // todo: go through and look at command and arguments, when each command in a hierarchy has enough arguments, this is one top level command. Then move on
        return null;
    }

    // purpose: make the hierarchy of an individual top level command
    private CommandNode makeTopLevelCommandNode(String command) {
        // todo: based on a string input command, here is where the actual hierarchy of making the top level commands and their children nodes go
        return null;
    }


}
