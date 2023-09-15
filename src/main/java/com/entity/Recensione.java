package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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

	@Min(0)
	@Max(5)
	private Integer voto;

	@ManyToOne
	@JoinColumn(name = "prodotto_id")
	private Prodotto prodotto;

	@ManyToOne
	@JoinColumn(name = "utente_id")
	private UserInfo utente;

}
