package com.dto;

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
	private Integer recensione;
	private Double prezzo;
	private Integer artigianoId;
}
