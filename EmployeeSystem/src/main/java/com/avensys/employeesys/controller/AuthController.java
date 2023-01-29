package com.avensys.employeesys.controller;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avensys.employeesys.dto.LoginDto;
import com.avensys.employeesys.dto.RegisterDto;
import com.avensys.employeesys.model.Employee;
import com.avensys.employeesys.model.Role;
import com.avensys.employeesys.repo.EmployeeRepo;
import com.avensys.employeesys.repo.RoleRepo;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private AuthenticationManager authenticationManager;
	private EmployeeRepo employeeRepository;
	private RoleRepo roleRepository;
	private PasswordEncoder passwordEncoder;
	
	public AuthController(AuthenticationManager authenticationManager, EmployeeRepo employeeRepository,
			RoleRepo roleRepository, PasswordEncoder passwordEncoder) {
		super();
		this.authenticationManager = authenticationManager;
		this.employeeRepository = employeeRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@PostMapping("register")
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
		if(employeeRepository.existsByEmail(registerDto.getEmail())) {
			return new ResponseEntity<>("Email is taken", HttpStatus.BAD_REQUEST);
		} else {
		
		Employee employee = new Employee();
		employee.setEmail(registerDto.getEmail());
		employee.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		
		Role roles = roleRepository.findByName("USER").get();
		employee.setRoles(Collections.singletonList(roles));
		
		employeeRepository.save(employee);
		
		return new ResponseEntity<>("User Registration Success!", HttpStatus.OK);
		}
	}
	
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(),
                loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>("Great!!!", HttpStatus.OK);
    }

	
}
