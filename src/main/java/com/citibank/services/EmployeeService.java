package com.citibank.services;

import java.util.List;
import java.util.Optional;

import com.citibank.model.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployees();

	Optional<Employee> getEmployee(int id);

	Employee addEmployee(Employee employee);

	void updateEmployee(Employee e, int id);


	void deleteEmployeeByID(int id);


}
