import com.counter.maintainer.TokenMaintenanceApp;
import com.counter.maintainer.data.contracts.*;
import com.counter.maintainer.repository.CounterRepository;
import com.counter.maintainer.service.TokenService;
import com.counter.maintainer.service.TokenServiceImpl;
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


    @Test
    public void createTokenForNewCustomer() throws InterruptedException {
        Token createdToken = tokenService.createToken(getFakeToken(ServicePriority.PREMIUM, TokenType.DEPOSIT));
        Assert.assertTrue(createdToken.getTokenId()>0);
        Assert.assertTrue(createdToken.getCounterId()>0);
        Assert.assertTrue(createdToken.getStatus()==TokenStatus.QUEUED);
        Thread.sleep(2000);

        Assert.assertTrue(tokenService.getToken(createdToken.getTokenId()).getStatus() == TokenStatus.COMPLETED);
    }

    public static Token getFakeToken(ServicePriority servicePriority) {
        Token token = new Token();
        Customer customer = new Customer();
        Address address = new Address();
        address.setState("AP");
        address.setCity("HYD");
        address.setCountry("INDIA");
        address.setStreetName("ROAD No.1");
        address.setZipCode("500034");
        customer.setAddress(address);
        customer.setPhoneNumber("123456789");
        customer.setName("Dummy Customer");
        token.setCustomer(customer);
        token.setServicePriority(servicePriority);
        token.setTokenType(TokenType.WITHDRAW);
        return  token;
    }

    public static Token getFakeToken(ServicePriority servicePriority, long tokenId) {
        Token token = getFakeToken( servicePriority);
        token.setTokenId(tokenId);
        return token;
    }

    public static Token getFakeToken(ServicePriority servicePriority, TokenType tokenType) {
        Token token = getFakeToken( servicePriority);
        token.setTokenType(tokenType);
        return token;
    }


}
