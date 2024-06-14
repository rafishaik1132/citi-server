package com.citibank.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.citibank.model.Department;
import com.citibank.repository.DepartmentRepository;
import com.citibank.services.DepartmentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTests {
	
	    @Mock
	    private DepartmentRepository departmentRepository;

	    @InjectMocks
	    private DepartmentServiceImpl departmentService;

	    private Department department;
	    
	    
	    
	    @Test
	    void testFindAllDepartments() {
	      List<Department> list = new ArrayList<Department>();
	      Department depOne = new Department(1,"IT", "Information Technology");
	      Department depTwo = new Department(2,"HR", "Human Resources");
	    
	      list.add(depOne);
	      list.add(depTwo);
	  
	      when(departmentRepository.findAll()).thenReturn(list);

	      //test
	      List<Department> depList = departmentService.getAllDepartments();

	      assertEquals(2, depList.size());
	      verify(departmentRepository, times(1)).findAll();
	    }
	    
	    @Test
	    void testCreateOrSaveDepartment() {
	    	  Department depOne = new Department(1,"IT", "Information Technology");

	    	  departmentService.addDepartment(depOne);
	           
	      verify(departmentRepository, times(1)).save(depOne);
	    }
	    
	    @Test
	    public void testDepartmentById() {
	        //given
	    	 Optional<Department> depOne = Optional.of(new Department(1,"IT", "Information Technology"));
	    
	        when(departmentRepository.findById(1)).thenReturn(depOne);

		      //test
		      Optional<Department> empList = departmentService.getDepartment(1);

		      assertEquals(1,1);
		      verify(departmentRepository, times(1)).findById(1);
	    }
	    
	    
	    @Test
	    public void testDelete() {
	    	int id=1;
	    	departmentService.deleteDepartmentByID(id);
	  	   verify(departmentRepository,times(1)).deleteById(id);  	
	    }
	    
	    @Test
	    public void testupdateDepartment()
	    {
	    	  Department depOne = new Department(1,"IT", "Information Technology");
	    	  departmentService.updateDepartment(depOne, 1);
	    	  verify(departmentRepository,times(1)).save(depOne);
	    	
	    }
	    

}
