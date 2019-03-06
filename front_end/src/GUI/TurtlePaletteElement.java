package GUI;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Comparator;

public class TurtlePaletteElement extends GridPane implements Comparator<TurtlePaletteElement> {

    private int myIndex;
    private DisplayView myTurtle;
    private String myTurtleType;

    public TurtlePaletteElement(int index, String turtleType){
        myIndex = index;
        myTurtleType = turtleType;
        try {
            var clazz = Class.forName("GUI."+turtleType);
            myTurtle = (DisplayView) clazz.getDeclaredConstructor().newInstance();
        }
        catch (Exception e){
            myTurtle = new BasicTurtleView();
        }
        add(new Text(myIndex+""), 0, 0, 1, 1);
        add(myTurtle, 1, 0, 3, 1);
    }

    public int getMyIndex(){
        return myIndex;
    }

    public DisplayView getMyTurtle(){
        return myTurtle;
    }

    public String getMyTurtleType(){
        return myTurtleType;
    }
    @Override
    public int compare(TurtlePaletteElement o1, TurtlePaletteElement o2) {
        return Integer.compare(o1.myIndex, o2.myIndex);
    }
}
