package com.counter.maintainer.repository;

import com.counter.maintainer.data.contracts.Employee;
import com.counter.maintainer.data.contracts.EmployeeRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public EmployeeRole getEmployeeRole(Long employeeId) {
        return EmployeeRole.valueOf(jdbcTemplate.queryForObject("select role from employee where employeeId=?",new Object[]{employeeId}, String.class));
    }
}
