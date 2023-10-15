package com.example.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Agent;
import com.example.demo.model.Poubelle;
import com.example.demo.model.Vidange;
import com.example.demo.repository.VidangeRepository;

@Service
public class VidangeService {

	@Autowired
	private VidangeRepository vidangeRepo;
	
	private Iterable<Vidange> vidanges;

	
	public Iterable<Vidange> findByVideur(Agent videur) {
		vidanges = this.vidangeRepo.findByVideur(videur);
		List<Vidange> list = new ArrayList<>();
		Iterator<Vidange> it = vidanges.iterator();
		while(it.hasNext()) {
			Vidange v = it.next();
			if(v.getVide().equals("no")) {
				list.add(v);
			}
		}
		return list;
	}
	
	public Iterable<Vidange> findByVide(String vide) {
		return vidangeRepo.findByVide(vide);
	}
	
	public Vidange findByPoubelle(Poubelle P) {
		return vidangeRepo.findByPoubelle(P);
	}
	
	public Vidange save(Vidange V) {
		return vidangeRepo.save(V);
	}
}
