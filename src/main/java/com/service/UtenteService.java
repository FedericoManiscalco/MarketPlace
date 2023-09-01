package com.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dto.UtenteDTO;
import com.entity.Utente;

public interface UtenteService {

	public List<Utente> getUtenti();

	public ResponseEntity<Utente> postUtente(UtenteDTO utenteDTO);

	public ResponseEntity<Utente> updateUtente(UtenteDTO utenteDTO);

	public ResponseEntity<Utente> patchUtente(UtenteDTO utenteDTO);

	public ResponseEntity<String> deleteUtente(Integer id);

}
