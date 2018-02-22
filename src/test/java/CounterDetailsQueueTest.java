import com.counter.maintainer.TokenMaintenanceApp;
import com.counter.maintainer.data.contracts.CounterQueue;
import com.counter.maintainer.data.contracts.CounterType;
import com.counter.maintainer.data.contracts.ServicePriority;
import com.counter.maintainer.data.contracts.Token;
import com.counter.maintainer.exceptions.EmptyCounterQueueException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TokenMaintenanceApp.class)
public class CounterDetailsQueueTest {

    @Test(expected = RuntimeException.class)
    public void counterQueueAddTest() {
        CounterQueue premiumQueue = new CounterQueue(CounterType.PREMIUM);
        CounterQueue regularQueue = new CounterQueue(CounterType.PREMIUM);
        CounterQueue bothQueue = new CounterQueue(CounterType.BOTH);

        bothQueue.addTokenToQueue(TokenTest.getFakeToken(ServicePriority.REGULAR));
        bothQueue.addTokenToQueue(TokenTest.getFakeToken(ServicePriority.REGULAR));
        bothQueue.addTokenToQueue(TokenTest.getFakeToken(ServicePriority.PREMIUM));
        bothQueue.addTokenToQueue(TokenTest.getFakeToken(ServicePriority.REGULAR));

        regularQueue.addTokenToQueue(TokenTest.getFakeToken(ServicePriority.PREMIUM));
        regularQueue.addTokenToQueue(TokenTest.getFakeToken(ServicePriority.REGULAR));

        premiumQueue.addTokenToQueue(TokenTest.getFakeToken(ServicePriority.PREMIUM));
        premiumQueue.addTokenToQueue(TokenTest.getFakeToken(ServicePriority.REGULAR));
    }

    @Test
    public void counterQueueOrderTest() throws EmptyCounterQueueException {
        CounterQueue bothQueue = new CounterQueue(CounterType.BOTH);

        Token regular1 = TokenTest.getFakeToken(ServicePriority.REGULAR, 10);
        Token regular2 = TokenTest.getFakeToken(ServicePriority.REGULAR, 20);
        Token regular3 = TokenTest.getFakeToken(ServicePriority.REGULAR, 30);

        Token premium1 = TokenTest.getFakeToken(ServicePriority.PREMIUM, 40);
        Token premium2 = TokenTest.getFakeToken(ServicePriority.PREMIUM, 50);
        Token premium3 = TokenTest.getFakeToken(ServicePriority.PREMIUM, 60);


        bothQueue.addTokenToQueue(regular1);
        bothQueue.addTokenToQueue(regular2);
        bothQueue.addTokenToQueue(regular3);


        bothQueue.addTokenToQueue(premium1);
        bothQueue.addTokenToQueue(premium2);
        bothQueue.addTokenToQueue(premium3);

        Assert.assertTrue(bothQueue.fetchToken().getTokenId() == premium1.getTokenId());
        bothQueue.addToRecentServedList(premium1);
        Assert.assertTrue(bothQueue.fetchToken().getTokenId() ==  premium2.getTokenId());
        bothQueue.addToRecentServedList(premium2);
        Assert.assertTrue(bothQueue.fetchToken().getTokenId() == regular1.getTokenId());
        bothQueue.addToRecentServedList(regular1);
        Assert.assertTrue(bothQueue.fetchToken().getTokenId() == premium3.getTokenId());
        bothQueue.addToRecentServedList(premium3);
        Assert.assertTrue(bothQueue.fetchToken().getTokenId() == regular2.getTokenId());
    }

}
