package GUI.Palettes;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Comparator;

public class ColorPaletteElement extends GridPane implements Comparator<ColorPaletteElement> {

    private int myIndex;
    private Color myColor;
    private Rectangle myRectangle;

    public ColorPaletteElement(int index, Color color){
        myIndex = index;
        myColor = color;
        myRectangle = new Rectangle(50, 10);
        myRectangle.setFill(color);
        add(new Text(myIndex+""), 0, 0, 1, 1);
        add(myRectangle, 1, 0, 3, 1);
    }

    public ColorPaletteElement(int index, int red, int green, int blue){
        this(index, Color.rgb(red, green, blue));
    }

//    public double[] getMyRBG(){
//        return new double[] {myColor.getRed(), myColor.getBlue(), myColor.getGreen()};
//    }

    public Color getMyColor(){
        return myColor;
    }

    public int getMyIndex(){
        return myIndex;
    }

    @Override
    public int compare(ColorPaletteElement o1, ColorPaletteElement o2) {
        return Integer.compare(o1.myIndex, o2.myIndex);
    }
}
