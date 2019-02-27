package nodes;

import java.util.List;

public class MakeVariable extends CommandNode{
    public MakeVariable(String a){
        super(a);
    }

    @Override
    public double evaluate(List<VisualCommand> myVisCommands){
        return super.getChildren().get(1).evaluate(myVisCommands);
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 2)
            throw new IllegalArgumentException();
        super.addChild(c);
    }

}
