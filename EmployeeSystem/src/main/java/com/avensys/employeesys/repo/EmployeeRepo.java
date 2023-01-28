package com.avensys.employeesys.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avensys.employeesys.model.Employee;

// Spring JPA already added @Repository and @ Transactional under SimpleJpaRepository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}
