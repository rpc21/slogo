//package GUI.Palettes;
//
//import javafx.scene.control.ScrollPane;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//
//import java.util.Map;
//import java.util.TreeMap;
//import java.util.function.Function;
//
//public class Palette<T extends PaletteElement<R>, R> extends ScrollPane {
//
//    VBox myVBox;
//    Map<Integer, T> myPaletteElements;
//
//    public Palette(){
//        myVBox = new VBox();
//        myPaletteElements = new TreeMap<>();
//        myVBox.getChildren().addAll(myPaletteElements.values());
//        setContent(myVBox);
////        populateColorPalette();
//    }
//
////    private void populateColorPalette() {
////        this.addPaletteElement(new ColorPaletteElement(1, Color.BLACK));
////        this.addPaletteElement(new ColorPaletteElement(2, Color.RED));
////        this.addPaletteElement(new ColorPaletteElement(3, Color.GREEN));
////    }
//
//    public void addPaletteElement(T element){
//        myPaletteElements.put(element.getMyIndex(), element);
//        myVBox.getChildren().clear();
//        myVBox.getChildren().addAll(myPaletteElements.values());
//    }
//
//    public void removePaletteElement(T element){
//        myPaletteElements.remove(element.getMyIndex());
//    }
//
//    public R getContent(int index) {
//        return myPaletteElements.get(index).getMyDisplay();
//    }
//
////    public Function<Integer, Color> colorLookupAccess(){
////        return (x) -> getContent(x);
////    }
//
//}
