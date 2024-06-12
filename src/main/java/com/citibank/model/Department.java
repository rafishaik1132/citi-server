package com.citibank.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="department")
public class Department {
	
	@Id
	@Column(name="department_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int department_ID;
	
	@Column(name="short_name")
	private String short_Name;
	
	@Column(name="department_name")
	private String department_Name;
	
	public Department() {
	
	}
	
	public Department(int departmentID){
		super();
		this.department_ID = departmentID;
	}
	
	public Department(int departmentID, String short_Name, String department_Name) {
		super();
		this.department_ID = departmentID;
		this.short_Name = short_Name;
		this.department_Name = department_Name;
	}

	public int getDepartment_ID() {
		return department_ID;
	}

	public void setDepartment_ID(int department_ID) {
		this.department_ID = department_ID;
	}

	public String getShort_Name() {
		return short_Name;
	}

	public void setShort_Name(String short_Name) {
		this.short_Name = short_Name;
	}

	public String getDepartment_Name() {
		return department_Name;
	}

	public void setDepartment_Name(String department_Name) {
		this.department_Name = department_Name;
	}

}
