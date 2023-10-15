package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Quarter;

@Repository
public interface QuarterRepository extends JpaRepository<Quarter,Integer> {

}
