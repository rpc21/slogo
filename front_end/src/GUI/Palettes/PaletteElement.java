package GUI.Palettes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.Comparator;

public class PaletteElement<R extends Node> extends HBox implements Comparator<PaletteElement> {

    private int myIndex;
    private R myContent;

    public PaletteElement(int index, R content){
        setAlignment(Pos.CENTER);
        setPadding(new Insets(0, 5, 0, 5));
        myIndex = index;
        myContent = content;
        getChildren().add(new Text(myIndex + ": "));
        getChildren().add(myContent);
    }

    public int getMyIndex(){
        return myIndex;
    }

    public R getMyDisplay(){
        return myContent;
    }

    @Override
    public int compare(PaletteElement o1, PaletteElement o2) {
        return Integer.compare(o1.myIndex, o2.myIndex);
    }

}
