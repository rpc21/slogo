package GUI;

import exceptions.InvalidCommandException;
import main.CommandController;

public class GUIController {
    private CommandController myController = new CommandController();
    private GUIDisplay myDisplay;
    GUIExecute myExecuteFunction = new GUIExecute() {
        @Override
        public void executeCurrentCommand(String c) throws InvalidCommandException {
            execute(c);
        }
    };

    public GUIController(GUIDisplay display){
        myDisplay = display;
        myDisplay.setUpRunButton(myExecuteFunction);
    }
    public void execute(String command) throws InvalidCommandException {
        double answer = myController.execute(command);
        myDisplay.executeVisualCommands(myController.getMyVisualCommands());

        //myController.updateState(ImmutableTurtleState);
    }

}
