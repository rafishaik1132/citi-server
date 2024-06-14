package com.citibank.model;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "employee")
@Builder
public class Employee {
	
	@Id
	@Column(name="employee_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int employeeID;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="employee_phone")
	private Long employeePhone; 
	
	@Column(name="gender")
	private String employeeGender;
	
	@Column(name="employee_salary")
	private String employeeSalary; 
	
	
	
	
	
	
	public Employee(int employeeID, String firstName, String lastName, Long employeePhone, String employeeGender,
			String employeeSalary, Department department) {
		super();
		this.employeeID = employeeID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.employeePhone = employeePhone;
		this.employeeGender = employeeGender;
		this.employeeSalary = employeeSalary;
		this.department = department;
	}

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name="department_id")
	private Department department;

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getEmployeePhone() {
		return employeePhone;
	}

	public void setEmployeePhone(Long employeePhone) {
		this.employeePhone = employeePhone;
	}

	public String getEmployeeGender() {
		return employeeGender;
	}

	public void setEmployeeGender(String employeeGender) {
		this.employeeGender = employeeGender;
	}

	public String getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(String employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public static Employee builder() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
