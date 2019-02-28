package testing;

import exceptions.InvalidCommandException;
import main.CommandController;

public class ParserTester {
    public static void main(String[] args) throws InvalidCommandException {
        String testOne = "nodes.SumNode nodes.SumNode 3 10 1";
        CommandController myController = new CommandController();
      double resultOne = myController.execute(testOne);
        System.out.println(testOne + ": " + resultOne);
    }
}
