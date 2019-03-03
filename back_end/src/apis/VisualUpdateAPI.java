package apis;

public interface VisualUpdateAPI {

    void turtleMove(int id, double x, double y);
    void turtleTurn(int id,double degrees);
//    void turnRight(double degrees);
//    void turnLeft(double degrees);
    void setPenUp(int id);
    void setPenDown(int id);
    void showTurtle(int id);
    void hideTurtle(int id);
    void setOrientation(int id, double degrees);
    void setTowards(int id, double degrees);//turn turtle to face this point
    void setLocation(int id, double x, double y);//move turtle to these coordinates
    void goHome(int id);
    void clearScreen();
    void setBackgroundColor(int index);
    void setPenColor(int id, int index);
    void setPenSize(int id, double pixels);
    void setShape(int id, int index);
    void setPalette(int index, int r, int b, int g);

}
