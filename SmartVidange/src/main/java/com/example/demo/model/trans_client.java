package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table( name ="trans_client")
public class trans_client {

	@Id
	private int transId;
	private String status;
}
