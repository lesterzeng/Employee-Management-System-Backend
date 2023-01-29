package com.avensys.employeesys.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avensys.employeesys.model.Employee;

// Spring JPA already added @Repository and @ Transactional under SimpleJpaRepository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	
	Optional<Employee> findByEmail(String email);
	Boolean existsByEmail(String email);
}
