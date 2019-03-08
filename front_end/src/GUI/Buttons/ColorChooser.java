package GUI.Buttons;

import GUI.GUI.GUIComponent;
import GUI.Tabs.PaletteTabExplorer;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.function.Consumer;

public class ColorChooser extends ColorPicker implements GUIComponent {

    public ColorChooser(){
        super();
    }

    public ColorChooser(Consumer<Rectangle> colorPaletteAccess, Consumer<Paint> objectColorAccess){
        super();
        setOnAction(event -> {
            colorPaletteAccess.accept(new Rectangle(PaletteTabExplorer.COLOR_PALETTE_WIDTH,
                    PaletteTabExplorer.COLOR_PALETTE_HEIGHT, getValue()));
            objectColorAccess.accept(getValue());
        });
    }
}
