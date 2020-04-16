package com.realestate.service;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import com.realestate.security.UserDetailsImpl;

public class AuditorAwareImpl implements AuditorAware<String> {
	@Override
	public Optional<String> getCurrentAuditor() {
		UserDetailsImpl details = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return Optional.ofNullable(details.getUsername());
		//return Optional.ofNullable("Ruchi");
		//replace with below code after integration with login
		// Can use Spring Security to return currently logged in user
		
		
        //return Optional.ofNullable(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
		
	}
	
	
}
