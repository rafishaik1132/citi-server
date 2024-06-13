package com.citibank.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.citibank.model.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer> {

}
