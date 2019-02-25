package testing;

import main.Controller;

public class ParserTester {
    public static void main(String[] args){
        String testOne = "nodes.SumNode nodes.SumNode 3 10 1";
        Controller myController = new Controller();
      double resultOne = myController.execute(testOne);
        System.out.println(testOne + ": " + resultOne);
    }
}
