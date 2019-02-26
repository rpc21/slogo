package APIs;


import java.util.*;

/**
 *
 */
public interface ExternalBackEnd {

    /**
     * returns needed information regarding results of Command
     */
    public Collection<Object> getCommandResult();

    /**
     * returns updated state of Turtle, including new position, orientation, and whether or not the pen is up or down
     */
   // public UnmodifiableDisplay getUnmodifiableDisplay();


}