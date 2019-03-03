package nodes;

import java.lang.reflect.Constructor;

public class CommandFactory {

    public CommandNode makeCommand(double d) {
        return new ConstantNode("" + d);
    }

    public CommandNode makeCommand(String c){
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
