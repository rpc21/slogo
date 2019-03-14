package apis;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 * This interface is meant to help abstract information from the back end to the front end, as the front end receives
 *  a list of visual commands to execute and accomplishes this by passing it an implementation of the VisualUpdateAPI
 *  which allows for an implementation of this class to invoke some particular method that the front end implements
 */
public interface ImmutableVisualCommand {
    void execute(VisualUpdateAPI myCanvas);
}
