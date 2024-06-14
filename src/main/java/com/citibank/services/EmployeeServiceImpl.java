package com.citibank.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citibank.model.Employee;
import com.citibank.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	// fetching all employees
	public List<Employee> getAllEmployees(){
		List<Employee> emps = (List<Employee>)employeeRepository.findAll(); 
		return emps;
	}
	
	// fetching employee by id
	public Optional<Employee> getEmployee(int id){
		return employeeRepository.findById(id);
	}
	
	// inserting employee
	public Employee addEmployee(Employee e) {
		return employeeRepository.save(e);
	}
	
	// updating employee by id
	public void updateEmployee(Employee emp, int id){
		if(id == emp.getEmployeeID()) {
			employeeRepository.save(emp);
		}
	}
	
	
	// deleting employee by id
	public void deleteEmployeeByID(int id){
		employeeRepository.deleteById(id);
	}
	

}
