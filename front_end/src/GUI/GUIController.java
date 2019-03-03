package GUI;

import exceptions.InvalidCommandException;
import exceptions.InvalidVariableException;
import parser.external.CommandController;

public class GUIController {
    private CommandController myController = new CommandController();
    private GUIDisplay myDisplay;
    GUIExecute myExecuteFunction = new GUIExecute() {
        @Override
        public void executeCurrentCommand(String c, String language) throws InvalidCommandException, InvalidVariableException {
            execute(c, language);
        }
    };

    public GUIController(GUIDisplay display){
        myDisplay = display;
        myDisplay.setUpRunButton(myExecuteFunction);
    }
    public void execute(String command, String language) throws InvalidCommandException, InvalidVariableException {
        double answer = myController.execute(command, language);

        myDisplay.executeVisualCommands(myController.getMyVisualCommands());

        //myController.updateState(ImmutableTurtleState);
    }

}
