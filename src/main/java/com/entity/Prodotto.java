package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Prodotto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prodotto_id")
	private Integer prodottoId;

	private String nome;
	private String materiale;
	private String descrizione;
	private Integer recensione;
	private Double prezzo;

	@ManyToOne
	@JoinColumn(name = "artigiano_id")
	private Artigiano artigiano;

}
