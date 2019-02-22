package GUI;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Display {

    private Scene myScene;
    private String myTitle = "SLogo";
    private GridPane myRoot;
    private TextArea myTextBox = makeTextBox();
    private String commandToExecute;
    private MenuButton myLanguageChooser = chooseLanguage();
    private MenuButton myPenColorChooser = choosePenColor();
    private MenuButton myBackGroundColorChooser = chooseCanvasColor();
    private MenuButton myTurtleIconChooser = chooseTurtleIcon();
    private Button myRunButton = runButton();
    private Button myClearButton = clearButton();
    private Button myHelpButton = helpButton();
    private TabPane myTabs = makeTabs();
    private Canvas myTurtleCanvas = makeTurtleCanvas();

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
        grid.add(myLanguageChooser, 1, 0);
        grid.add(myPenColorChooser, 2, 0);
        grid.add(myBackGroundColorChooser, 3, 0);
        grid.add(myTurtleIconChooser, 4, 0);
        grid.add(myTextBox, 0, 7, 5, 3);
        grid.add(myRunButton, 5, 7);
        grid.add(myClearButton, 5, 8);
        grid.add(myHelpButton, 5, 9);
        grid.add(myTurtleCanvas, 0, 1, 5, 5);
        grid.add(myTabs, 6, 1, 3, 5);
        return grid;
    }

    private TabPane makeTabs(){
        TabPane tabs = new TabPane();
        Tab tab1 = new Tab("Variable Explorer");
        Label label1 = new Label("Put variables here");
        tab1.setContent(label1);
        tabs.getTabs().add(tab1);

        Tab tab2 = new Tab("Command History");
        Label label2 = new Label("Put command history here");
        tab2.setContent(label2);
        tabs.getTabs().add(tab2);

        Tab tab3 = new Tab("Methods");
        Label label3 = new Label("Put user defined and built in methods here");
        tab3.setContent(label3);
        tabs.getTabs().add(tab3);

        tabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
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
            commandToExecute = myTextBox.getText();
        });
        return button;
    }

    private Button helpButton(){
        Button button = new Button("Help");
        button.setOnAction(event -> {
            System.out.println("Help");
            Alert help = showHelpMenu();
            help.show();
        });
        return button;
    }

    private Alert showHelpMenu(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("Help Menu");
        alert.setContentText("all the help info");
        return alert;
    }

    private Button clearButton(){
        Button button = new Button("Clear");
        button.setOnAction(event -> {
            System.out.println("Clear");
            myTextBox.setText("");
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
             white.setOnAction(event -> {
        //  edgeType = "regular";
                 myTurtleCanvas.getGraphicsContext2D().setFill(Color.WHITE);
                 myTurtleCanvas.getGraphicsContext2D().rect(0, 0, myTurtleCanvas.getWidth(), myTurtleCanvas.getHeight());
                 myTurtleCanvas.getGraphicsContext2D().fill();
             });
        MenuItem grey = new MenuItem("Grey");
             grey.setOnAction(event -> {
        //  edgeType = "toroid";
                 myTurtleCanvas.getGraphicsContext2D().setFill(Color.GREY);
                 myTurtleCanvas.getGraphicsContext2D().rect(0, 0, myTurtleCanvas.getWidth(), myTurtleCanvas.getHeight());
                 myTurtleCanvas.getGraphicsContext2D().fill();
            });
        MenuItem blue = new MenuItem("Blue");
             blue.setOnAction(event -> {
        //  edgeType = "toroid";
                 myTurtleCanvas.getGraphicsContext2D().setFill(Color.BLUE);
                 myTurtleCanvas.getGraphicsContext2D().rect(0, 0, myTurtleCanvas.getWidth(), myTurtleCanvas.getHeight());
                 myTurtleCanvas.getGraphicsContext2D().fill();
              });
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
