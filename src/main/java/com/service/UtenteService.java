package com.service;

import org.springframework.http.ResponseEntity;

import com.dto.ProdottoDTO;
import com.dto.UtenteDTO;
import com.entity.Utente;

public interface UtenteService {

	public ResponseEntity<Utente> postUtente(UtenteDTO utenteDTO);

	public ResponseEntity<Utente> updateUtente(Integer id);

	public ResponseEntity<Utente> patchUtente(Integer id);

	public ResponseEntity<Utente> patchProdottiUtente(Integer id, ProdottoDTO prodottoDTO);

	public ResponseEntity<Utente> deleteUtente(Integer id);

}
