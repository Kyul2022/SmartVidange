package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Poubelle;
import com.example.demo.repository.PoubelleRepository;

@Service
public class PoubelleService {

	@Autowired
	private PoubelleRepository poubelleRepo;
	
	public Poubelle savePoubelle(Poubelle Poubelle) {
		Poubelle = poubelleRepo.save(Poubelle);
		return Poubelle;
	}
	
	public Poubelle findByNumSerie(int id) {
		return poubelleRepo.findByNumSerie(id);
	}
	
	public Iterable<Poubelle> getPoubellesAll(){
		return poubelleRepo.findAll();
	}
}
