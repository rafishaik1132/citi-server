package com.citibank.tests;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.citibank.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(SecurityConfigTest.class)
public class EmployeeControllerTests {
	
	
	@Autowired
	  private MockMvc mvc;
	
	@Test
	public void getAllEmployee() throws Exception 
	{
	  mvc.perform(MockMvcRequestBuilders
	  			.get("/api/employees")
	  			.accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$[0].employeeID").isNotEmpty());
	}
	
	@Test
	public void createEmployeeAPI() throws Exception 
	{
	  mvc.perform( MockMvcRequestBuilders
		      .post("/api/employees")
		      .content(asJsonString(new Employee(1,"John", "John",22222l,"j","33333",null)))
		      .contentType(MediaType.APPLICATION_JSON)
		      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isCreated());
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	
	@Test
	public void deleteEmployee() throws Exception 
	{
	  mvc.perform(MockMvcRequestBuilders
	  			.delete("/api/employees/152")
	  			.accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk());
	}
	
	@Test
	public void getEmployeeById() throws Exception 
	{
	  mvc.perform(MockMvcRequestBuilders
	  			.get("/api/employees/302")
	  			.accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.employeeID").isNotEmpty());
	}
  
  

}
