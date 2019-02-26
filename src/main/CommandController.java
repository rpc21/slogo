


package main;
import java.util.ArrayList;
import java.util.List;

import exceptions.InvalidCommandException;
import nodes.CommandNode;
import nodes.*;
import nodes.VisualCommand;

public class CommandController {
    private Parser myParser;
    private static final int INVALID_COMMAND = 0;
    List<VisualCommand> myVisualCommands;

    public CommandController(){
        myParser = new Parser();
    }

    public double execute(String command){
        CommandNode myNode;
        myNode = new TurtleBackward("backward");
        myNode.addChild(new ConstantNode("" + Double.parseDouble(command.split(" ")[1])));
        try{
            myNode = myParser.parse(command).get(0); // note of change! This is changed now because their could be many commands in a list that come from a parser.
        }
        catch(ArithmeticException e){
         return INVALID_COMMAND;
       } catch (InvalidCommandException e) {
            // todo : fix e.printStackTrace();
        }
        myVisualCommands = new ArrayList<VisualCommand>();
        try {
            return myNode.evaluate(myVisualCommands);
        }
        catch(Exception e) {
            System.out.println("Invalid Calculation");
            return INVALID_COMMAND;
        }
        //pass list of visual commands to vis
        //would need to write
        //for (nodes.VisualCommand c: myVisualCommands)
        // c.execute(myCanvas);
    }
    public List<VisualCommand> getMyVisualCommands(){
        return myVisualCommands;
    }

}
