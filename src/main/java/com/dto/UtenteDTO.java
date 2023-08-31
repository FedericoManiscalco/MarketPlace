package com.dto;

import java.util.ArrayList;
import java.util.List;

import com.entity.Prodotto;
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
public class UtenteDTO {

	private String nome;
	private String cognome;
	private String codiceFiscale;
	private Long cellulare;
	private String email;
	private String residenza;
	private List<Prodotto> Prodotti = new ArrayList<>();
}
