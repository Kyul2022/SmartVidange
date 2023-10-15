package com.example.demo.model;

import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table( name ="Client")
public class Client {

	@Id
	private String NCNI;
	private String nom;
	private String tel;
}
