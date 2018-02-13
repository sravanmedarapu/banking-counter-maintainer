package com.counter.maintainer.repository;

import com.counter.maintainer.data.contracts.CounterDetails;
import com.counter.maintainer.data.contracts.CounterType;
import com.counter.maintainer.data.contracts.ServiceType;
import com.counter.maintainer.data.contracts.TokenType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CounterRepositoryImpl implements CounterRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly=true)
    public List<ServiceType> findCounterServices(long counterId) {
        return jdbcTemplate.query("select serviceName from Service where serviceId in "
                                      + "(select serviceID from CounterService where counterId=?)", new Object[]{counterId}, new ServiceTypeRowMapper());
    }

    @Transactional(readOnly=true)
    public List<Long> findCounterTokens(long counterId) {
        return jdbcTemplate.queryForList("select DISTINCT tokenId from TokenStatus where counterId=? and inQ='true'", new Object[]{counterId}, Long.class);
    }

    @Transactional(readOnly=true)
    public List<CounterDetails> getAvailableCounters() {
        List<CounterDetails> counterDetailsList = new ArrayList<CounterDetails>();

        List<CounterDetails> counterIdList = jdbcTemplate.query("select * from Counter where active='true'", new CounterRowMapper());

        for(CounterDetails counterDetails: counterIdList) {
            counterDetails.setTokenIdList(findCounterTokens(counterDetails.getCounterId()));
            counterDetails.setServiceTypes(findCounterServices(counterDetails.getCounterId()));
            counterDetails.setActive(true);
            counterDetailsList.add(counterDetails);
        }

        return counterDetailsList;
    }
}

class  CounterRowMapper implements RowMapper<CounterDetails>
{
    @Override
    public CounterDetails mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        CounterDetails counterDetails = new CounterDetails();
        counterDetails.setCounterId(rs.getInt("counterId"));
        counterDetails.setActive(rs.getBoolean("active"));
        counterDetails.setEmployeeId(rs.getLong("employeeId"));
        counterDetails.setCounterType(CounterType.valueOf(rs.getString("counterType")));
        return counterDetails;
    }
}

class ServiceTypeRowMapper implements RowMapper<ServiceType>
{
    @Override
    public ServiceType mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        String serviceName = rs.getString("serviceName");

        ServiceType serviceType = ServiceType.valueOf(serviceName);
        return serviceType;
    }
}
