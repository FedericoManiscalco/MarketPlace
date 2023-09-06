package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dto.LogInDTO;
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
	public ResponseEntity<Utente> findByEmail(String email) {

		Utente u = ur.findByEmail(email);
		if (u != null) {
			return new ResponseEntity<>(u, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Map<String, Boolean>> findByEmailAndPassword(LogInDTO logIn) {
		Utente u = new Utente();
		Map<String, Boolean> res = new HashMap<String, Boolean>();
		u = ur.findByEmailAndPassword(logIn.getEmail(), logIn.getPassword());

		if (u != null) {
			res.put("logged", true);
			return new ResponseEntity<>(res, HttpStatus.OK);

		}
		res.put("logged", false);
		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
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
				u.setCellulare(utenteDTO.getCellulare());
			}
			if (utenteDTO.getCodiceFiscale() != null) {
				u.setCodiceFiscale(utenteDTO.getCodiceFiscale());
			}
			if (utenteDTO.getNome() != null) {
				u.setNome(utenteDTO.getNome());
			}
			if (utenteDTO.getCognome() != null) {
				u.setCognome(utenteDTO.getCognome());
			}
			if (utenteDTO.getEmail() != null) {
				u.setEmail(utenteDTO.getEmail());
			}
			if (utenteDTO.getResidenza() != null) {
				u.setResidenza(utenteDTO.getResidenza());
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
		u.setPassword(utenteDTO.getPassword());
		u.setResidenza(utenteDTO.getResidenza());
		return u;

	}

}
