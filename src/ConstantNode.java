public class ConstantNode extends CommandNode{

    private double myValue;

    public ConstantNode(String val){
        super(val);
        myValue = Double.parseDouble(val);
    }
    public double evaluate(){
        return myValue;
    }

}
