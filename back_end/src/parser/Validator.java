package parser;

import exceptions.InvalidCommandException;
import exceptions.InvalidInputException;
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

    public String removeComments(String command) {
        String[] lines = command.split("\\n");
        for(int i = 0; i < lines.length; i++) {
            if(match(myGeneralSyntax, lines[i], COMMENT_KEY)) {
                lines[i] = " ";
            }
        }
        return String.join(" ", lines).trim();
    }

    public String getCommandKey(String input) throws InvalidInputException {
        for(var key : myCommandSyntax.keySet()) {
            if(match(myCommandSyntax, input, key)) {
                return key;
            }
        }
        throw new InvalidCommandException(input);
    }

    // purpose: check if something successfully can be parsed as a double
    public boolean isDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    public void validateVariableName(String variable) throws InvalidInputException {
        if(!match(myGeneralSyntax, variable, VARIABLE_KEY)) {
            throw new InvalidVariableException(variable);
        }
    }

    public int getExpectedNumberOfParameters(String currentCommandKey) {
        return Integer.parseInt(myParameterProperties.getString(currentCommandKey).trim());
    }

    public void updateLanguage(String newLanguage) {
        myCommandProperties = ResourceBundle.getBundle(COMMAND_PROPERTIES_LOCATION + newLanguage);
        addPatterns(myCommandSyntax, myCommandProperties);
    }

    public boolean isListStart(String child) {
        return match(myGeneralSyntax, child, LIST_START_KEY);
    }

    public boolean isListEnd(String child) {
        return match(myGeneralSyntax, child, LIST_END_KEY);
    }

    public boolean hasListEnd(String content) {
        return content.indexOf(mySyntaxProperties.getString(LIST_END_KEY)) != -1;
    }

    public boolean isVariable(String child) {
        return match(myGeneralSyntax, child,  VARIABLE_KEY);
    }
}
