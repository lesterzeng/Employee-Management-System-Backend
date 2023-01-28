package com.avensys.employeesys.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.avensys.employeesys.exception.ResourceNotFoundException;
import com.avensys.employeesys.model.Department;
import com.avensys.employeesys.model.Employee;
import com.avensys.employeesys.repo.DepartmentRepo;
import com.avensys.employeesys.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	
	private DepartmentRepo departmentRepository;

	public DepartmentServiceImpl(DepartmentRepo departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;
	}

	//GET ALL
	@Override
	public List<Department> getAllDepartments(){
		return departmentRepository.findAll();
	}
	
	//GET BY ID
	@Override
	public Department getDepartmentById(long id) {
		Optional<Department> department = departmentRepository.findById(id);
		if(department.isPresent()) {
			return department.get();
		} else {
		throw new ResourceNotFoundException("Department", "Id", department);
		}
	}
	
	//GET EMPLOYEES BY DEPARTMENT
	@Override
	public List<Employee> getEmployeesByDepartment(long id){
		return departmentRepository.getEmployeeByDepartment(id);
	}
	
	// ADD
	@Override
	public Department addDepartment(Department department){
		return departmentRepository.save(department);
	}
	
	// UPDATE
	@Override
	public Department updateDepartment(Department department, long id) {
			try {
			Department currentDepartment = departmentRepository.findById(id).get();
			currentDepartment.setDepartmentName(department.getDepartmentName());
			departmentRepository.save(currentDepartment);
			return currentDepartment;
			} catch (NoSuchElementException e){
				throw new ResourceNotFoundException("Department", "Id", department);
			}
		}
		
	// DELETE
	@Override
	public void deleteDepartment(long id) {
			Optional<Department> currentDepartment = departmentRepository.findById(id);
			if(currentDepartment.isEmpty()) {
				throw new ResourceNotFoundException("Department", "Id", currentDepartment);
			} else
				departmentRepository.deleteById(id);
		}
	
}
