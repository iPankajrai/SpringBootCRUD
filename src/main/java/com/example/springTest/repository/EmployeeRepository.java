package com.example.springTest.repository;

import com.example.springTest.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//To make communication with database related queries
// Do not have to use @Repository for this interface as jpaRepository already used that
public interface EmployeeRepository extends JpaRepository <Employee, Long >{
    //@Query => used for custom query
    //Dto
    @Query
    List<dto> getAllEmployees();
}
