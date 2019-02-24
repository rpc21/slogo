import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class Parser {
    private CommandFactory myCommandFactory;
    private ResourceBundle parameterProperties;
    private ResourceBundle commandProperties;
    private static final String PARAMETER_PROPERTIES_LOCATION = "resources/languages/English.properties";
    private static final String COMMAND_PROPERTIES_LOCATION = "resources/parser/Parameters.properties";

    public Parser() {
        myCommandFactory = new CommandFactory();
    }

    public List<CommandNode> parse(String input) { // todo: throw invalidcommandexception and invalidnumberinputs exception
        List<CommandNode> topLevelCommands = new ArrayList<>();
        parameterProperties = ResourceBundle.getBundle(PARAMETER_PROPERTIES_LOCATION);
        commandProperties = ResourceBundle.getBundle(COMMAND_PROPERTIES_LOCATION);


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
        String[] commandInputs = command.split(" ");
        if(commandInputs.length < 1) {
            // todo: is this an actual problem? and if so, what should it throw?
        }
        String commandName = commandInputs[0]; // todo: check for comment/variable
        String formalName = "";
        for(String key : commandProperties.keySet()) {
            if(commandProperties.getString(key).contains(commandName)) { // todo: use the regex here
                formalName = key;
            }
        }
        if(formalName.equals("")) {
            //todo: throw invalid command exception
        }
        int expectedParameters = Integer.parseInt(parameterProperties.getString(formalName));
        List<CommandNode> childrenNodes = new ArrayList<>();
        for(int i = 0; i < expectedParameters; i++) {
            if(i > commandInputs.length) {
                // throw exception
            }
            childrenNodes.add(myCommandFactory.makeCommand(command)); //todo: make this actually work
        }
        CommandNode currentCommandHead = null; // todo ????????
        return currentCommandHead;
    }


}
