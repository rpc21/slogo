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
        for(String topLevelCommand : separateTopLevelCommands(input)) {
            topLevelCommands.add(makeTopLevelCommand(topLevelCommand));
        }
        return topLevelCommands;
    }

    private CommandNode makeTopLevelCommand(String command) {
        String commandName = command.split("\\s+")[0];
        int numberOfParameters = Integer.parseInt(parameterProperties.getString(commandName));
        return makeNode(commandName, numberOfParameters);
    }

    // purpose: take in all of the commands and divide them up by their top level commands
    private String[] separateTopLevelCommands(String input) {
        // todo: go through and look at command and arguments, when each command in a hierarchy has enough arguments, this is one top level command. Then move on
        return null;
    }

    private CommandNode makeNode(String command, int expectedNumberOfParameters) {
        if(command == "") {
            return null; // end of recursion
        }
        String[] commandSplit = command.split("\\s+");
        if(commandSplit.length < expectedNumberOfParameters + 1) {
            // todo: throw an exception
        }
        CommandNode currentNode = myCommandFactory.makeCommand(commandSplit[0]);
        command = command.substring(commandSplit[0].length());
        for(int i = 1; i < expectedNumberOfParameters; i++) {
            if(isDouble(commandSplit[i])) {
                currentNode.addChild(myCommandFactory.makeCommand(commandSplit[i]));
            } else {
                currentNode.addChild(makeNode(command, Integer.parseInt(parameterProperties.getString(commandSplit[i]))));
            }
            command = command.substring(commandSplit[i].length()); // todo: does this work? do indices line up?
        }
        if(command.length() != 0) {
            // todo: throw an exception for too many args
        }
        return currentNode;
    }

    // purpose: check if something successfully can be parsed as a double
    private boolean isDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

}
