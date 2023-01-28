package com.avensys.employeesys.service.impl;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class EmployeeAuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {

		return Optional.ofNullable("Lester").filter(s->!s.isEmpty());
	}

}
