package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Agent;
import com.example.demo.model.Quarter;
import com.example.demo.repository.AgentRepository;

@Service
public class AgentService implements UserDetailsService {

	@Autowired
	private AgentRepository agentRepo;
	
	public Agent findByQuarter(Quarter quarter) {
		return agentRepo.findByQuarter(quarter);
	}
	
	public Agent findByMatricule(String matricule) {
		return agentRepo.findByMatricule(matricule);
	}
		
	@Override
	public Agent loadUserByUsername(String name) throws UsernameNotFoundException {
		 Agent agent = agentRepo.findByMatricule(name);
		    if(agent == null){
		        throw new UsernameNotFoundException("No user found with email");
		    }
		    return agent;
		}
		

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	}


