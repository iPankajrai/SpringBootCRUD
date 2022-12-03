package com.example.springTest.service;

import com.example.springTest.model.Employee;

import java.util.List;

public interface EmployeeService {
    //To create employees
    Employee saveEmployee(Employee employee);

    //To get all employees
    List<Employee> getAllEmployees();

    //To get employee with particular id
    Employee getEmployeeById(long id);

    // To update employee
    Employee updateEmployee(Employee employee, long id);

    // To delete employee
    void deleteEmployee(long id);

}
