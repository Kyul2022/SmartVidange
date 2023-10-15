package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Agent;
import com.example.demo.model.Poubelle;
import com.example.demo.model.Vidange;

@Repository
public interface VidangeRepository extends JpaRepository<Vidange,Integer> {

	Iterable<Vidange> findByVideur(Agent videur);
	Iterable<Vidange> findByVide(String vide);
	Vidange findByPoubelle(Poubelle P);
}
