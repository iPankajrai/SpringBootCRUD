package com.example.springTest.service.impl;

import com.example.springTest.exception.ResourceNotFoundException;
import com.example.springTest.model.Employee;
import com.example.springTest.repository.EmployeeRepository;
import com.example.springTest.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
// Don't have to use @Transaction annotation here as spring data jpa internally provides @transaction to all its methods
public class EmployeeServiceImpl implements EmployeeService {

    // constructor based dependency injection
    //will have to use repository
    private EmployeeRepository employeeRepository;

    // Don't have to use @AutoWired annotation here, because whenever spring boot finds spring bean it has only one constructor, then spring boot will automatically autowire its dependency
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
        /*
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            return employee.get();
        }else{
            throw new ResourceNotFoundException("Employee", "Id", id);
        }
        */
        // by lambda expression
        return employeeRepository.findById(id).orElseThrow(() ->
                                        new ResourceNotFoundException("Employee", "Id", id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        // check if emplyee with given id exists in db or not
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee", "Id", id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());

        // save existing employee to db
        employeeRepository.save(existingEmployee);

        return existingEmployee;

    }

    @Override
    public void deleteEmployee(long id) {
        // check whether employee exists in database or not
        employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee", "Id", id));
        employeeRepository.deleteById(id);
    }
}
