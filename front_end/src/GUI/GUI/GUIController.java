package GUI.GUI;

import exceptions.*;
import parser.external.CommandController;

/**
 * Class that executes commands and displays results
 */
public class GUIController {

    private CommandController myController = new CommandController();

    private GUIDisplay myDisplay;
    GUIExecute myExecuteFunction = new GUIExecute() {
        @Override
        public void executeCurrentCommand(String c, String language) throws InvalidInputException {
            execute(c, language);
        }
    };

    /**
     * Constructor for GUIController
     * @param display Active GUIDisplay so that the controller can access the run button
     */
    public GUIController(GUIDisplay display){
        myDisplay = display;
        myDisplay.setUpRunButton(myExecuteFunction);
    }

    /**
     * Executes a command and displays visual result
     * @param command Command to execute
     * @param language Current language user is using
     * @throws InvalidInputException Exception caused by incorrect user input
     */
    public void execute(String command, String language) throws InvalidInputException {
        double answer = myController.execute(command, language);
        myDisplay.executeVisualCommands(myController.getMyVisualCommands());
    }

}
