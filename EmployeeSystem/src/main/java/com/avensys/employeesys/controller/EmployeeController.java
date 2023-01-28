package com.avensys.employeesys.controller;

import java.util.List;

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

import com.avensys.employeesys.model.Employee;
import com.avensys.employeesys.service.EmployeeService;

import jakarta.validation.Valid;

@RestController // <-- Combined @ResponseBody & @Controller
@RequestMapping("/employees")
@CrossOrigin(origins="http://localhost:3000")
public class EmployeeController {
	
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	// APIs ///////
	
	// ADD
	@PostMapping 							// @RequestBody to retrieve request's body and convert it to Java Object
	public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee){    // Pass status as CREATED
		return new ResponseEntity<Employee>(employeeService.addEmployee(employee), HttpStatus.CREATED);
	}
	
	// GET ALL
	@GetMapping
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
	// GET BY ID
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id")long employeeId){
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
	}

	// UPDATE
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id")long employeeId, @RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, employeeId), HttpStatus.OK);
	}
	
	// DELETE
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id")long employeeId){
		employeeService.deleteEmployee(employeeId);
		return new ResponseEntity<String>("Employee deleted", HttpStatus.OK);
	}

}
