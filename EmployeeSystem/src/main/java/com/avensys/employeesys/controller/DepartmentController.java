package com.avensys.employeesys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avensys.employeesys.model.Department;
import com.avensys.employeesys.model.Employee;
import com.avensys.employeesys.service.DepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/departments")
@CrossOrigin(origins="http://localhost:3000")
public class DepartmentController {

	
	@Autowired
	private DepartmentService departmentService;
	

	public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}
	
	// API ///////
	

	// GET ALL
	@GetMapping
	public List<Department> getAllDepartment(){
		return departmentService.getAllDepartments();
	}
	
	// GET BY ID
	@GetMapping("{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable("id")long departmentId){
		return new ResponseEntity<Department>(departmentService.getDepartmentById(departmentId), HttpStatus.OK);
	}
	
	// GET LIST OF EMPLOYEES BY DEPARTMENT
	@GetMapping("{id}/employees")
	public List<Employee> getEmployeedsByDepartment(@PathVariable("id")long departmentId){
		return departmentService.getEmployeesByDepartment(departmentId);
	}
	
	// ADD
		@PostMapping 							
		public ResponseEntity<Department> addDepartment(@Valid @RequestBody Department department){    
			return new ResponseEntity<Department>(departmentService.addDepartment(department), HttpStatus.CREATED);
		}
	
	// UPDATE
	@PutMapping("{id}")		
		public ResponseEntity<Department> updateDepartment(@PathVariable("id")long departmentId, @RequestBody Department department){
			return new ResponseEntity<Department>(departmentService.updateDepartment(department, departmentId), HttpStatus.OK);
		}
	
	// DELETE
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteDepartment(@PathVariable("id")long departmentId){
		departmentService.deleteDepartment(departmentId);
		return new ResponseEntity<String>("Department deleted", HttpStatus.OK);
	}
}
