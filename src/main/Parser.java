package main;

import exceptions.InvalidCommandException;
import nodes.CommandFactory;
import nodes.CommandNode;

import java.util.*;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;
import java.util.regex.Pattern;

public class Parser {
    private CommandFactory myCommandFactory;
    private ResourceBundle parameterProperties;
    private static final String PARAMETER_PROPERTIES_LOCATION = "parser/Parameters";
    private String myCurrentCommand;
    private List<Entry<String, Pattern>> mySymbols;

    public Parser() {
        myCommandFactory = new CommandFactory();
    }

    public List<CommandNode> parse(String input) throws InvalidCommandException { // todo: throw invalidcommandexception and invalidnumberinputs exception
        myCurrentCommand = input;
        List<CommandNode> topLevelCommands = new ArrayList<>();
        parameterProperties = ResourceBundle.getBundle(PARAMETER_PROPERTIES_LOCATION);
        addPatterns();
        while(myCurrentCommand.length() > 0) {
            topLevelCommands.add(makeNodeTree());
        }
        return topLevelCommands;
    }

    private CommandNode makeNodeTree() throws InvalidCommandException { // todo: check for invalid number of inputs and invalid commands
        String[] commandSplit = myCurrentCommand.split("\\s+");
        String currentCommandKey = getCommandKey(commandSplit[0]);
        int expectedNumberOfParameters = Integer.parseInt(parameterProperties.getString(currentCommandKey));
        myCurrentCommand = myCurrentCommand.substring(currentCommandKey.length() + 1);
        CommandNode currentNode = myCommandFactory.makeCommand(commandSplit[0]);
        for(int i = 1; i < expectedNumberOfParameters; i++) {
            addChild(currentNode, commandSplit[i]);
        }
        return currentNode;
    }

    private void addChild(CommandNode currentNode, String child) throws InvalidCommandException {
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

    private String getCommandKey(String input) {//throws InvalidCommandException {
        for(var symbol : mySymbols) {
            if(match(input, symbol.getValue())) {
                return symbol.getKey();
            }
        }
        return "";
        //throw new InvalidCommandException(); // todo: personalize this
    }

    private boolean match (String text, Pattern regex) {
        // THIS IS THE IMPORTANT LINE
        return regex.matcher(text).matches();
    }

    private void addPatterns () {
        mySymbols = new ArrayList<>();
        for (var key : Collections.list(parameterProperties.getKeys())) {
            var regex = parameterProperties.getString(key);
            mySymbols.add(new SimpleEntry<>(key,
                    Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }


}
