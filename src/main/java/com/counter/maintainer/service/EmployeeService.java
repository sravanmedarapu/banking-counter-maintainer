package com.counter.maintainer.service;

import com.counter.maintainer.data.contracts.EmployeeRole;

public interface EmployeeService {
    EmployeeRole getEmployeeRole(Long empId);
}
