package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class DisplayView extends ImageView {

    public DisplayView(){
        super();
    }

    public DisplayView(Image image){
        super(image);
    }
}
