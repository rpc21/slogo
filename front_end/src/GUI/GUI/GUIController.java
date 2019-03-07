package GUI.GUI;

import exceptions.*;
import parser.external.CommandController;

import java.lang.reflect.InvocationTargetException;

public class GUIController {
    private CommandController myController = new CommandController();
    private GUIDisplay myDisplay;
    GUIExecute myExecuteFunction = new GUIExecute() {
        @Override
        public void executeCurrentCommand(String c, String language) throws InvalidInputException {
            execute(c, language);
        }
    };

    public GUIController(GUIDisplay display){
        myDisplay = display;
        myDisplay.setUpRunButton(myExecuteFunction);
    }

    public void execute(String command, String language) throws InvalidInputException {

        double answer = myController.execute(command, language);

        myDisplay.executeVisualCommands(myController.getMyVisualCommands());

        //myController.updateState(ImmutableTurtleState);
    }

}
