package com.counter.maintainer.service;

import com.counter.maintainer.data.contracts.*;
import com.counter.maintainer.exceptions.InsufficientPrivilegesException;
import com.counter.maintainer.repository.CounterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.counter.maintainer.service.ProcessTimeConstants.CHECK_DEPOSIT_TIME_SEC;
import static com.counter.maintainer.service.ProcessTimeConstants.MANAGER_APPROVAL_TIME_IN_SEC;
import static com.counter.maintainer.service.ProcessTimeConstants.WITHDRAW_TIME_IN_SEC;

@Service
public class CounterServiceImpl implements CounterService {

    @Autowired
    private CounterRepository counterRepository;
    @Autowired
    private CounterManager counterManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private EmployeeService employeeService;

    private static final Logger logger = LoggerFactory.getLogger(CounterServiceImpl.class);


    @Override
    public Token serveToken(Token token, CounterDesk counterDesk) {
        while (counterDesk.getServiceTypes().contains((ServiceType)token.peekNextServiceType())) {
            Enum serviceType = token.pollNextServiceType();
            if(serviceType == null) {
                token.setStatus(TokenStatus.COMPLETED);
                updateTokenStatus(token.getTokenId(), TokenStatus.COMPLETED, counterDesk.getEmpId());
                break;
            }
            logger.info("CounterId:{}, TokenId:{}, Processing Request:{}", counterDesk.getCounterId(), token.getTokenId(), serviceType.name());
            processRequest((ServiceType)serviceType);
        }
        if(token.getActionItems().size() > 0) {
            token = counterManager.assignTokenToCounter(token);
            updateTokenComments(token.getTokenId(), "Redirecting to counter Id:"+ token.getCounterId());
        } else {
            token.setStatus(TokenStatus.COMPLETED);
            updateTokenStatus(token.getTokenId(), TokenStatus.COMPLETED, counterDesk.getEmpId());
            updateTokenComments(token.getTokenId(), "Completed processing token at counter id:"+ token.getCounterId());
        }
        tokenService.updateTokenQueueStatus(token.getTokenId(), counterDesk.getCounterId(), false/*inQ*/);
        return token;
    }

    @Override
    public Boolean updateTokenStatus(Long tokenId, TokenStatus tokenStatus, Long emplId) {
        if(tokenStatus == TokenStatus.COMPLETED || tokenStatus == TokenStatus.CANCELLED) {
            EmployeeRole role = employeeService.getEmployeeRole(emplId);
            if(role == EmployeeRole.MANAGER || role == EmployeeRole.OPERATOR) {
                tokenService.updateTokenStatus(tokenId, tokenStatus);
                } else {
                throw new InsufficientPrivilegesException("Only MANAGER or OPERATOR have privileges to " + tokenStatus.name() + " the token");
            }

        } else {
            tokenService.updateTokenStatus(tokenId, tokenStatus);
        }
        return true;
    }

    @Override
    public Token updateTokenComments(Long tokenId, String comments) {
        return tokenService.updateTokenComments(tokenId, comments);
    }

    public List<CounterDetails> getCounterStatus() {
        return counterRepository.getAvailableCounters();
    }

    private void processRequest(ServiceType serviceType) {
        switch (serviceType) {
        case ACC_VERIFICATION:
        case BALANCE_ENQUIRY:
        case CASH_WITHDRAW:
        case CASH_DEPOSIT:
        case ACC_CLOSE:
        case ACC_OPEN:
        case DOC_VERIFICATION:
            //process the request
            logger.info("Processing {} request, will be completed in 1 min", serviceType.name());
            waitInSec(WITHDRAW_TIME_IN_SEC);
            break;
        case CHECK_DEPOSIT:
            //process the request
            logger.info("Processing {} request, will be completed in 1 min", serviceType.name());
            waitInSec(CHECK_DEPOSIT_TIME_SEC);
            break;
        case MANAGER_APPROVAL:
            //process the request
            logger.info("Processing {} request, will be completed in 2 mins", serviceType.name());
            waitInSec(MANAGER_APPROVAL_TIME_IN_SEC);
            break;
        default:
            logger.error("Unknown ServiceType: {}", serviceType.name());
        }
    }

    private void waitInSec(int i) {
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
