import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Parser {
    private CommandFactory myCommandFactory;
    private ResourceBundle parameterProperties;
    private ResourceBundle commandProperties;
    private static final String PARAMETER_PROPERTIES_LOCATION = "resources/languages/English.properties";
    private static final String COMMAND_PROPERTIES_LOCATION = "resources/parser/Parameters.properties";

    private String myCurrentCommand;

    public Parser() {
        myCommandFactory = new CommandFactory();
    }

    public List<CommandNode> parse(String input) { // todo: throw invalidcommandexception and invalidnumberinputs exception
        myCurrentCommand = input;
        List<CommandNode> topLevelCommands = new ArrayList<>();
        parameterProperties = ResourceBundle.getBundle(PARAMETER_PROPERTIES_LOCATION);
        commandProperties = ResourceBundle.getBundle(COMMAND_PROPERTIES_LOCATION);
        while(myCurrentCommand.length() > 0) {
            topLevelCommands.add(makeNodeTree());
        }
        return topLevelCommands;
    }

    private CommandNode makeNodeTree() { // todo: make this better
        String[] commandSplit = myCurrentCommand.split("\\s+");
        String currentCommandString = commandSplit[0];
        int expectedNumberOfParameters = Integer.parseInt(parameterProperties.getString(currentCommandString));
        myCurrentCommand = myCurrentCommand.substring(currentCommandString.length() + 1);
        CommandNode currentNode = myCommandFactory.makeCommand(commandSplit[0]);
        for(int i = 1; i < expectedNumberOfParameters; i++) {
            addChild(currentNode, commandSplit[i]);
        }
        return currentNode;
    }

    private void addChild(CommandNode currentNode, String child) {
        if(isDouble(child)) {
            currentNode.addChild(myCommandFactory.makeCommand(child));
        } else {
            currentNode.addChild(makeNodeTree());
        }
        myCurrentCommand = myCurrentCommand.substring(child.length() + 1);
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
