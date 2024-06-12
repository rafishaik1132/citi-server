package com.citibank.repository;

import org.springframework.data.repository.CrudRepository;

import com.citibank.model.Department;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {

}
