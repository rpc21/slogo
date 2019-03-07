//package GUI.Pen;
//
//import GUI.Turtle.DisplayView;
//
//public class ImmutableTurtleState {
//
//    private double myOrientation;
//    private double myXPosition;
//    private double myYPosition;
//    private boolean hidden;
//    private ImmutablePen myPen;
//
//
//    public ImmutableTurtleState(DisplayView displayView){
//        myOrientation = displayView.getRotate();
//        myXPosition = displayView.getTranslateX();
//        myYPosition = displayView.getTranslateY();
//        hidden = displayView.isVisible();
//        myPen = new ImmutablePen(displayView.getMyPen());
//    }
//
//    public double getMyOrientation() {
//        return myOrientation;
//    }
//
//    public double getMyXPosition() {
//        return myXPosition;
//    }
//
//    public double getMyYPosition() {
//        return myYPosition;
//    }
//
//    public boolean isHidden() {
//        return hidden;
//    }
//
//    public ImmutablePen getMyPen() {
//        return myPen;
//    }
//}
