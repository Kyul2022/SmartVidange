package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Users;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

	Users findUserByName(String name);
}
