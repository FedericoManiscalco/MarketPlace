package com.dto;

import java.util.ArrayList;
import java.util.List;

import com.entity.Recensione;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class ProdottoDTO {

	private Integer prodottoId;
	private String nome;
	private String materiale;
	private String descrizione;
	private List<Recensione> recensioni = new ArrayList<>();
	private Double prezzo;
	private String immagine;
	private Integer utenteId;
}
