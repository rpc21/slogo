
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscription;


public class TurtleSubscriber<T> implements Flow.Subscriber<T> {
    private Subscription subscription;
    public List<T> consumedElements = new LinkedList<>();

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }
    @Override
    public void onNext(T item) {
        System.out.println("Got : " + item);
        subscription.request(1);
    }
    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Done");
    }
}
