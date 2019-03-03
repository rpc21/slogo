package parser;

import apis.AddVariable;
import exceptions.InvalidCommandException;
import exceptions.InvalidVariableException;
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
    private ResourceBundle mySyntaxProperties;
    private static final String PARAMETER_PROPERTIES_LOCATION = "parser/Parameters";
    private static final String DEFAULT_LANGUAGE = "English";
    private static final String COMMAND_PROPERTIES_LOCATION = "languages/";
    private static final String SYNTAX_PROPERTIES_LOCATION = "languages/Syntax";
    private String myCurrentCommand;
    private List<Entry<String, Pattern>> myCommandSyntax;
    private Map<String,Double> myVariables;
    private List<Entry<String, Pattern>> myNumberOfParameters;
    private List<Entry<String, Pattern>> myGeneralSyntax;
    private static final int COMMENT_INDEX = 0;
    private static final int VARIABLE_INDEX = 2;
    private UserCreated myUserCreated;


    public Parser(UserCreated userCreated) {
        myUserCreated = userCreated;
        myCommandFactory = new CommandFactory();
        myParameterProperties = ResourceBundle.getBundle(PARAMETER_PROPERTIES_LOCATION);
        myCommandProperties = ResourceBundle.getBundle(COMMAND_PROPERTIES_LOCATION + DEFAULT_LANGUAGE);
        mySyntaxProperties = ResourceBundle.getBundle(SYNTAX_PROPERTIES_LOCATION);
        myCommandSyntax = new ArrayList<>();
        myNumberOfParameters = new ArrayList<>();
        myGeneralSyntax = new ArrayList<>();
        addPatterns(myCommandSyntax, myCommandProperties);
        addPatterns(myNumberOfParameters, myParameterProperties);
        addPatterns(myGeneralSyntax, mySyntaxProperties);
    }

    public List<CommandNode> parse(String input) throws InvalidCommandException, InvalidVariableException { // todo: throw invalidcommandexception and invalidnumberinputs exception
        myCurrentCommand = input;
        removeComments();
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

    private CommandNode makeNodeTree() throws InvalidCommandException, InvalidVariableException { // todo: check for invalid number of inputs?
        String[] commandSplit = myCurrentCommand.trim().split("\\s+");
        String currentValue = commandSplit[0];
        String currentCommandKey = getCommandKey(currentValue);
        int expectedNumberOfParameters = Integer.parseInt(myParameterProperties.getString(currentCommandKey));
        updateMyCurrentCommand();
        CommandNode currentNode = myCommandFactory.makeCommand(currentCommandKey);
        for(int i = 1; i <= expectedNumberOfParameters; i++) {
            addChild(currentNode, commandSplit[i]);
        }
        return currentNode;
    }

    private void validateVariableName(String variable) throws InvalidVariableException {
        if(!isSpecificFormat(variable, VARIABLE_INDEX)) {
            throw new InvalidVariableException(variable);
        }
    }

    private void addChild(CommandNode currentNode, String child) throws InvalidCommandException, InvalidVariableException {
        if(currentNode.needsName()) {
            validateVariableName(child);
        }
        if(isDouble(child)) {
            currentNode.addChild(myCommandFactory.makeCommand(Double.parseDouble(child)));
        } else {
            currentNode.addChild(makeNodeTree());
        }
        updateMyCurrentCommand();
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
        for(var symbol : myCommandSyntax) {
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
            var regex = bundle.getString(key).trim();
            patternList.add(new SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }

    private boolean isSpecificFormat(String text, int syntaxIndex) {
        return match(text, myGeneralSyntax.get(syntaxIndex).getValue());
    }

    private void removeComments() {
        String[] lines = myCurrentCommand.split("\\n");
        for(int i = 0; i < lines.length; i++) {
            System.out.println(lines[i]);
            if(isSpecificFormat(lines[i], COMMENT_INDEX)) {
                lines[i] = " ";
            }
        }
        myCurrentCommand = String.join(" ", lines).trim();
    }

    private void updateMyCurrentCommand() {
        String[] split = myCurrentCommand.split(" ");
        myCurrentCommand = "";
        for(int i = 1; i < split.length; i++) {
            myCurrentCommand += split[i] + " ";
        }
        myCurrentCommand = myCurrentCommand.trim();
    }

    public void updateLanguage(String newLanguage) {
        myCommandProperties = ResourceBundle.getBundle(COMMAND_PROPERTIES_LOCATION + newLanguage);
        addPatterns(myCommandSyntax, myCommandProperties);
    }

}
