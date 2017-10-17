package com.astontech.hr.services.impl;

import com.astontech.hr.domain.Employee;
import com.astontech.hr.repositories.EmployeeRepository;
import com.astontech.hr.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Override
    public Iterable<Employee> listAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findOne(id);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Iterable<Employee> saveEmployeeList(Iterable<Employee> employeeIterable) {
        return employeeRepository.save(employeeIterable);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRepository.delete(id);
    }

    @Autowired
    private EmployeeRepository employeeRepository;
}
