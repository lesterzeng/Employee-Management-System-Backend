package com.avensys.employeesys.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
      
import com.avensys.employeesys.model.Employee;
import com.avensys.employeesys.model.Role;
import com.avensys.employeesys.repo.EmployeeRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	private EmployeeRepo employeeRepository;
	
	
	public CustomUserDetailsService(EmployeeRepo employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}






	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = employeeRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
		return new User(employee.getEmail(), employee.getPassword(), mapRolesToAuthorities(employee.getRoles()));
	}

	 private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
	        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	    }

}
