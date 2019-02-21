package GUI;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

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
        grid.add(makeTextBox(), 0, 7, 5, 3);
        grid.add(runButton(), 5, 7);
        grid.add(clearButton(), 5, 8);
        grid.add(helpButton(), 5, 9);
        grid.add(makeTurtleCanvas(), 0, 1, 5, 5);
        grid.add(makeTabs(), 6, 0);
        return grid;
    }

    private TabPane makeTabs(){
        TabPane tabs = new TabPane();
        Tab tab1 = new Tab("Tab1");
        Label label1 = new Label("This is tab one");
        tab1.setContent(label1);
        tabs.getTabs().add(tab1);

        Tab tab2 = new Tab("Tab2");
        Label label2 = new Label("This is tab two");
        tab2.setContent(label2);
        tabs.getTabs().add(tab2);

        Tab tab3 = new Tab("Tab3");
        Label label3 = new Label("This is tab three");
        tab3.setContent(label3);
        tabs.getTabs().add(tab3);

        return tabs;
    }

    private TextArea makeTextBox(){
        TextArea textArea = new TextArea();
        textArea.setPrefRowCount(4);
        textArea.setPrefColumnCount(10);
        textArea.setWrapText(true);
        return textArea;
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

    private Button helpButton(){
        Button button = new Button("Help");
        button.setOnAction(event -> {
            System.out.println("Help");
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
