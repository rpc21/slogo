package GUI;

public class GUIdata {
    private String myTextToUpdate;
    private String myCommandToRun;

    public void setMyTextToUpdate(String command){
        myTextToUpdate = command;
    }

    public String getMyTextToUpdate(){
        return myTextToUpdate;
    }

    public void setMyCommandToRun(String command){
        myCommandToRun = command;
    }

    public String getMyCommandToRun(){
        return myCommandToRun;
    }

}
