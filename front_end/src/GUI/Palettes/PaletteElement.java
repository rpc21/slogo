//package GUI.Palettes;
//
//import GUI.Turtle.BasicTurtleView;
//import GUI.Turtle.DisplayView;
//import javafx.scene.Node;
//import javafx.scene.text.Text;
//
//public class PaletteElement<R> extends Node {
//
//    private int myIndex;
//    private DisplayView myTurtle;
//    private R myDisplay;
//    private String myTurtleType;
//
//    public PaletteElement(int index, String turtleType){
//        myIndex = index;
//        myTurtleType = turtleType;
//        try {
//            var clazz = Class.forName("GUI.Turtle"+turtleType);
//            myTurtle = (DisplayView) clazz.getDeclaredConstructor().newInstance();
//        }
//        catch (Exception e){
//            myTurtle = new BasicTurtleView();
//        }
//        add(new Text(myIndex+""), 0, 0, 1, 1);
//        add(myTurtle, 1, 0, 3, 1);
//    }
//
//
//
//    public int getMyIndex(){
//        return myIndex;
//    }
//
//    public R getMyDisplay(){
//        return myDisplay;
//    }
//
//    public String getMyStringRepresentation(){}
//
//    @Override
//    public int compare(TurtlePaletteElement o1, TurtlePaletteElement o2) {
//        return Integer.compare(o1.myIndex, o2.myIndex);
//    }
//}
