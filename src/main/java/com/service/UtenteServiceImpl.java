package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dto.UtenteDTO;
import com.entity.Utente;
import com.repository.UtenteRepository;

@Service
public class UtenteServiceImpl implements UtenteService {

	@Autowired
	private UtenteRepository ur;

	@Override
	public List<Utente> getUtenti() {
		return ur.findAll();
	}

	@Override
	public ResponseEntity<Utente> postUtente(UtenteDTO utenteDTO) {
		Utente u = toEntity(utenteDTO);
		try {
			ur.save(u);
			return new ResponseEntity<>(u, HttpStatus.ACCEPTED);
		} catch (OptimisticLockingFailureException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

	@Override
	public ResponseEntity<Utente> updateUtente(UtenteDTO utenteDTO) {
		try {
			Utente u = ur.findById(utenteDTO.getUtenteId()).get();

			u.setNome(utenteDTO.getNome());
			u.setCognome(utenteDTO.getCognome());
			u.setCodiceFiscale(utenteDTO.getCodiceFiscale());
			u.setCellulare(utenteDTO.getCellulare());
			u.setEmail(utenteDTO.getEmail());
			u.setResidenza(utenteDTO.getResidenza());

			Utente updatedUtente = ur.save(u);

			return new ResponseEntity<>(updatedUtente, HttpStatus.OK);
		} catch (OptimisticLockingFailureException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Utente> patchUtente(UtenteDTO utenteDTO) {
		try {

			Utente u = ur.findById(utenteDTO.getUtenteId()).get();

			if (utenteDTO.getCellulare() != null) {

			}
			Utente updatedUtente = ur.save(u);
			return new ResponseEntity<>(updatedUtente, HttpStatus.OK);
		} catch (OptimisticLockingFailureException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<String> deleteUtente(Integer id) {
		try {
			ur.deleteById(id);
			String message = "cancellazione avvenuta con successo";
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
		}
		String message = "cancellazione fallita";
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

	private Utente toEntity(UtenteDTO utenteDTO) {
		Utente u = new Utente();

		u.setNome(utenteDTO.getNome());
		u.setCognome(utenteDTO.getCognome());
		u.setCodiceFiscale(utenteDTO.getCodiceFiscale());
		u.setCellulare(utenteDTO.getCellulare());
		u.setEmail(utenteDTO.getEmail());
		u.setResidenza(utenteDTO.getResidenza());
		return u;

	}

}
