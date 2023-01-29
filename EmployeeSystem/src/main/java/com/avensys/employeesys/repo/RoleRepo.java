package com.avensys.employeesys.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avensys.employeesys.model.Role;

// Spring JPA already added @Repository and @ Transactional under SimpleJpaRepository
public interface RoleRepo extends JpaRepository<Role, Long> {
	
	Optional<Role> findByName(String name);

}
