package com.avensys.employeesys.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {


	public Department(long departmentId, String departmentName, List<Employee> employee) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.employee = employee;
	}
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long departmentId;
	
	@Column(name = "department_name")
	private String departmentName;
	
	
	@OneToMany(mappedBy="department")
	private List<Employee> employee;

	public long getId() {
		return departmentId;
	}

	public void setId(long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@JsonIgnore
	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + ", employee=" + employee + "]";
	}
	
	public Department() {};
}
