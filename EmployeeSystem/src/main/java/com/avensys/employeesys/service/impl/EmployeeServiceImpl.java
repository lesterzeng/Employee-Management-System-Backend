package com.avensys.employeesys.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.avensys.employeesys.exception.ResourceNotFoundException;
import com.avensys.employeesys.model.Employee;
import com.avensys.employeesys.repo.EmployeeRepo;
import com.avensys.employeesys.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	
	
	private EmployeeRepo employeeRepository;
	
	// No need @Autowired because Spring Boot will auto inject dependencies when it's only one constructor in the class
	// From version 4.3 onwards
	public EmployeeServiceImpl(EmployeeRepo employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}



	// ADD
	@Override
	public Employee addEmployee(Employee employee){
		return employeeRepository.save(employee);
	}



	// GET ALL
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}


	// GET BY ID
	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if(employee.isPresent()) {
		return employee.get();
		} else {
			throw new ResourceNotFoundException("Employee", "Id", employee);
		}
	}



	// UPDATE
	@Override
	public Employee updateEmployee(Employee employee, long id) {
		try {
		Employee currentEmployee = employeeRepository.findById(id).get();
		currentEmployee.setFirstName(employee.getFirstName());
		currentEmployee.setLastName(employee.getLastName());
		currentEmployee.setEmail(employee.getEmail());
		currentEmployee.setDepartment(employee.getDepartment());
		employeeRepository.save(currentEmployee);
		return currentEmployee;
		} catch (NoSuchElementException e){
			throw new ResourceNotFoundException("Employee", "Id", employee);
		}
	}
		
//		if (currentEmployee==null) {
//			throw new ResourceNotFoundException("Employee", "Id", employee);
//		} else {
//			currentEmployee.setFirstName(employee.getFirstName());
//			currentEmployee.setLastName(employee.getLastName());
//			currentEmployee.setEmail(employee.getEmail());
//			employeeRepository.save(currentEmployee);
//			return currentEmployee;
//		}




	// DELETE
	@Override
	public void deleteEmployee(long id) {
		Optional<Employee> currentEmployee = employeeRepository.findById(id);
		if(currentEmployee.isEmpty()) {
			throw new ResourceNotFoundException("Employee", "Id", currentEmployee);
		} else
		employeeRepository.deleteById(id);
	}


	
	

}
