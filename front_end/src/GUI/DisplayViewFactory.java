package GUI;

public class DisplayViewFactory {

    public DisplayView generateDislplayView(String displayViewName, DisplayView currentDisplayView){
        if (displayViewName.equals(DisplayView.BASIC_TURTLE_NAME)){
            return new BasicTurtleView(currentDisplayView);
        }
        else if (displayViewName.equals(DisplayView.ADVANCED_TURTLE_NAME)){
            return new AdvancedTurtleView(currentDisplayView);
        }
        else {
            return new BasicTurtleView(currentDisplayView);
        }
    }

}
