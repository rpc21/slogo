package apis;

public interface CanvasAPI {

    void turtleMove(double pixels);
    void turnRight(double degrees);
    void turnLeft(double degrees);
    void setPenUp();
    void setPenDown();
    void showTurtle();
    void hideTurtle();
    void setOrientation(double degrees);
    void setTowards(double x, double y);//turn turtle to face this point
    void setLocation(double x, double y);//move turtle to these coordinates
    void goHome();
    void clearScreen();
}
