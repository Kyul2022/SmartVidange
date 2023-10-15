package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Poubelle;

@Repository
public interface PoubelleRepository extends JpaRepository<Poubelle,Integer> {

	Poubelle findByNumSerie(int id);
}
