package com.citibank.repository;

import org.springframework.data.repository.CrudRepository;

import com.citibank.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
