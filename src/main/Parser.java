package main;

import GUI.GUIExecute;
import exceptions.InvalidCommandException;
import nodes.CommandFactory;
import nodes.CommandNode;

import java.util.*;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;
import java.util.regex.Pattern;

public class Parser {
    private CommandFactory myCommandFactory;
    private ResourceBundle myParameterProperties;
    private ResourceBundle myCommandProperties;
    private static final String PARAMETER_PROPERTIES_LOCATION = "parser/Parameters";
    private static final String DEFAULT_LANGUAGE = "English";
    private static final String COMMAND_PROPERTIES_LOCATION = "languages/";
    private String myCurrentCommand;
    private List<Entry<String, Pattern>> mySymbols;
    private Map<String,Double> myVariables;
    private List<Entry<String, Pattern>> mySyntax;

    AddVariable myAddVarFunction = new AddVariable() {
        @Override
        public void addVar(String s,Double d) {
            addVariable(s,d);
        }
    };
    public Parser() {
        myCommandFactory = new CommandFactory();
        myParameterProperties = ResourceBundle.getBundle(PARAMETER_PROPERTIES_LOCATION);
        myCommandProperties = ResourceBundle.getBundle(COMMAND_PROPERTIES_LOCATION + DEFAULT_LANGUAGE);
        mySymbols = new ArrayList<>();
        mySyntax = new ArrayList<>();
        addPatterns(mySymbols, myCommandProperties);
        addPatterns(mySyntax, myParameterProperties);
    }

    public List<CommandNode> parse(String input) throws InvalidCommandException { // todo: throw invalidcommandexception and invalidnumberinputs exception
        myCurrentCommand = input;
        myVariables = new HashMap<>();
        List<CommandNode> topLevelCommands = new ArrayList<>();
        while(myCurrentCommand.length() > 0) {
            topLevelCommands.add(makeNodeTree());
        }
        return topLevelCommands;
    }

    private void addVariable(String s, Double d){
        myVariables.put(s,d);
    }

    private CommandNode makeNodeTree() throws InvalidCommandException { // todo: check for invalid number of inputs?
        String[] commandSplit = myCurrentCommand.trim().split("\\s+");
        String currentValue = commandSplit[0];
        String currentCommandKey = getCommandKey(currentValue);
        int expectedNumberOfParameters = Integer.parseInt(myParameterProperties.getString(currentCommandKey));
        updateString();
        CommandNode currentNode = myCommandFactory.makeCommand(currentCommandKey);
        for(int i = 1; i <= expectedNumberOfParameters; i++) {
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
        updateString();
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

    private String getCommandKey(String input) throws InvalidCommandException {
        for(var symbol : mySymbols) {
            if(match(input, symbol.getValue())) {
                return symbol.getKey();
            }
        }
        throw new InvalidCommandException(input);
    }

    private boolean match (String text, Pattern regex) {
        return regex.matcher(text).matches();
    }

    private void addPatterns(List<Entry<String, Pattern>> patternList, ResourceBundle bundle) {
        patternList.clear();
        for (var key : Collections.list(bundle.getKeys())) {
            var regex = bundle.getString(key);
            patternList.add(new SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }

    private void updateString() {
        String[] split = myCurrentCommand.split(" ");
        myCurrentCommand = "";
        for(int i = 1; i < split.length; i++) {
            myCurrentCommand += split[i] + " ";
        }
        myCurrentCommand = myCurrentCommand.trim();
    }

    private void updateLanguage(String newLanguage) {
        myCommandProperties = ResourceBundle.getBundle(COMMAND_PROPERTIES_LOCATION + newLanguage);
        addPatterns(mySymbols, myCommandProperties);
    }

}
