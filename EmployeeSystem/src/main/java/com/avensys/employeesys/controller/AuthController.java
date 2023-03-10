package com.avensys.employeesys.controller;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avensys.employeesys.dto.AuthResponseDto;
import com.avensys.employeesys.dto.LoginDto;
import com.avensys.employeesys.dto.RegisterDto;
import com.avensys.employeesys.model.Employee;
import com.avensys.employeesys.model.Role;
import com.avensys.employeesys.repo.EmployeeRepo;
import com.avensys.employeesys.repo.RoleRepo;
import com.avensys.employeesys.security.JWTGenerator;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins="http://localhost:3000")
public class AuthController {

	private AuthenticationManager authenticationManager;
	private EmployeeRepo employeeRepository;
	private RoleRepo roleRepository;
	private PasswordEncoder passwordEncoder;
	private JWTGenerator jwtGenerator;
	
	
	public AuthController(AuthenticationManager authenticationManager, EmployeeRepo employeeRepository,
			RoleRepo roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
		super();
		this.authenticationManager = authenticationManager;
		this.employeeRepository = employeeRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtGenerator = jwtGenerator;
	}
	
	@PostMapping("register")
	public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterDto registerDto){
//		if(employeeRepository.existsByEmail(registerDto.getEmail())) {
//			return new ResponseEntity<>(new AuthResponseDto("Email is taken", null) HttpStatus.BAD_REQUEST);
//		} else {
		
		Employee employee = new Employee();
		employee.setFirstName(registerDto.getFirstName());
		employee.setLastName(registerDto.getLastName());
		employee.setEmail(registerDto.getEmail());
		employee.setDepartment(registerDto.getDepartment());
		employee.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		
		Role roles = roleRepository.findByName("USER").get();
		employee.setRoles(Collections.singletonList(roles));
		
		employeeRepository.save(employee);
		
		return new ResponseEntity<>(new AuthResponseDto("User Registration Success!", null), HttpStatus.OK);
//		}
	}
	
    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(),
                loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }

	
}
