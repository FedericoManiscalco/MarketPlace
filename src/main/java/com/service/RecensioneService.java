package com.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dto.RecensioneDTO;
import com.entity.Recensione;

public interface RecensioneService {

	public List<Recensione> getRecensioni();

	public List<Recensione> findRecensioniByUtente(Integer utenteId);

	public List<Recensione> findRecensioniByProdotto(Integer prodottoId);

	public ResponseEntity<Recensione> postRecensione(RecensioneDTO recensioneDTO);

	public ResponseEntity<Recensione> updateRecensione(RecensioneDTO recensioneDTO);

	public ResponseEntity<String> deleteRecensione(Integer id);

}
