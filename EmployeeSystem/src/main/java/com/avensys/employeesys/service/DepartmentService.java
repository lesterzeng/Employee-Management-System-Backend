package com.avensys.employeesys.service;

import java.util.List;

import com.avensys.employeesys.model.Department;
import com.avensys.employeesys.model.Employee;

public interface DepartmentService {

	List<Department> getAllDepartments();
	
	Department getDepartmentById(long id);
	
	List<Employee> getEmployeesByDepartment(long id);

	Department addDepartment(Department department);

	Department updateDepartment(Department department, long id);

	void deleteDepartment(long departmentId);
}
