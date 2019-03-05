package GUI;

import javafx.scene.control.Tab;

import java.util.function.Consumer;

public class TurtleViewTabExplorer extends TabExplorer {

    public static final String PEN_PROPERTIES = "Pen Properties";
    public static final String TURTLE_PROPERTIES = "Turtle Properties";
    private SLogoTab myPenProperties;
    private SLogoTab myTurtleProperties;

    public TurtleViewTabExplorer(){
        myPenProperties = new SLogoTab(PEN_PROPERTIES);
        myTurtleProperties = new SLogoTab(TURTLE_PROPERTIES);
        getTabs().addAll(myPenProperties, myTurtleProperties);
    }

    public Consumer<DisplayView> getTabAccess(){
        return (x) -> {
            updatePenPropertiesTab(x);
            updateTurtleProperties(x);
        };
    }

    private void updateTurtleProperties(DisplayView x) {
        myTurtleProperties.clearContents();
        myTurtleProperties.addContents("Showing turtle properties for turtle " + x.getTurtleId());
        myTurtleProperties.addContents("X Coordinate: " + x.getTranslateX());
        myTurtleProperties.addContents("Y Coordinate: " + x.getTranslateY());
        myTurtleProperties.addContents("Heading: " + x.getRotate());
    }

    private void updatePenPropertiesTab(DisplayView x) {
        myPenProperties.clearContents();
        myPenProperties.addContents("Pen Color: " + x.getMyPen().getMyColor());
        myPenProperties.addContents("Pen Size: " + x.getMyPen().getMyWidth());
        myPenProperties.addContents("Pen Down: " + x.getMyPen().isDown());
    }
}
