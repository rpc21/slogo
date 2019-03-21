/**
 * @Author Megan Phibbons
 * @Date March 2019
 * @Purpose Validate command inputs and differentiate between different types of inputs in order to aid the parser
 * @Dependencies exceptions
 * @Uses: Created in the Parser class and only used by Parser.
 * @Resources: Used Professor Duvall's starter code for the parser.
 */

package parser;

import exceptions.InvalidCommandException;
import exceptions.external.InvalidInputException;
import exceptions.InvalidVariableException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class Validator {
    private ResourceBundle myParameterProperties;
    private ResourceBundle myCommandProperties;
    private ResourceBundle mySyntaxProperties;
    private Map<String, Pattern> myCommandSyntax;
    private Map<String, Pattern> myNumberOfParameters;
    private Map<String, Pattern> myGeneralSyntax;
    private static final String PARAMETER_PROPERTIES_LOCATION = "parser/Parameters";
    private static final String DEFAULT_LANGUAGE = "English";
    private static final String COMMAND_PROPERTIES_LOCATION = "languages/";
    private static final String SYNTAX_PROPERTIES_LOCATION = "languages/Syntax";
    private static final String COMMENT_KEY = "Comment";
    private static final String VARIABLE_KEY = "Variable";
    private static final String LIST_START_KEY = "ListStart";
    private static final String LIST_END_KEY = "ListEnd";
    private static final String DOUBLE_KEY = "Constant";

    /**
     * @purpose validator constructor that initializes and instantiates all maps and accesses the properties files
     */
    public Validator() {
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

    /**
     * @purpose parse through a string and remove all commented out lines
     * @param command the input command that needs comments removed
     * @return a string without comments. If the command does not contain comments, this will be the same as command.
     */
    public String removeComments(String command) {
        String[] lines = command.split("\\n");
        for(int i = 0; i < lines.length; i++) {
            if(match(myGeneralSyntax, lines[i], COMMENT_KEY)) {
                lines[i] = " ";
            }
        }
        return String.join(" ", lines).trim();
    }

    /**
     * @purpose used across all languages and types of commands to return one "key" in the same format (the key of the properties files)
     * @param input a one-word command used to search through the properties file
     * @return the key corresponding to the input value
     * @throws InvalidInputException thrown when the value does not exist in the properties files
     */
    public String getCommandKey(String input) throws InvalidInputException {
        for(var key : myCommandSyntax.keySet()) {
            if(match(myCommandSyntax, input, key)) {
                return key;
            }
        }
        throw new InvalidCommandException(input);
    }

    /**
     * @purpose check if something is a double in order to filter out constants and commands
     * @param input the string that is being checked for being a constant
     * @return true if the input string is a constant
     */
    public boolean isDouble(String input) {
        return match(myGeneralSyntax, input, DOUBLE_KEY);
    }

    /**
     * @purpose check if an input variable name is of the correct form
     * @param variable the variable being validated
     * @throws InvalidInputException if the variable is not in the correct form
     */
    public void validateVariableName(String variable) throws InvalidInputException {
        if(!match(myGeneralSyntax, variable, VARIABLE_KEY)) {
            throw new InvalidVariableException(variable);
        }
    }

    /**
     * @purpose filter through the properties file and figure out the expected number of parameters for a given key
     * @param currentCommandKey the key corresponding to the given command
     * @return an integer representation of the expected number of parameters
     */
    public int getExpectedNumberOfParameters(String currentCommandKey) {
        return Integer.parseInt(myParameterProperties.getString(currentCommandKey).trim());
    }

    /**
     * @purpose update the language used by the validator/parser to correspond with the UI
     * @param newLanguage the language corresponding to the correct properties file
     */
    public void updateLanguage(String newLanguage) {
        myCommandProperties = ResourceBundle.getBundle(COMMAND_PROPERTIES_LOCATION + newLanguage);
        addPatterns(myCommandSyntax, myCommandProperties);
    }

    /**
     * @purpose check if the input string is the start of a list
     * @param child is the string to check against
     * @return true if child matches the syntax for the start of a list
     */
    public boolean isListStart(String child) {
        return match(myGeneralSyntax, child, LIST_START_KEY);
    }

    /**
     * @purpose check if the input string is the end of a list
     * @param child is the string to check against
     * @return true if child matches the syntax for the end of a list
     */
    public boolean isListEnd(String child) {
        return match(myGeneralSyntax, child, LIST_END_KEY);
    }

    /**
     * @purpose check if the list has an end - this is called after the start of a list is encountered
     * @param content a long string
     * @return true if content contains a list end
     */
    public boolean hasListEnd(String content) {
        return content.indexOf(mySyntaxProperties.getString(LIST_END_KEY)) != -1;
    }

    /**
     * @purpose check if the input value is a variable
     * @param child the string to check whether or not it's a variable
     * @return true if it's of the form of a variable
     */
    public boolean isVariable(String child) {
        return match(myGeneralSyntax, child,  VARIABLE_KEY);
    }

    private void addPatterns(Map<String, Pattern> patternMap, ResourceBundle bundle) {
        patternMap.clear();
        for (var key : Collections.list(bundle.getKeys())) {
            var regex = bundle.getString(key).trim();
            patternMap.put(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE));
        }
    }

    private boolean match(Map<String, Pattern> patterns, String text, String key) {
        Pattern p = patterns.get(key);
        if(p == null) {
            return true;
        }
        return p.matcher(text).matches();
    }
}
