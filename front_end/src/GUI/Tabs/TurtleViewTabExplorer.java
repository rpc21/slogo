package GUI.Tabs;

import GUI.Turtle.DisplayView;
import javafx.scene.paint.Color;
import java.util.function.Consumer;

/**
 * Class that displays the turtle and pen properties of the the selected turtle.
 * Author: Ryan Culhane
 */
public class TurtleViewTabExplorer extends TabExplorer {

    private static final String PEN_PROPERTIES = "Pen Properties";
    private static final String TURTLE_PROPERTIES = "Turtle Properties";
    private static final int RBG_MAX = 255;
    public static final int FULL_ROTATION = 360;
    private BasicTab myPenProperties;
    private BasicTab myTurtleProperties;
    private final double EPSILON = 0.001;

    /**
     * TurtleViewTabExplorer constructor
     */
    public TurtleViewTabExplorer(){
        myPenProperties = new BasicTab(PEN_PROPERTIES);
        myTurtleProperties = new BasicTab(TURTLE_PROPERTIES);
        getTabs().addAll(myPenProperties, myTurtleProperties);
    }

    /**
     * Returns consumer that allows a DisplayView to be able to update the information being displayed in the tabs
     * @return tab access
     */
    public Consumer<DisplayView> getTabAccess(){
        return x -> {
            updatePenPropertiesTab(x);
            updateTurtleProperties(x);
        };
    }

    private void updateTurtleProperties(DisplayView x) {
        myTurtleProperties.clearContents();
        myTurtleProperties.addContents("Active? " + x.isItActive());
        myTurtleProperties.addContents(String.format("Heading: %.2f degrees", formatRotation(x.getRotate())));
        myTurtleProperties.addContents(String.format("Y Coordinate: %.2f", -1 * x.getTranslateY() + EPSILON));
        myTurtleProperties.addContents(String.format("X Coordinate: %.2f", x.getTranslateX()));
        myTurtleProperties.addContents(String.format("Showing turtle properties for turtle %d", x.getTurtleId()+1));
    }

    private double formatRotation(double rotate) {
        if (rotate >= 0){
            return rotate % FULL_ROTATION;
        }
        return FULL_ROTATION - (Math.abs(rotate) % FULL_ROTATION);
    }

    private void updatePenPropertiesTab(DisplayView x) {
        myPenProperties.clearContents();
        Color color = (Color) x.getMyPen().getMyColor();
        myPenProperties.addContents(String.format("Pen Color: [%d, %d, %d]", (int) (RBG_MAX * color.getRed()),
                (int) (RBG_MAX * color.getBlue()), (int) (RBG_MAX * color.getGreen())));
        myPenProperties.addContents(String.format("Pen Size: %.2f", x.getMyPen().getMyWidth()));
        myPenProperties.addContents(String.format("Pen Down: %b", x.getMyPen().isDown()));
    }
}
