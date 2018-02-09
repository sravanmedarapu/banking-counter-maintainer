import com.counter.maintainer.config.TokenMaintenanceApp;
import com.counter.maintainer.data.contracts.*;
import com.counter.maintainer.repository.CounterRepository;
import com.counter.maintainer.service.TokenService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TokenMaintenanceApp.class)
public class TokenCounterDetailsTest
{
    @Autowired
    private CounterRepository counterRepository;

    @Autowired
    private TokenService tokenService;

    //@Test
    public void findAllUsers() {
        List<CounterDetails> counterDetails = counterRepository.getCountersStatus();
        assertNotNull(counterDetails);
        assertTrue(!counterDetails.isEmpty());

        counterRepository.getAvailableCounters(ServiceType.WITHDRAW);
    }

    //@Test
    public void createTokenForNewCustomer() {
        Token createdtToken = tokenService.createToken(getFakeToken(ServicePriority.PREMIUM));
        Assert.assertTrue(createdtToken.getTokenId()>0);
    }

    public static Token getFakeToken(ServicePriority servicePriority) {
        Token token = new Token();
        Customer customer = new Customer();
        Address address = new Address();
        address.setState("AP");
        address.setCity("HYD");
        address.setCountry("INDIA");
        address.setStreeName("ROAD No.1");
        address.setZipCode("500034");
        customer.setAddress(address);
        customer.setPhoneNumber("123456789");
        customer.setName("Dummy Customer");
        token.setCustomer(customer);
        token.setServicePriority(servicePriority);
        token.setServiceType(ServiceType.WITHDRAW);
        return  token;
    }

    public static Token getFakeToken(ServicePriority servicePriority, long tokenId) {
        Token token = getFakeToken( servicePriority);
        token.setTokenId(tokenId);
        return token;
    }

    public static Token getFakeToken(ServicePriority servicePriority, ServiceType serviceType) {
        Token token = getFakeToken( servicePriority);
        token.setServiceType(serviceType);
        return token;
    }


}
