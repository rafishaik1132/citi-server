package com.citibank.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.citibank.controllers.DepartmentController;
import com.citibank.model.Department;
import com.citibank.repository.DepartmentRepository;


public class DepartmentControllerTests {

	@Mock
    private DepartmentRepository depRepo; // Assuming UserService is a service class used by UserController
    
    @InjectMocks
    private DepartmentController deptController;
	
	@Test
	 public void testGetUserById() {
	        // Arrange
	        int userId = 123; // Assuming this is the ID of the user to be retrieved
	        Optional<Department> mockUser = Optional.of(new Department(userId, "John Doe","test")); // Mock user object
	        Optional<Department> depOne = Optional.of(new Department(1,"IT", "Information Technology"));
		    
	        // Stubbing the behavior of userService.getUserById(userId) method
	        when(depRepo.findById(1)).thenReturn(depOne); // Stubbing a successful user retrieval
	        
	        // Act
	        ResponseEntity<Department> responseEntity = deptController.getDepartment(userId);
	        
	        // Assert
	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode()); // Verifying the status code
	        assertEquals(mockUser.get(), responseEntity.getBody()); // Verifying the returned user object
	    }
}
