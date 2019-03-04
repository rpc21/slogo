package parser;

import exceptions.InvalidCommandException;
import exceptions.InvalidVariableException;
import nodes.CommandNode;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
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
    private Map<String, Pattern> myCommandSyntax;
    private Map<String, Pattern> myNumberOfParameters;
    private Map<String, Pattern> myGeneralSyntax;
    private static final String COMMENT_KEY = "Comment";
    private static final String VARIABLE_KEY = "Variable";
    private UserCreated myUserCreated;

    public Parser(UserCreated userCreated) {
        myUserCreated = userCreated;
        myCommandFactory = new CommandFactory();
        myParameterProperties = ResourceBundle.getBundle(PARAMETER_PROPERTIES_LOCATION);
        myCommandProperties = ResourceBundle.getBundle(COMMAND_PROPERTIES_LOCATION + DEFAULT_LANGUAGE);
        mySyntaxProperties = ResourceBundle.getBundle(SYNTAX_PROPERTIES_LOCATION);
        myCommandSyntax = new HashMap<>();
        myNumberOfParameters = new HashMap<>();
        myGeneralSyntax = new HashMap<>();
        addPatterns(myCommandSyntax, myCommandProperties);
        addPatterns(myNumberOfParameters, myParameterProperties);
        addPatterns(myGeneralSyntax, mySyntaxProperties);
    }

    public List<CommandNode> parse(String input) throws InvalidCommandException, InvalidVariableException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException { // todo: throw invalidcommandexception and invalidnumberinputs exception
        myCurrentCommand = input;
        removeComments();
        List<CommandNode> topLevelCommands = new ArrayList<>();
        while(myCurrentCommand.length() > 0) {
            System.out.println("TOP LEVEL COMMAND");
            topLevelCommands.add(makeNodeTree());
            System.out.println("Size: " + topLevelCommands.size());
        }
        return topLevelCommands;
    }

    private CommandNode makeNodeTree() throws InvalidCommandException, InvalidVariableException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException { // todo: check for invalid number of inputs?
        String[] commandSplit = myCurrentCommand.trim().split("\\s+");
        String currentValue = commandSplit[0];
        String currentCommandKey = getCommandKey(currentValue);
        int expectedNumberOfParameters = Integer.parseInt(myParameterProperties.getString(currentCommandKey));
        updateMyCurrentCommand();
        CommandNode currentNode = myCommandFactory.makeCommand(currentCommandKey);
        System.out.println(currentCommandKey);
        for(int i = 1; i <= expectedNumberOfParameters; i++) {
            addChild(currentNode, commandSplit[i]);
        }
        return currentNode;
    }

    private void validateVariableName(String variable) throws InvalidVariableException {
        if(!isSpecificFormat(variable, VARIABLE_KEY)) {
            System.out.println(VARIABLE_KEY);
            System.out.println(variable);
            throw new InvalidVariableException(variable);
        }
    }

    private void addChild(CommandNode currentNode, String child) throws InvalidCommandException, InvalidVariableException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        if(currentNode.needsName()) {
            validateVariableName(child);
            currentNode.addChild(myCommandFactory.makeCommand(child, myUserCreated.getMyAddVarFunction()));
        } else if(isDouble(child)) {
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
        for(var key : myCommandSyntax.keySet()) {
            if(match(input, myCommandSyntax.get(key))) {
                return key;
            }
        }
        throw new InvalidCommandException(input);
    }

    private boolean match (String text, Pattern regex) {
        return regex.matcher(text).matches();
    }

    private void addPatterns(Map<String, Pattern> patternMap, ResourceBundle bundle) {
        patternMap.clear();
        for (var key : Collections.list(bundle.getKeys())) {
            var regex = bundle.getString(key).trim();
            patternMap.put(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE));
        }
    }

    private boolean isSpecificFormat(String text, String key) {
        return match(text, myGeneralSyntax.get(key));
    }

    private void removeComments() {
        String[] lines = myCurrentCommand.split("\\n");
        for(int i = 0; i < lines.length; i++) {
            if(isSpecificFormat(lines[i], COMMENT_KEY)) {
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
