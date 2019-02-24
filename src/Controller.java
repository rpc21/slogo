public class Controller {
    private Parser myParser;
    private static final int INVALID_COMMAND = 0;

    public Controller(){
        myParser = new Parser();
    }

    public double execute(String command){
        CommandNode myNode;
        try{
            myNode = myParser.parse(command);
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
