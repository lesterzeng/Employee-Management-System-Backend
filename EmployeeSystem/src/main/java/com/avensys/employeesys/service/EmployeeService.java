package com.avensys.employeesys.service;

import java.util.List;

import com.avensys.employeesys.model.Employee;

public interface EmployeeService {
	Employee addEmployee( Employee employee);
	
	List<Employee> getAllEmployees();
	
	Employee getEmployeeById(long id);
	
	Employee updateEmployee(Employee employee, long id);
	
	void deleteEmployee(long id);


}
