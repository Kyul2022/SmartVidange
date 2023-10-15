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
@Table( name ="Poubelle")
public class Poubelle {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int numSerie;
	private float longitude;
	private float lattitude;
	private double plein;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "client", referencedColumnName = "NCNI")
	private Client client;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "quarter", referencedColumnName = "quarter_num")
	private Quarter quarter;

}
