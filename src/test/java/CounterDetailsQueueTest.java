import com.counter.maintainer.config.TokenMaintenanceApp;
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

/*@RunWith(SpringRunner.class)
@SpringBootTest(classes = TokenMaintenanceApp.class)*/
public class CounterDetailsQueueTest {

    //@Test(expected = RuntimeException.class)
    public void counterQueueAddTest() {
        CounterQueue premimumQeue = new CounterQueue(CounterType.PREMIUM);
        CounterQueue regularQeue = new CounterQueue(CounterType.PREMIUM);
        CounterQueue bothQeue = new CounterQueue(CounterType.BOTH);

        bothQeue.addTokenToQueue(TokenCounterDetailsTest.getFakeToken(ServicePriority.REGULAR));
        bothQeue.addTokenToQueue(TokenCounterDetailsTest.getFakeToken(ServicePriority.REGULAR));
        bothQeue.addTokenToQueue(TokenCounterDetailsTest.getFakeToken(ServicePriority.PREMIUM));
        bothQeue.addTokenToQueue(TokenCounterDetailsTest.getFakeToken(ServicePriority.REGULAR));

        regularQeue.addTokenToQueue(TokenCounterDetailsTest.getFakeToken(ServicePriority.PREMIUM));
        regularQeue.addTokenToQueue(TokenCounterDetailsTest.getFakeToken(ServicePriority.REGULAR));

        premimumQeue.addTokenToQueue(TokenCounterDetailsTest.getFakeToken(ServicePriority.PREMIUM));
        premimumQeue.addTokenToQueue(TokenCounterDetailsTest.getFakeToken(ServicePriority.REGULAR));
    }

    //@Test
    public void counterQueueOrderTest() throws EmptyCounterQueueException {
        CounterQueue bothQeue = new CounterQueue(CounterType.BOTH);

        Token regular1 = TokenCounterDetailsTest.getFakeToken(ServicePriority.REGULAR, 1);
        Token regular2 = TokenCounterDetailsTest.getFakeToken(ServicePriority.REGULAR, 2);
        Token regular3 = TokenCounterDetailsTest.getFakeToken(ServicePriority.REGULAR, 3);

        Token premium1 = TokenCounterDetailsTest.getFakeToken(ServicePriority.PREMIUM, 4);
        Token premium2 = TokenCounterDetailsTest.getFakeToken(ServicePriority.PREMIUM, 5);
        Token premium3 = TokenCounterDetailsTest.getFakeToken(ServicePriority.PREMIUM, 6);


        bothQeue.addTokenToQueue(regular1);
        bothQeue.addTokenToQueue(regular2);
        bothQeue.addTokenToQueue(regular3);


        bothQeue.addTokenToQueue(premium1);
        bothQeue.addTokenToQueue(premium2);
        bothQeue.addTokenToQueue(premium3);

        Assert.assertTrue(bothQeue.fetchToken().getTokenId() == premium1.getTokenId());
        Assert.assertTrue(bothQeue.fetchToken().getTokenId() ==  premium2.getTokenId());
        Assert.assertTrue(bothQeue.fetchToken().getTokenId() == regular1.getTokenId());
        Assert.assertTrue(bothQeue.fetchToken().getTokenId() == premium2.getTokenId());
        Assert.assertTrue(bothQeue.fetchToken().getTokenId() == regular3.getTokenId());
    }

}
