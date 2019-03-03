package apis;

public interface CanvasAPI {

    void turtleMove(double x, double y);
    void turtleTurn(double degrees);
//    void turnRight(double degrees);
//    void turnLeft(double degrees);
    void setPenUp();
    void setPenDown();
    void showTurtle();
    void hideTurtle();
    void setOrientation(double degrees);
    void setTowards(double degrees);//turn turtle to face this point
    void setLocation(double x, double y);//move turtle to these coordinates
    void goHome();
    void clearScreen();
}
