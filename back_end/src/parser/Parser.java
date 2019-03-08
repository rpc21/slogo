package parser;

import exceptions.InvalidInputException;
import exceptions.InvalidListException;
import exceptions.TooFewInputsException;
import nodes.CommandNode;
import nodes.structures.UserInstruction;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private CommandFactory myCommandFactory;
    private String myCurrentCommand;
    private UserCreated myUserCreated;
    private Validator myValidator;

    private static final String LIST_NODE_NAME = "ListNode";
    private static final String VARIABLE_NODE_NAME = "Variable";

    public Parser(UserCreated userCreated) {
        myUserCreated = userCreated;
        myCommandFactory = new CommandFactory();
        myValidator = new Validator();
    }

    public List<CommandNode> parse(String input) throws InvalidInputException {
        myCurrentCommand = input;
        myCurrentCommand = myValidator.removeComments(input);
        List<CommandNode> topLevelCommands = new ArrayList<>();
        while(myCurrentCommand.length() > 0) {
            topLevelCommands.add(makeNodeTree());
        }
        return topLevelCommands;
    }

    private CommandNode makeNodeTree() throws InvalidInputException { // todo: check for invalid number of inputs?
        String[] commandSplit = myCurrentCommand.trim().split("\\s+");
        String currentValue = commandSplit[0];
        String currentCommandKey;
        CommandNode currentNode;
        int expectedNumberOfParameters;
        try {
            currentCommandKey = myValidator.getCommandKey(currentValue);
            currentNode = myCommandFactory.makeCommand(currentCommandKey, myUserCreated);
            expectedNumberOfParameters = myValidator.getExpectedNumberOfParameters(currentCommandKey);
        } catch (InvalidInputException e) {
            if(myUserCreated.containsCommand(currentValue)) {
                currentCommandKey = currentValue;
                currentNode = myCommandFactory.makeCommand("UserInstruction", myUserCreated);
                expectedNumberOfParameters = myUserCreated.getExpectedNumberOfParameters(currentCommandKey);
            } else {
                throw e;
            }
        }
        updateMyCurrentCommand();
        if(currentNode.needsName()) { // this means the current node is looking for a variable
            addVariableChild(currentNode, commandSplit[1]);
        }
        if(currentNode.isMethodDeclaration()) {
            addNameChild(currentNode,  commandSplit[1]);
        }
        for(int i = getStartIndex(currentNode); i <= expectedNumberOfParameters; i++) {
            commandSplit = myCurrentCommand.split("\\s+");
            if(commandSplit[0].length()  == 0) {
                throw new TooFewInputsException();
            }
            addChild(currentNode, commandSplit[0]);
        }
        return currentNode;
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
        addNameChild(currentNode, child);
    }

    private void addChild(CommandNode currentNode, String child) throws InvalidInputException {
        if (myValidator.isDouble(child)) {
            currentNode.addChild(myCommandFactory.makeCommand(Double.parseDouble(child)));
        } else if (myValidator.isListStart(child)) {
            currentNode.addChild(makeListTree());
        } else if (myValidator.isVariable(child)) {
            currentNode.addChild(myCommandFactory.makeCommand(VARIABLE_NODE_NAME, myUserCreated));
        } else {
            currentNode.addChild(makeNodeTree());
            return;
        }
        updateMyCurrentCommand();
    }

    private CommandNode makeListTree() throws InvalidInputException {
        if(myValidator.hasListEnd(myCurrentCommand)) {
            throw new InvalidListException();
        }
        CommandNode parent = myCommandFactory.makeCommand(LIST_NODE_NAME, myUserCreated);
        updateMyCurrentCommand();
        String[] splitCommand = myCurrentCommand.trim().split("\\s+");
        String child = splitCommand[0];
        while(!myValidator.isListEnd(child)) {
            addChild(parent, child);
            splitCommand = myCurrentCommand.trim().split("\\s+");
            child = splitCommand[0];
        }
        return parent;
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
        myValidator.updateLanguage(newLanguage);
    }

}
