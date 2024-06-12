package com.citibank.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citibank.model.Employee;
import com.citibank.repository.EmployeeRepository;
import com.citibank.services.EmployeeService;

@RequestMapping("/auth")
@RestController
@CrossOrigin(origins="http://localhost:4200")
public class EmployeeController {
	
	static final Logger logger  = LogManager.getLogger(EmployeeController.class.getName());

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	// displaying list of all employees
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployee(){
		
		try {
			List<Employee> employees = new ArrayList<Employee>();
			if (employees == null)
				logger.info("getAllEmployees::");
				employeeService.getAllEmployees().forEach(employees::add);

			if (employees.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") int id) {
		Optional<Employee> employeeData = employeeService.getEmployee(id);

		if (employeeData.isPresent()) {
			return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	

	@PostMapping("/employees")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		try {
			Employee employeeData =employeeService.addEmployee(employee) ;
			return new ResponseEntity<>(employeeData, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
		Optional<Employee> employeeData = employeeRepository.findById(id);

		if (employeeData.isPresent()) {
			Employee updatedData = employeeData.get();
			updatedData.setFirstName(employee.getFirstName());
			updatedData.setLastName(employee.getLastName());
			updatedData.setEmployeePhone(employee.getEmployeePhone());
			updatedData.setEmployeeGender(employee.getEmployeeGender());
			updatedData.setEmployeeSalary(employee.getEmployeeSalary());
			updatedData.setDepartment(employee.getDepartment());
			return new ResponseEntity<>(employeeRepository.save(updatedData), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("employees/{id}")
	public ResponseEntity<HttpStatus> deleteEmployeeById(@PathVariable("id") int id) {
		try {
			employeeService.deleteEmployeeByID(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
