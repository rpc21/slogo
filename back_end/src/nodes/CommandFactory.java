package nodes;

import java.lang.reflect.Constructor;

public class CommandFactory {

    public CommandNode makeCommand(double d) {
        return new ConstantNode("" + d);
    }

    public CommandNode makeCommand(String c){

        if (c.length() < 3){
            ConstantNode hmmm = new ConstantNode(c);
            return hmmm;
        }
        Class myCommandClass;
        try {
            myCommandClass = Class.forName("nodes." + c);
        }
        catch (ClassNotFoundException e) {
            System.out.println("Invalid Command");
            return new ConstantNode("1");
        }
        Constructor myConstructor;
        try {
            myConstructor = myCommandClass.getConstructor(String.class);
        }
        catch (NoSuchMethodException e){
            System.out.println("Anna writes bad code");
            return new ConstantNode("1");
        }
        // I'm going to take for granted that I don't have to explain how or why `party` shouldn't be an instance variable.
        CommandNode myCommandNode;
        try {
            myCommandNode = (CommandNode)myConstructor.newInstance(c);
        }
        catch (Exception e){
            System.out.println("whoops");
            return new ConstantNode("1");
        }
        return myCommandNode;
    }
}
