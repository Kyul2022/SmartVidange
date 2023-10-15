package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table( name ="Quarter")
public class Quarter {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int quarter_num;
	private String nom;
	private float longitutde;
	private float latitude;

}
