package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dto.ProdottoDTO;
import com.dto.UtenteDTO;
import com.entity.Utente;
import com.repository.ProdottoRepository;
import com.repository.UtenteRepository;

@Service
public class UtenteServiceImpl implements UtenteService {

	@Autowired
	private UtenteRepository ur;

	@Autowired
	private ProdottoRepository pr;

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
	public ResponseEntity<Utente> updateUtente(Integer id) {
		try {
			Utente u = ur.findById(id).get();
			ur.save(u);
			return new ResponseEntity<>(u, HttpStatus.OK);
		} catch (OptimisticLockingFailureException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Utente> patchUtente(Integer id) {
		try {
			Utente u = ur.findById(id).get();
			ur.save(u);
			return new ResponseEntity<>(u, HttpStatus.OK);
		} catch (OptimisticLockingFailureException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Utente> patchProdottiUtente(Integer id, ProdottoDTO prodottoDTO) {
		try {
			Utente u = ur.findById(id).get();
			u.getProdotti().add(pr.findById(prodottoDTO.getProdottoId()).get());

			return new ResponseEntity<>(u, HttpStatus.OK);
		} catch (OptimisticLockingFailureException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Utente> deleteUtente(Integer id) {
		try {
			ur.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	private Utente toEntity(UtenteDTO utenteDTO) {
		Utente u = new Utente();

		u.setNome(utenteDTO.getNome());
		u.setCognome(utenteDTO.getCognome());
		u.setCodiceFiscale(utenteDTO.getCodiceFiscale());
		u.setCellulare(utenteDTO.getCellulare());
		u.setEmail(utenteDTO.getEmail());
		u.setResidenza(utenteDTO.getResidenza());
		u.setProdotti(utenteDTO.getProdotti());
		return u;

	}

}
