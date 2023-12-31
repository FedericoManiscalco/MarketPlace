package com.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Prodotto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prodotto_id")
	private Integer prodottoId;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String materiale;
	private String descrizione;

	@JsonIgnore
	@OneToMany(mappedBy = "prodotto")
	private List<Recensione> recensioniProdotto;

	@OneToOne
	@JoinColumn(name = "image_id", referencedColumnName = "image_id")
	private Image image;

	@Column(nullable = false)
	private Double prezzo;

	@ManyToOne
	@JoinColumn(name = "utente_id", nullable = false)
	private UserInfo utente;

}
