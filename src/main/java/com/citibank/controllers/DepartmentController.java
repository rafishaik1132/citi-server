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

@RequestMapping("/api")
@RestController
@CrossOrigin(origins="http://localhost:4200")
public class DepartmentController {
	
          Logger logger  = LogManager.getLogger(DepartmentController.class);
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private DepartmentRepository departmentRepo;
	
	// displaying list of all department
		
		
		@GetMapping("/departments")
		public ResponseEntity<?> getAllDepartments(){
			logger.info("inside getAllDepartments:{}");
			try {
				List<Department> departments = new ArrayList<Department>();
			
				if (departments == null || departments.isEmpty())
					departmentService.getAllDepartments().forEach(departments::add);

				if (departments.isEmpty()) {
					return new ResponseEntity<>(departments,HttpStatus.NO_CONTENT);
				}

				return new ResponseEntity<>(departments, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>("Could not get departments,try again"+e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		
		
		@GetMapping("/departments/{id}")
		public ResponseEntity<?> getDepartment(@PathVariable("id") int id) {
			Optional<Department> departmentData = departmentService.getDepartment(id);
			
			logger.info("department data"+departmentData);
			if (departmentData.isPresent()) {
				return new ResponseEntity<>(departmentData.get(), HttpStatus.OK);
			} else {
				return ResponseEntity
			            .status(HttpStatus.NOT_FOUND)
			            .body("Could not get department due to data not present for id"+id);
			}
		}
	
		
		@PostMapping("/departments")
		public ResponseEntity<?> addDepartment(@RequestBody Department department) {
			try {
				Department departmentData =departmentService.addDepartment(department);
				logger.info("add department response"+departmentData);
				return new ResponseEntity<>(departmentData, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>("Could not add the department"+e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		@PutMapping("/departments/{id}")
		public ResponseEntity<?> updateDepartment(@PathVariable("id") int id, @RequestBody Department department) {
			Optional<Department> departmentData = departmentRepo.findById(id);

			if (departmentData.isPresent()) {
				logger.info("inside update department");
				Department updatedData = departmentData.get();
				updatedData.setDepartment_Name(department.getDepartment_Name());
				updatedData.setShort_Name(department.getShort_Name());
				
				return new ResponseEntity<>(departmentRepo.save(updatedData), HttpStatus.OK);
			} else {
				return ResponseEntity
			            .status(HttpStatus.NOT_FOUND)
			            .body("Department does not exists for id "+id);
			}
		}
		
		
		@DeleteMapping("departments/{id}")
		public ResponseEntity<?> deleteDepartmentById(@PathVariable("id") int id) {
			logger.info("deleteDepartment:{}", id);
			Optional<Department> departmentData = departmentService.getDepartment(id);
			if (!departmentData.isEmpty()) {
			try {
				departmentService.deleteDepartmentByID(id);
				return new ResponseEntity<>("Department deleted successfully",HttpStatus.OK);
			} 
			catch (Exception e) {
				return new ResponseEntity<>("Could not delete the department,try again",HttpStatus.INTERNAL_SERVER_ERROR);
			}
			}else {
				return ResponseEntity
			            .status(HttpStatus.NOT_FOUND)
			            .body("Could not delete the department,because data not present");
			}
		}

}
