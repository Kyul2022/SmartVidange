package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;

@Service
public class CustomUserDetails implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Users loadUserByUsername(String name) throws UsernameNotFoundException {
	    Users user = userRepository.findUserByName(name);
	    if(user == null){
	        throw new UsernameNotFoundException("No user found with email");
	    }
	    return user;
	}
	

@Bean
public PasswordEncoder passwordEncoder() {
	return NoOpPasswordEncoder.getInstance();
}
}

