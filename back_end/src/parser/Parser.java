package parser;

import exceptions.InvalidCommandException;
import exceptions.InvalidListException;
import exceptions.InvalidVariableException;
import nodes.CommandNode;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Parser {
    private CommandFactory myCommandFactory;
    private String myCurrentCommand;
    private UserCreated myUserCreated;
    private Validator myValidator;

    public Parser(UserCreated userCreated) {
        myUserCreated = userCreated;
        myCommandFactory = new CommandFactory();
        myValidator = new Validator();
    }

    public List<CommandNode> parse(String input) throws NoSuchMethodException, InvocationTargetException, InvalidCommandException, InstantiationException, IllegalAccessException, InvalidVariableException, ClassNotFoundException, InvalidListException { // todo: throw invalidcommandexception and invalidnumberinputs exception
        myCurrentCommand = input;
        myCurrentCommand = myValidator.removeComments(input);
        List<CommandNode> topLevelCommands = new ArrayList<>();
        while(myCurrentCommand.length() > 0) {
            topLevelCommands.add(makeNodeTree());
        }
        return topLevelCommands;
    }

    private CommandNode makeNodeTree() throws InvalidCommandException, InvalidVariableException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvalidListException { // todo: check for invalid number of inputs?
        String[] commandSplit = myCurrentCommand.trim().split("\\s+");
        String currentValue = commandSplit[0];
        String currentCommandKey = myValidator.getCommandKey(currentValue);
        // todo: refactor this
            int start = 1;
            int expectedNumberOfParameters = myValidator.getExpectedNumberOfParameters(currentCommandKey);
            CommandNode currentNode = myCommandFactory.makeCommand(currentCommandKey);
            updateMyCurrentCommand();
            if(currentNode.needsName()) {
                currentNode = myCommandFactory.makeCommand(currentCommandKey, myUserCreated.getMyAddVarFunction());
                myValidator.validateVariableName(commandSplit[1]);
                currentNode.addChild(myCommandFactory.makeNameNode(commandSplit[1]));
                updateMyCurrentCommand();
                start = 2;
            }
        // todo end of refactor
        for(int i = start; i <= expectedNumberOfParameters; i++) {
            addChild(currentNode, commandSplit[i]);
        }
        return currentNode;
    }

    private void addChild(CommandNode currentNode, String child) throws InvalidCommandException, InvalidVariableException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, InvalidListException {
        System.out.println("Child: " +  child);
        if(myValidator.isDouble(child)) {
            currentNode.addChild(myCommandFactory.makeCommand(Double.parseDouble(child)));
        } else if(myValidator.isListStart(child)) {
            currentNode.addChild(makeListTree(child));
            System.out.println("ADDED CHILDREN");
        } else {
            currentNode.addChild(makeNodeTree());
        }
        updateMyCurrentCommand();
    }

    private CommandNode makeListTree(String child) throws InvalidListException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvalidVariableException, InvalidCommandException {
        if(myValidator.hasListEnd(myCurrentCommand)) {
            throw new InvalidListException();
        }
        CommandNode parent = myCommandFactory.makeCommand("ListNode");
        updateMyCurrentCommand();
        String[] splitCommand = myCurrentCommand.split("\\s");
        child = splitCommand[0];
        while(!myValidator.isListEnd(child)) {
            System.out.println("in while loop: " + child);
            addChild(parent, child);
            splitCommand = myCurrentCommand.split("\\s");
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
