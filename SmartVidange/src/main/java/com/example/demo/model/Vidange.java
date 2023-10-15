package com.example.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table( name ="Vidange")
public class Vidange {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vidange_id;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "poubelle", referencedColumnName = "numSerie")
	private Poubelle poubelle;	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "videur", referencedColumnName = "matricule")
	private Agent videur;
	private String vide;//prise en charge deja ou pas
	
}
