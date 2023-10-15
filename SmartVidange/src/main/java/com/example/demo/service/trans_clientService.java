package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.trans_client;
import com.example.demo.repository.trans_clientRepository;

@Service
public class trans_clientService {

	@Autowired
	private trans_clientRepository transRepo;
	
	public trans_client findById(int id) {
		
		return transRepo.findByTransId(id);
	}
	
	public trans_client save(trans_client tr) {
		
		return transRepo.save(tr);
	}
	
	public Iterable<trans_client> findByStatus(String str) {
		
		return transRepo.findByStatus(str);
	}
}
