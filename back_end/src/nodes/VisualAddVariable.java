package nodes;

import apis.VisualUpdateAPI;

public class VisualAddVariable extends VisualCommand {
    private String myName;
    private Double myValue;
    public VisualAddVariable(String name, Double value){
        myName = name;
        myValue = value;
    }

    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        //myCanvas.addVariable(myName,myValue);
    }
}
