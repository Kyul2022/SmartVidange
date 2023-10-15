package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.trans_client;

@Repository
public interface trans_clientRepository extends JpaRepository<trans_client, Integer> {

	trans_client findByTransId(int id);
	Iterable<trans_client> findByStatus(String str);

}
