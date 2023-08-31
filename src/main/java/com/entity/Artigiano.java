package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Artigiano {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "artigiano_id")
	private Integer artigianoId;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String cognome;

	@Column(name = "codice_fiscale", nullable = false)
	private String codiceFiscale;

	@Column(nullable = false, unique = true)
	private Long cellulare;

	@Column(nullable = false, unique = true)
	private String email;

}
