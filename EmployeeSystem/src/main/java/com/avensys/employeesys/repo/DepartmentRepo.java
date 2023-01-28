package com.avensys.employeesys.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.avensys.employeesys.model.Department;
import com.avensys.employeesys.model.Employee;


public interface DepartmentRepo extends JpaRepository<Department, Long>{
	
	@Query("select employee from Department d where d.id=?1")
	List<Employee> getEmployeeByDepartment(long id);



}
