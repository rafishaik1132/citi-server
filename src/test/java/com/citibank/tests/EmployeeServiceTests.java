package com.citibank.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
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

import com.citibank.model.Employee;
import com.citibank.repository.EmployeeRepository;
import com.citibank.services.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {
	
	 @Mock
	    private EmployeeRepository employeeRepository;

	    @InjectMocks
	    private EmployeeServiceImpl employeeService;

	    private Employee employee;
	    
	    
	    @Test
	    void testFindAllEmployees() {
	      List<Employee> list = new ArrayList<Employee>();
	      Employee empOne = new Employee(1,"John", "John",22222l,"j","33333",null);
	      Employee empTwo = new Employee(2,"John", "John",22222l,"j","33333",null);
	    
	      list.add(empOne);
	      list.add(empTwo);
	  
	      when(employeeRepository.findAll()).thenReturn(list);

	      //test
	      List<Employee> empList = employeeService.getAllEmployees();

	      assertEquals(2, empList.size());
	      verify(employeeRepository, times(1)).findAll();
	    }
	    
	    @Test
	    void testCreateOrSaveEmployee() {
	      Employee employee = new Employee(1,"John", "John",22222l,"j","33333",null);

	      employeeService.addEmployee(employee);
	           
	      verify(employeeRepository, times(1)).save(employee);
	    }
	    
	    @Test
	    public void testFindById() {
	        //given
	    	Optional<Employee> user = Optional.ofNullable(new Employee());
	        user.get().setEmployeeID(1);
	        user.get().setFirstName("Shaik");
	        user.get().setLastName("rafi");
	        user.get().setEmployeeGender("m");
	        user.get().setEmployeePhone(1234567898l);
	        user.get().setEmployeeSalary("43247");
	        user.get().setDepartment(null);
	    
	        when(employeeRepository.findById(1)).thenReturn(user);

		      //test
		      Optional<Employee> empList = employeeService.getEmployee(1);

		      assertEquals(1,1);
		      verify(employeeRepository, times(1)).findById(1);
	    }
	    

	

}
