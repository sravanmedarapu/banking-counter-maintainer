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

    public List<Employee> getEmployees() {
        //TODO: yet to implement
        List<Employee> employeeList = new ArrayList<Employee>();
        Employee employee = new Employee();
        employee.setDesignation(EmployeeRole.MANAGER);
        employee.setEmployeeId(1);
        employee.setName("ABC");

        Employee employee2 = new Employee();
        employee.setDesignation(EmployeeRole.OPERATOR);
        employee.setEmployeeId(2);
        employee.setName("provider 1");

        employeeList.add(employee);
        employeeList.add(employee2);

        return employeeList;
    }

    public Boolean validatePermissions(Long employeeId) {
        //TODO:
        return true;
    }
}
