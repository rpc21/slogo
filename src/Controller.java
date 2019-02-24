public class Controller {
    private Parser myParser;
    private static final int INVALID_COMMAND = 0;

    public Controller(){
        myParser = new Parser();
    }

    public double execute(String command){
        CommandNode myNode;
        try{
            myNode = myParser.parse(command).get(0); // note of change! This is changed now because their could be many commands in a list that come from a parser.
        }
        catch(ArithmeticException e){
            return INVALID_COMMAND;
        }
        try {
            return myNode.evaluate();
        }
        catch(Exception e) {
            System.out.println("Invalid Calculation");
            return INVALID_COMMAND;
        }
    }

}
