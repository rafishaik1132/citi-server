package com.citibank.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.citibank.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
