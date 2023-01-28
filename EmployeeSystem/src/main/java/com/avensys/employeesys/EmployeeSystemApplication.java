package com.avensys.employeesys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.avensys.employeesys.service.impl.EmployeeAuditorAwareImpl;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef="auditorAware")
public class EmployeeSystemApplication {

	@Bean
	public AuditorAware<String> auditorAware(){
		return new EmployeeAuditorAwareImpl();
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(EmployeeSystemApplication.class, args);
		System.out.println("Application started");
	}

}
