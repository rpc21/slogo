package GUI;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;

import java.util.concurrent.CancellationException;

public class Display {

    private Scene myScene;
    private String myTitle = "SLogo";
    private GridPane myRoot;

    public void start (Stage stage) {
        myScene = setupVisualization(stage);
        stage.setScene(myScene);
        stage.setTitle(myTitle);
        stage.setResizable(true);
        stage.show();
    }

    private Scene setupVisualization(Stage stage) {
        myRoot = createGridPane();
        Scene myScene = new Scene(myRoot,1200, 650, Color.LIGHTGRAY);
        return myScene;
    }

    private GridPane createGridPane(){
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        Text title = new Text("SLogo");
        title.setFont(Font.font("Arial", 50));
        grid.add(title, 0, 0);
        grid.add(chooseLanguage(), 1, 0);
        grid.add(choosePenColor(), 2, 0);
        grid.add(chooseCanvasColor(), 3, 0);
        grid.add(chooseTurtleIcon(), 4, 0);

        TextField textField = new TextField ();
        grid.add(textField, 0, 7);

        grid.add(runButton(), 5, 1);
        grid.add(clearButton(), 5, 2);
        grid.add(makeTurtleCanvas(), 0, 1, 5, 5);

        return grid;
    }

    private Canvas makeTurtleCanvas(){
        Canvas turtleCanvas = new Canvas(800, 450);
        GraphicsContext gc = turtleCanvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.rect(0, 0, turtleCanvas.getWidth(), turtleCanvas.getHeight());
        gc.fill();
        return turtleCanvas;
    }

    private Button runButton(){
        Button button = new Button("Run");
        button.setOnAction(event -> {
            System.out.println("Run");
        });
        return button;
    }

    private Button clearButton(){
        Button button = new Button("Clear");
        button.setOnAction(event -> {
            System.out.println("Clear");
        });
        return button;
    }

    private MenuButton chooseLanguage(){
        MenuButton menuButton = new MenuButton("Select Language");
        MenuItem english = new MenuItem("English");
   //     english.setOnAction(event -> {
          //  edgeType = "regular";
   //     });
        MenuItem spanish = new MenuItem("Spanish");
   //     spanish.setOnAction(event -> {
          //  edgeType = "toroid";
    //    });
        MenuItem chinese = new MenuItem("Chinese");
   //     chinese.setOnAction(event -> {
            //  edgeType = "toroid";
  //      });
        menuButton.getItems().addAll(english, spanish, chinese);
        return menuButton;
    }

    private MenuButton choosePenColor(){
        MenuButton menuButton = new MenuButton("Select Pen Color");
        MenuItem black = new MenuItem("Black");
        //     english.setOnAction(event -> {
        //  edgeType = "regular";
        //     });
        MenuItem blue = new MenuItem("Blue");
        //     spanish.setOnAction(event -> {
        //  edgeType = "toroid";
        //    });
        MenuItem red = new MenuItem("Red");
        //     chinese.setOnAction(event -> {
        //  edgeType = "toroid";
        //      });
        menuButton.getItems().addAll(black, blue, red);
        return menuButton;
    }

    private MenuButton chooseCanvasColor(){
        MenuButton menuButton = new MenuButton("Select Canvas Color");
        MenuItem white = new MenuItem("White");
        //     english.setOnAction(event -> {
        //  edgeType = "regular";
        //     });
        MenuItem grey = new MenuItem("Grey");
        //     spanish.setOnAction(event -> {
        //  edgeType = "toroid";
        //    });
        MenuItem blue = new MenuItem("Blue");
        //     chinese.setOnAction(event -> {
        //  edgeType = "toroid";
        //      });
        menuButton.getItems().addAll(white, grey, blue);
        return menuButton;
    }

    private MenuButton chooseTurtleIcon(){
        MenuButton menuButton = new MenuButton("Select Turle Icon");
        MenuItem turtle = new MenuItem("Turtle");
        //     english.setOnAction(event -> {
        //  edgeType = "regular";
        //     });
        MenuItem star = new MenuItem("Star");
        //     spanish.setOnAction(event -> {
        //  edgeType = "toroid";
        //    });
        MenuItem arrow = new MenuItem("Arrow");
        //     chinese.setOnAction(event -> {
        //  edgeType = "toroid";
        //      });
        menuButton.getItems().addAll(turtle, star, arrow);
        return menuButton;
    }
}
