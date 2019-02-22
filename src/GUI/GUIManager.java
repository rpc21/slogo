package GUI;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GUIManager {

    private Stage myStage;
    private List<GUIComponent> myGUIComponents;
    private TurtleCanvas myTurtleCanvas;
    BorderPaneComponent myRoot;

    public GUIManager(Stage stage){
        myStage = stage;
        myGUIComponents = new ArrayList<>();
        render();
        Scene myScene = new Scene(myRoot, 900, 600);
        myStage.setTitle("SLogo");
        myStage.setScene(myScene);
        myStage.show();
    }

    private void render() {
        setBorderPane();
        createToolbar();
        createRightPanel();
        createBottomLine();
        createCanvas();
    }

    private void createBottomLine() {
        HBox bottomLine = new HBox();
        CommandLine commandLine = createCommandLine();
        VBox buttons = createButtons();
        Button helpButton = new Button("Help");
        bottomLine.getChildren().addAll(commandLine, buttons, helpButton);
        myRoot.setBottom(bottomLine);
    }

    private VBox createButtons() {
        VBox verticalButtons = new VBox();
        List<Button> buttons = new ArrayList<>();
        Button run = new Button("Run");
        Button clear = new Button("Clear");
        buttons.addAll(List.of(run, clear));
        verticalButtons.getChildren().addAll(buttons);
        return verticalButtons;
    }

    private void createCanvas() {
        TurtleCanvas turtleCanvas = new TurtleCanvas(550, 400);
        GraphicsContext graphicsContext = turtleCanvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.rect(0, 0, turtleCanvas.getWidth(), turtleCanvas.getHeight());
        graphicsContext.fill();
        myTurtleCanvas = turtleCanvas;
        myRoot.setCenter(turtleCanvas);

    }

    private CommandLine createCommandLine() {
        CommandLine commandLine = new CommandLine();
        commandLine.setPrefColumnCount(50);
        commandLine.setPrefRowCount(8);
        commandLine.setWrapText(true);
        return commandLine;
    }

    private void createRightPanel() {
        TabExplorer tabExplorer = new TabExplorer();
        SLogoTab variablesTab = new SLogoTab("Variables");
        SLogoTab userDefinedMethods = new SLogoTab("Methods");
        SLogoTab commandHistory = new SLogoTab("Command History");
        variablesTab.addContents("First Contents added to variables");
        variablesTab.addContents("Second Contents added to variables");
        commandHistory.addContents("here are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.");
        for (int i = 0; i < 100; i++) {
            userDefinedMethods.addContents("Is this scrollable? "+i);
        }
        tabExplorer.getTabs().addAll(variablesTab, userDefinedMethods, commandHistory);
        myRoot.setRight(tabExplorer);
    }

    private void createToolbar() {
        Toolbar toolbar = new Toolbar();
        List<Control> toolbarMenus = new ArrayList<>();
        initializeToolbarMenus(toolbarMenus);
        System.out.println(toolbar);
        toolbar.getChildren().addAll(toolbarMenus);
        myRoot.setTop(toolbar);
    }

    private void initializeToolbarMenus(List<Control> toolbarMenus) {
        LanguageChooser<String> languageChooser = createLanguageChooser();
        toolbarMenus.add(languageChooser);
        BackgroundColorChooser backgroundColorChooser = createBackgroundChooser();
        toolbarMenus.add(backgroundColorChooser);
        PenColorChooser penColorChooser = new PenColorChooser();
        toolbarMenus.add(penColorChooser);
        ImageChooser imageChooser = createImageChooser();
        toolbarMenus.add(imageChooser);
    }

    private BackgroundColorChooser createBackgroundChooser() {

        BackgroundColorChooser backgroundColorChooser = new BackgroundColorChooser();
        backgroundColorChooser.setOnAction(event -> {
            myTurtleCanvas.getGraphicsContext2D().setFill(backgroundColorChooser.getValue());
            myTurtleCanvas.getGraphicsContext2D().rect(0, 0, myTurtleCanvas.getWidth(), myTurtleCanvas.getHeight());
            myTurtleCanvas.getGraphicsContext2D().fill();
        });
        return backgroundColorChooser;
    }

    private ImageChooser createImageChooser() {
        ImageChooser imageChooser = new ImageChooser();
        imageChooser.getItems().addAll("Basic Turtle Image", "Advanced Turtle Image");
        imageChooser.getSelectionModel().selectFirst();
        return imageChooser;
    }


    private LanguageChooser<String> createLanguageChooser() {
        LanguageChooser<String> languageChooser = new LanguageChooser<>();
        languageChooser.getItems().addAll("English", "German", "French");
        languageChooser.getSelectionModel().selectFirst();
        return languageChooser;
    }

    private void setBorderPane() {
        myRoot = new BorderPaneComponent();
        myGUIComponents.add(myRoot);
    }


}
