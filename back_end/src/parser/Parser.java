/**
 * @Author Megan Phibbons
 * @Date March 2019
 * @Purpose parse the user's input and create CommandNodes for the controller to execute. Additionally, check the user's
 *          input and make sure it is all valid so that the code does not crash.
 * @Dependencies exceptions and nodes
 * @Uses: Used in the CommandController class, which takes in a string to parse and sends it over to the parser. This
 *        is the only instance of the parser.
 */

package parser;

import exceptions.InvalidInputException;
import exceptions.InvalidListException;
import exceptions.TooFewInputsException;
import nodes.CommandNode;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private CommandFactory myCommandFactory;
    private String myCurrentCommand;
    private UserCreated myUserCreated;
    private Validator myValidator;

    private static final String LIST_NODE_NAME = "ListNode";
    private static final String USER_INSTRUCTION_KEY = "UserInstruction";

    private static final String LIST_START = "[";
    private static final String LIST_END = "]";

    /**
     * @purpose the constructor of the Parser. Creates a command factory and a validator for use throughout the parser.
     * @param userCreated an instance of UserCreated that the command controller passes into the parser so that all
     *                    classes that need access to UserCreated are accessing the same instance.
     */
    public Parser(UserCreated userCreated) {
        myUserCreated = userCreated;
        myCommandFactory = new CommandFactory();
        myValidator = new Validator();
    }

    /**
     * @purpose parse the string input, validate it, and create the hierarchy of commands
     * @param input the string that the user inputs, complete with comments and multiple lines of code.
     * @return a list of CommandNode trees that are ready to be executed.
     * @throws InvalidInputException when the user tries to use a variable or command that doesn't exist or does not
     *                               have the right amount of parameters.
     */
    public List<CommandNode> parse(String input) throws InvalidInputException {
        myCurrentCommand = input;
        myCurrentCommand = myValidator.removeComments(input);
        List<CommandNode> topLevelCommands = new ArrayList<>();
        while(myCurrentCommand.length() > 0) {
            topLevelCommands.add(makeNodeTree());
        }
        return topLevelCommands;
    }

    /**
     * @purpose when the user tries to update the language in the front end, the parser needs to update its language
     *          as well so that it can properly process commands.
     * @param newLanguage the new language in proper format corresponding to the name of its properties file.
     */
    public void updateLanguage(String newLanguage) {
        myValidator.updateLanguage(newLanguage);
    }

    private CommandNode makeNodeTree() throws InvalidInputException {
        String[] commandSplit = splitCommand(myCurrentCommand);
        String currentValue = commandSplit[0];
        if(myUserCreated.containsCommand(currentValue)) {
            return makeUserCreatedNode(currentValue);
        }
        String currentCommandKey = myValidator.getCommandKey(currentValue);
        CommandNode currentNode = myCommandFactory.makeCommand(currentCommandKey, myUserCreated);
        int expectedNumberOfParameters = myValidator.getExpectedNumberOfParameters(currentCommandKey);
        updateMyCurrentCommand();
        if(currentNode.needsName()) { // this means the current node is looking for a variable
            addNameChild(currentNode, commandSplit[1]);
        }
        if(currentNode.isMethodDeclaration()) { // special case where we want the children to be a bit different
            return makeMethodDeclaration(currentNode, commandSplit[1]);
        }
        addChildren(currentNode, expectedNumberOfParameters);
        return currentNode;
    }

    private void addChildren(CommandNode currentNode,  int expectedNumberOfParameters) throws InvalidInputException {
        for(int i = getStartIndex(currentNode); i <= expectedNumberOfParameters; i++) {
            String[] commandSplit = splitCommand(myCurrentCommand);
            if(commandSplit[0].length()  == 0) {
                throw new TooFewInputsException();
            }
            addChild(currentNode, commandSplit[0]);
        }
    }

    private CommandNode makeMethodDeclaration(CommandNode currentNode, String commandName) throws InvalidInputException {
        addNameChild(currentNode, commandName);
        currentNode.addChild(makeNameListTree());
        updateMyCurrentCommand();
        currentNode.addChild(myCommandFactory.makeNameNode(myCurrentCommand.substring(myCurrentCommand.indexOf(LIST_START) + 1, myCurrentCommand.indexOf(LIST_END))));
        myCurrentCommand = "";
        return currentNode;
    }

    private CommandNode makeUserCreatedNode(String currentValue) throws InvalidInputException {
        CommandNode currentNode = myCommandFactory.makeCommand(USER_INSTRUCTION_KEY, myUserCreated);
        addNameChild(currentNode, currentValue);
        currentNode.addChild(makeListTree());
        currentNode.addChild(makeListNode(parse(myUserCreated.getCommand(currentValue))));
        return currentNode;
    }

    private String[] splitCommand(String s) {
        return s.trim().split("\\s+");
    }

    private CommandNode makeNameListTree() throws InvalidInputException {
        CommandNode parent = makeListHead();
        String[] splitCommand = splitCommand(myCurrentCommand);
        String child = splitCommand[0];
        while(!myValidator.isListEnd(child)) {
            addNameChild(parent, child);
            splitCommand = splitCommand(myCurrentCommand);
            child = splitCommand[0];
        }
        return parent;
    }

    private CommandNode makeListNode(List<CommandNode> commands) throws InvalidInputException {
        CommandNode head = myCommandFactory.makeCommand(LIST_NODE_NAME, myUserCreated);
        for(CommandNode command : commands) {
            head.addChild(command);
        }
        return head;
    }

    private void addNameChild(CommandNode currentNode, String s) {
        currentNode.addChild(myCommandFactory.makeNameNode(s));
        updateMyCurrentCommand();
    }

    private int getStartIndex(CommandNode currentNode) {
        if(currentNode.needsName() || currentNode.isMethodDeclaration()) {
            return 2;
        } else {
            return 1;
        }
    }

    private void addVariableChild(CommandNode currentNode, String child) throws InvalidInputException {
        myValidator.validateVariableName(child);
        currentNode.addChild(myCommandFactory.makeVariableNode(child, myUserCreated));
    }

    private void addChild(CommandNode currentNode, String child) throws InvalidInputException {
        if (myValidator.isDouble(child)) {
            currentNode.addChild(myCommandFactory.makeCommand(Double.parseDouble(child)));
        } else if (myValidator.isListStart(child)) {
            currentNode.addChild(makeListTree());
        } else if (myValidator.isVariable(child)) {
            addVariableChild(currentNode, child);
        } else {
            currentNode.addChild(makeNodeTree());
            return;
        }
        updateMyCurrentCommand();
    }

    private CommandNode makeListTree() throws InvalidInputException {
        CommandNode parent = makeListHead();
        String[] splitCommand = splitCommand(myCurrentCommand);
        String child = splitCommand[0];
        while(!myValidator.isListEnd(child)) {
            addChild(parent, child);
            splitCommand = splitCommand(myCurrentCommand);
            child = splitCommand[0];
        }
        return parent;
    }

    private CommandNode makeListHead() throws InvalidInputException {
        if (myValidator.hasListEnd(myCurrentCommand)) {
            throw new InvalidListException();
        }
        CommandNode parent = myCommandFactory.makeCommand(LIST_NODE_NAME, myUserCreated);
        updateMyCurrentCommand();
        return parent;
    }

    private void updateMyCurrentCommand() {
        String[] split = splitCommand(myCurrentCommand);
        myCurrentCommand = "";
        for(int i = 1; i < split.length; i++) {
            myCurrentCommand += split[i] + " ";
        }
        myCurrentCommand = myCurrentCommand.trim();

    }

}
