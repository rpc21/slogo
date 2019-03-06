package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListNode extends CommandNode {
    public ListNode(String a){
        super(a);
    }
    @Override
    public double evaluate(java.util.List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        double ret = 0;
        for (CommandNode c: super.getChildren())
            ret = c.evaluate(myVisCommands,myTurtles);
       return ret;
    }

    /*
      * ListNode can have an unlimited number of children
     */
    @Override
    public void addChild(CommandNode c){
        super.addChild(c);
    }
}
