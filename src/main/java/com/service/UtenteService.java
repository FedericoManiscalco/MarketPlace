package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.dto.LogInDTO;
import com.dto.UtenteDTO;
import com.entity.Utente;

public interface UtenteService {

	public List<Utente> getUtenti();

	public ResponseEntity<Utente> findByEmail(String email);

	public ResponseEntity<Map<String, Boolean>> findByEmailAndPassword(LogInDTO logIn);

	public ResponseEntity<Utente> postUtente(UtenteDTO utenteDTO);

	public ResponseEntity<Utente> updateUtente(UtenteDTO utenteDTO);

	public ResponseEntity<Utente> patchUtente(UtenteDTO utenteDTO);

	public ResponseEntity<String> deleteUtente(Integer id);

}
