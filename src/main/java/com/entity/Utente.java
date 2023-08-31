package com.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Utente {

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
	private String residenza;

	@ManyToMany
	@JoinTable(name = "utente_prodotto", joinColumns = @JoinColumn(name = "utente_id"), inverseJoinColumns = @JoinColumn(name = "prodotto_id"))
	private List<Prodotto> Prodotti = new ArrayList<>();

}
