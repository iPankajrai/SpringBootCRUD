package com.example.springTest.controller;

import com.example.springTest.model.Employee;
import com.example.springTest.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    //inject employee service
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    //build create employee rest API
    // http://localhost:8080/api/employees/create
    @PostMapping("/create")
    // Here using post mapping annotation because this api should handle post http request
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        // Using ResponseEntity generic return type because this can contain all the response parts like headers, reponse and errors
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    //build Get all employee REST API
    // http://localhost:8080/api/employees
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    //build get employee by ID REST API
    // http://localhost:8080/api/employees/1
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    // build update Employee REST API
    // http://localhost:8080/api/employees/update/1
    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
    }

    //build delete employee REST API
    //http://localhost:8080/api/employees/delete/1
    // controller layer depends on service layer. so first we make changes in service layer.
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>("Employee deleted successfully!", HttpStatus.OK);

    }
}
