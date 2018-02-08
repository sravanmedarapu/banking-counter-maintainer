package com.counter.maintainer.service;

import com.counter.maintainer.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Boolean validateCompleteAndCancelPermission(Long empId) {
        return employeeRepository.validatePermissions(empId);
    }
}
