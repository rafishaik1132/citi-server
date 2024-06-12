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

import com.citibank.model.Department;
import com.citibank.model.Employee;
import com.citibank.repository.DepartmentRepository;
import com.citibank.services.DepartmentService;

@RequestMapping("/auth")
@RestController
@CrossOrigin(origins="http://localhost:4200")
public class DepartmentController {
	
static final Logger logger  = LogManager.getLogger(DepartmentController.class.getName());
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private DepartmentRepository departmentRepo;
	
	// displaying list of all department
		
		
		@GetMapping("/departments")
		public ResponseEntity<List<Department>> getAllDepartments(){
			
			try {
				List<Department> departments = new ArrayList<Department>();
				if (departments == null)
					departmentService.getAllDepartments().forEach(departments::add);

				if (departments.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}

				return new ResponseEntity<>(departments, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		
		
		@GetMapping("/departments/{id}")
		public ResponseEntity<Department> getDepartment(@PathVariable("id") int id) {
			Optional<Department> departmentData = departmentService.getDepartment(id);

			if (departmentData.isPresent()) {
				return new ResponseEntity<>(departmentData.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	
		
		@PostMapping("/departments")
		public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
			try {
				Department departmentData =departmentService.addDepartment(department);
				return new ResponseEntity<>(departmentData, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		@PutMapping("/departments/{id}")
		public ResponseEntity<Department> updateDepartment(@PathVariable("id") int id, @RequestBody Department department) {
			Optional<Department> departmentData = departmentRepo.findById(id);

			if (departmentData.isPresent()) {
				Department updatedData = departmentData.get();
				updatedData.setDepartment_Name(department.getDepartment_Name());
				updatedData.setShort_Name(department.getShort_Name());
				
				return new ResponseEntity<>(departmentRepo.save(updatedData), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		// deleting all department
		@DeleteMapping("/departments")
		public void deleteAllDepartments(){
			departmentService.deleteAllDepartment();
		}

		
		
		@DeleteMapping("departments/{id}")
		public ResponseEntity<HttpStatus> deleteDepartmentById(@PathVariable("id") int id) {
			try {
				departmentService.deleteDepartmentByID(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

}
