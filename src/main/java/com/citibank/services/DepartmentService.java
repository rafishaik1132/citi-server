package com.citibank.services;

import java.util.List;
import java.util.Optional;

import com.citibank.model.Department;

public interface DepartmentService {

	List<Department> getAllDepartments();

	Optional<Department> getDepartment(int id);

	Department addDepartment(Department department);

	void updateDepartment(Department d, int id);


	void deleteDepartmentByID(int id);


}
