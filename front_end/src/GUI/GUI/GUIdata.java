package GUI.GUI;

/**
 * Class used to pass data between other classes
 * Author: Louis Jensen
 */
public class GUIdata {
    private String myTextToUpdate = "";
    private String myCommandToRun = "";

    /**
     * Stores string  so that it can be passed to GUIDisplay
     * @param command String to store
     */
    public void setMyTextToUpdate(String command){
        myTextToUpdate = command;
    }

    /**
     * Gets any string that is being stored in myTextToUpdate
     * @return String that was stored
     */
    public String getMyTextToUpdate(){
        return myTextToUpdate;
    }

    /**
     * Stores a command that will be run to pass to another class
     * @param command Command to store
     */
    public void setMyCommandToRun(String command){
        myCommandToRun = command;
    }

    /**
     * Gets a command that wants to be run by another class
     * @return Command to run
     */
    public String getMyCommandToRun(){
        return myCommandToRun;
    }

}
