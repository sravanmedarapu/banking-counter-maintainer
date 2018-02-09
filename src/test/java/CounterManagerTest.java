import com.counter.maintainer.config.TokenMaintenanceApp;
import com.counter.maintainer.data.contracts.CounterDetails;
import com.counter.maintainer.data.contracts.ServicePriority;
import com.counter.maintainer.data.contracts.ServiceType;
import com.counter.maintainer.data.contracts.Token;
import com.counter.maintainer.service.CounterManager;
import com.counter.maintainer.service.CounterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
/*
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TokenMaintenanceApp.class)*/
public class CounterManagerTest {

    @Autowired
    private CounterManager counterManager;
    @Autowired
    private CounterService counterService;
    //@Test
    public void counterTest() {
        Token token = TokenCounterDetailsTest.getFakeToken(ServicePriority.PREMIUM, ServiceType.WITHDRAW);
        counterManager.assignTokenToCounter(token);

        List<CounterDetails> counterDetailList = counterManager.getCounterStatus();




    }
}
