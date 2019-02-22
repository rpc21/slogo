public class ConstantNode extends CommandNode{

    private int myValue;

    public ConstantNode(String val){
        super(val);
        myValue = Integer.parseInt(val);
    }
    public int evaluate(){
        return myValue;
    }

}
