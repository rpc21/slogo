package GUI.CanvasItems;

import GUI.Commands.CommandExecutable;
import GUI.Commands.Language;
import GUI.Commands.LanguageChangeable;
import GUI.Turtle.BasicTurtleView;
import GUI.Turtle.DisplayView;
import GUI.Turtle.DisplayViewFactory;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class StackedCanvasPane extends StackPane implements CommandExecutable, LanguageChangeable {

    public static final double DEFAULT_CANVAS_WIDTH = 800;
    public static final double DEFAULT_CANVAS_HEIGHT = 450;

    private TurtleCanvas myBackgroundCanvas;
    private TurtleCanvas myDrawingCanvas;
    private DisplayView myCurrentDisplayView;
    private List<DisplayView> myTurtles;
    public Function<Integer, Color> colorPaletteLookup;
    private Function<Integer, String> turtlePaletteLookup;
    private Consumer<String> myCommandAccess;
    private Language myLanguage;
    private Consumer<DisplayView> myTabAccess;
//    private boolean penDown;

    public StackedCanvasPane(){
        super();
        myTurtles = new ArrayList<>();
        myBackgroundCanvas = createBackgroundCanvas(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT);
        myDrawingCanvas = new TurtleCanvas(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT);
//        myCurrentDisplayView = new BasicTurtleView(myDrawingCanvas);
//        makeTurtle();
//        myCurrentDisplayView = myTurtles.get(0);
//        penDown = true;
        getChildren().addAll(myBackgroundCanvas, myDrawingCanvas);
        makeTurtle();
        this.setLayoutX(DEFAULT_CANVAS_WIDTH);
        this.setLayoutY(DEFAULT_CANVAS_HEIGHT);
    }

    private TurtleCanvas createBackgroundCanvas(double width, double height) {
        TurtleCanvas canvas = new TurtleCanvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.rect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.fill();
        return canvas;
    }

    public void makeTurtle(){
        DisplayView newTurtle = new BasicTurtleView(myDrawingCanvas);
        newTurtle.setLanguage(myLanguage);
        newTurtle.giveAbilityToRunCommands(myCommandAccess);
        getChildren().add(newTurtle);
        newTurtle.setTurtleId(myTurtles.size());
        newTurtle.giveTabAccess(myTabAccess);
        myTurtles.add(newTurtle);
    }

    public Consumer<Color> getBackgroundColorAccess(){
        return myBackgroundCanvas.getBackgroundColorAccess();
    }

    public Consumer<Paint> getPenPropertiesAccess(){
        return (x) -> {
            for (DisplayView turtle : myTurtles) {
                turtle.getMyPen().setMyColor(x);
            }
        };
    }

    public Consumer<String> getIconAccess(){
        return (x) -> {
            this.getChildren().remove(myCurrentDisplayView);
            myCurrentDisplayView = new DisplayViewFactory().generateDislplayView(x, myCurrentDisplayView);
            this.getChildren().add(myCurrentDisplayView);
        };
    }

//    public void addMove(Move move){
//        myCurrentDisplayView.addMove(move);
//        myCurrentDisplayView.drawPath();
//    }

//    public void addAllMoves(List<Move> moves){
//        myCurrentDisplayView.addAllMoves(moves);
//    }
//
//    public void batchUpdateCanvas(){
//        myCurrentDisplayView.drawPath();
//    }

    public void resizeCanvases(double v, double v1) {
        myBackgroundCanvas.resize(v, v1);
        myDrawingCanvas.resize(v, v1);
    }

    public void turtleMove(int id, double x, double y){
        myTurtles.get(id).turtleMove(x,y);
    }

    public void turtleTurn(int id, double degrees){
        myTurtles.get(id).turn(degrees);
    }

//    @Override
//    public void turnRight(double degrees) {
//        myCurrentDisplayView.turn(degrees);
//    }
//
//    @Override
//    public void turnLeft(double degrees) {
//        myCurrentDisplayView.turn(-degrees);
//    }

    public void setPenUp(int id){
        myTurtles.get(id).setPenDown(false);
    }

    public void setPenDown(int id){
        myTurtles.get(id).setPenDown(true);
    }

    public void showTurtle(int id) {
        myTurtles.get(id).setVisible(true);
    }

    public void hideTurtle(int id) {
        myTurtles.get(id).setVisible(false);
    }

    public void setOrientation(int id, double degrees) {
        myTurtles.get(id).setRotate(degrees);
    }

    public void setTowards(int id, double degrees) {
        myTurtles.get(id).towards(degrees);
    }

    public void setLocation(int id, double x, double y) {
        myTurtles.get(id).setLocation(x, y);
    }

    public void goHome(int id) {
        myTurtles.get(id).goHome();
    }

    public void clearScreen() {
        myBackgroundCanvas.setColor(Color.WHITE);
        myDrawingCanvas.clearCanvas();
        for (DisplayView turtle: myTurtles){
            getChildren().remove(turtle);
        }
        myTurtles.clear();
        makeTurtle();
    }

    public void setPenColor(int id, Color color) {
        myTurtles.get(id).setPenColor(color);
    }

    public void setPenSize(int id, double pixels) {
        myTurtles.get(id).setPenWidth(pixels);
    }

    public void setTurtleShape(int id, String content) {
        DisplayView turtle;
        DisplayView turtleToRemove = myTurtles.get(id);
        try {
            var clazz = Class.forName("GUI."+content);
            turtle = (DisplayView) clazz.getDeclaredConstructor(DisplayView.class).newInstance(myTurtles.get(id));
        }
        catch (Exception e) {
            turtle = new BasicTurtleView();
        }
        this.getChildren().remove(turtleToRemove);
        this.getChildren().add(turtle);
        myTurtles.remove(id);
        myTurtles.add(id, turtle);
    }


//    public void setColorPaletteLookupAccess(Function<Integer, Color> colorLookupAccess) {
//        colorPaletteLookup = colorLookupAccess;
//    }
//
//    public void setTurtleLookupAccess(Function<Integer, String> turtleLookupAccess) {
//        turtlePaletteLookup = turtleLookupAccess;
//    }

    @Override
    public void giveAbilityToRunCommands(Consumer<String> commandAccess) {
        myCommandAccess = commandAccess;
        for (DisplayView turtle: myTurtles){
            turtle.giveAbilityToRunCommands(commandAccess);
        }
    }

    @Override
    public void runCommand(String command) {
        myCommandAccess.accept(command);
    }

    @Override
    public void setLanguage(Language newLanguage) {
        myLanguage = newLanguage;
        for (DisplayView turtle : myTurtles){
            turtle.setLanguage(newLanguage);
        }
    }

    public void grantTabAccess(Consumer<DisplayView> tabAccess){
        myTabAccess = tabAccess;
        for (DisplayView turtle : myTurtles){
            turtle.giveTabAccess(tabAccess);
        }
    }
}
