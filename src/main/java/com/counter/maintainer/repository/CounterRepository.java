package com.counter.maintainer.repository;

import com.counter.maintainer.data.contracts.CounterDetails;
import com.counter.maintainer.data.contracts.ServiceType;
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
public class CounterRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly=true)
    public List<CounterDetails> getCountersStatus() {
        List<CounterDetails> counterDetailsList = jdbcTemplate.query("select * from counterStatus where inQ='true'", new CounterRowMapper());
        for(CounterDetails counterDetails : counterDetailsList) {
            counterDetails.setServiceTypes(findCounterServices(counterDetails.getCounterId()));
            counterDetails.setTokenIdList(findCounterTokens(counterDetails.getCounterId()));
        }
        return counterDetailsList;
    }

    @Transactional(readOnly=true)
    public List<ServiceType> findCounterServices(long counterId) {
        return jdbcTemplate.query("select DISTINCT name from serviceTypes where serviceId in "
                                      + "(select serviceID from counterServices where counterId=? and active='true')", new Object[]{counterId}, new ServiceTypeRowMapper());
    }

    @Transactional(readOnly=true)
    public List<Long> findCounterTokens(long counterId) {
        return jdbcTemplate.queryForList("select DISTINCT tokenId from counterStatus where counterId=? and inQ='true'", new Object[]{counterId}, Long.class);
    }

    @Transactional(readOnly=true)
    public List<CounterDetails> getAvailableCounters(ServiceType serviceType) {
        List<CounterDetails> counterDetailsList = new ArrayList<CounterDetails>();

        List<Long> counterIdList = jdbcTemplate.queryForList("select DISTINCT counterId from counterServices where serviceID in "
                                      + "(select DISTINCT serviceId from serviceTypes where name=?) and active='true'", new Object[]{serviceType.name()}, Long.class);

        for(Long counterId: counterIdList) {
            CounterDetails counterDetails = new CounterDetails();
            counterDetails.setTokenIdList(this.findCounterTokens(counterId));
            counterDetails.setActive(true);
            counterDetails.setCounterId(counterId);
            counterDetailsList.add(counterDetails);
        }

        return counterDetailsList;
    }

    @Transactional(readOnly=true)
    public List<CounterDetails> getAvailableCounters() {
        List<CounterDetails> counterDetailsList = new ArrayList<CounterDetails>();

        List<Long> counterIdList = jdbcTemplate.queryForList("select DISTINCT counterId from counterServices where active='true'", Long.class);

        for(Long counterId: counterIdList) {
            CounterDetails counterDetails = new CounterDetails();
            counterDetails.setTokenIdList(this.findCounterTokens(counterId));
            counterDetails.setActive(true);
            counterDetails.setCounterId(counterId);
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
        return counterDetails;
    }
}

class ServiceTypeRowMapper implements RowMapper<ServiceType>
{
    @Override
    public ServiceType mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        String serviceName = rs.getString("name");

        ServiceType serviceType = ServiceType.valueOf(serviceName);
        return serviceType;
    }
}
