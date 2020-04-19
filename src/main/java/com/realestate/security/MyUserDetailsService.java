package com.realestate.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.realestate.dao.ReUserJPADao;
import com.realestate.model.ReUser;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private ReUserJPADao userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ReUser user = userRepo.findByUsername(username);
		if(user==null)
			throw new UsernameNotFoundException("User 404");
		return new UserDetailsImpl(user);
	}
	
	
}
