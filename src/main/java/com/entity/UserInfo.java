package com.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user_details")
@Data
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "utente_id")
	private Integer utenteId;

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

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String residenza;

	@Column(nullable = false)
	private String roles;

	@JsonIgnore
	@OneToMany(mappedBy = "utente")
	private List<Prodotto> prodottiInVendita = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "utente")
	private List<Recensione> recensioni = new ArrayList<>();
}
