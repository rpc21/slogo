public class Parser {
    CommandFactory myCommandFactory = new CommandFactory();

    public CommandNode parse(String command){
        CommandNode root = myCommandFactory.makeCommand(command.split(" ")[0]);
        CommandNode subRoot = myCommandFactory.makeCommand(command.split(" ")[1]);
        root.addChild(subRoot);
        subRoot.addChild(myCommandFactory.makeCommand(command.split(" ")[2]));
        subRoot.addChild(myCommandFactory.makeCommand(command.split(" ")[3]));
        root.addChild(myCommandFactory.makeCommand(command.split(" ")[4]));
        return root;
    }
}
