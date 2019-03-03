package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListNode extends CommandNode {
    private List<Double> myList = new ArrayList<>();
    public ListNode(String a){
        super(a);
    }
    @Override
    public double evaluate(java.util.List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        if (super.getChildren().size() == 0)
            return 0;
        else
            return myList.get(myList.size()-1);

    }
    /*
      * ListNode can have an unlimited number of children
     */
    @Override
    public void addChild(CommandNode c){
        super.addChild(c);
    }
}
