package com.citibank.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citibank.model.Employee;
import com.citibank.model.User;
import com.citibank.repository.EmployeeRepository;
import com.citibank.services.EmployeeService;

@RequestMapping("/api")
@RestController
@CrossOrigin(origins="http://localhost:4200")
public class EmployeeController {
	
	 Logger logger  = LogManager.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	// displaying list of all employees
	@GetMapping("/employees")
	public ResponseEntity<?> getAllEmployee(){
		logger.info("inside getAllEmployess::{}");
		try {
			List<Employee> employees = new ArrayList<Employee>();
			if (employees == null)
				logger.info("getAllEmployees::");
				employeeService.getAllEmployees().forEach(employees::add);

			if (employees.isEmpty()) {
				return new ResponseEntity<>("Could not get employees,because data not present",HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Could not get employees,try again"+e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@GetMapping("/employees/{id}")
	public ResponseEntity<?> getEmployee(@PathVariable("id") int id) {
		Optional<Employee> employeeData = employeeService.getEmployee(id);
		logger.info("get employeee data"+employeeData);

		if (employeeData.isPresent()) {
			return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
		} else {
			return ResponseEntity
		            .status(HttpStatus.NOT_FOUND)
		            .body("Could not get employee due to data not present for id"+id);

		}
	}
	
	@PostMapping("/employees")
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
		try {
			Employee employeeData =employeeService.addEmployee(employee) ;
			logger.info("add employee data"+employeeData);
			return new ResponseEntity<>(employeeData, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Could not add the employee"+e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
		Optional<Employee> employeeData = employeeRepository.findById(id);
		logger.info("employee data"+employeeData);

		if (employeeData.isPresent()) {
			logger.info("update employee data"+employeeData);
			Employee updatedData = employeeData.get();
			updatedData.setFirstName(employee.getFirstName());
			updatedData.setLastName(employee.getLastName());
			updatedData.setEmployeePhone(employee.getEmployeePhone());
			updatedData.setEmployeeGender(employee.getEmployeeGender());
			updatedData.setEmployeeSalary(employee.getEmployeeSalary());
			updatedData.setDepartment(employee.getDepartment());
			return new ResponseEntity<>(employeeRepository.save(updatedData), HttpStatus.OK);
		} else {
			return ResponseEntity
		            .status(HttpStatus.NOT_FOUND)
		            .body("Employee does not exists for id "+id);

		}
	}
	
	
	@DeleteMapping("employees/{id}")
	public ResponseEntity<?> deleteEmployeeById(@PathVariable("id") int id) {
		logger.info("delete employee:{}"+id);
		Optional<Employee> employeeData = employeeService.getEmployee(id);
		if (!employeeData.isEmpty()) {		
		try {
			employeeService.deleteEmployeeByID(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		}else
		{
			return ResponseEntity
		            .status(HttpStatus.NOT_FOUND)
		            .body("Could not delete  employee,because data not present");
			
		}
	}


}
