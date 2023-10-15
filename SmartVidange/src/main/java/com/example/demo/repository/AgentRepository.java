package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Agent;
import com.example.demo.model.Quarter;

@Repository
public interface AgentRepository extends JpaRepository<Agent,String> {

	Agent findByQuarter(Quarter quarter);
	Agent findByMatricule(String matricule);
}
