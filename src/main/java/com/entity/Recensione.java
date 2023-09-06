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
public class Recensione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recensione_id")
	private Integer recensioneId;

	private String messaggio;

	@ManyToOne
	@JoinColumn(name = "prodotto_id")
	private Prodotto prodotto;

}
