import com.counter.maintainer.TokenMaintenanceApp;
import com.counter.maintainer.data.contracts.*;
import com.counter.maintainer.exceptions.InsufficientPrivilegesException;
import com.counter.maintainer.repository.CounterRepository;
import com.counter.maintainer.service.CounterService;
import com.counter.maintainer.service.TokenService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TokenMaintenanceApp.class)
public class TokenTest
{
    @Autowired
    private CounterRepository counterRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CounterService counterService;

    private static final long SEC =1000;


    @Test
    public void createTokenForNewAndOldCustomer() throws InterruptedException {
        Token tokenReq = getFakeToken(ServicePriority.PREMIUM, TokenType.WITHDRAW);
        Token newCustomerToken = tokenService.createAndAssignToken(tokenReq);
        Assert.assertTrue(newCustomerToken.getCustomerId()>0);
        Assert.assertTrue(newCustomerToken.getTokenId()>0);
        Assert.assertTrue(newCustomerToken.getCounterId()>0);
        Assert.assertTrue(newCustomerToken.getStatus()==TokenStatus.QUEUED);
        Assert.assertTrue(tokenService.getToken(newCustomerToken.getTokenId()).getStatus() == TokenStatus.QUEUED);
        Thread.sleep(8 * SEC);

        Assert.assertTrue(tokenService.getToken(newCustomerToken.getTokenId()).getStatus() == TokenStatus.COMPLETED);

        tokenReq = getFakeToken(ServicePriority.PREMIUM, TokenType.WITHDRAW);
        long customerId = newCustomerToken.getCustomerId();
        tokenReq.setCustomerId(customerId);
        Token existingCustomerToken = tokenService.createAndAssignToken(tokenReq);

        Assert.assertTrue(existingCustomerToken.getCustomerId() == customerId);
        Assert.assertTrue(tokenService.getToken(existingCustomerToken.getTokenId()).getStatus() == TokenStatus.QUEUED);
        Thread.sleep(8 * SEC);

        Assert.assertTrue(tokenService.getToken(existingCustomerToken.getTokenId()).getStatus() == TokenStatus.COMPLETED);
    }

    @Test(expected= InsufficientPrivilegesException.class)
    public void tokenStatusUpdateTest() {
        Employee attender = new Employee();
        attender.setEmployeeId(7);
        attender.setDesignation(EmployeeRole.ATTENDER);
        Token token = getFakeToken(ServicePriority.PREMIUM, TokenType.DEPOSIT);
        counterService.updateTokenStatus(token.getTokenId(), TokenStatus.COMPLETED, attender.getEmployeeId());
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
        token.setBranchName("MIDTOWN");
        return  token;
    }

    public static Token getFakeToken(ServicePriority servicePriority, long tokenId) {
        Token token = getFakeToken( servicePriority);
        token.setTokenId(tokenId);
        token.setBranchName("MIDTOWN");
        return token;
    }

    public static Token getFakeToken(ServicePriority servicePriority, TokenType tokenType) {
        Token token = getFakeToken( servicePriority);
        token.setTokenType(tokenType);

        return token;
    }


}
