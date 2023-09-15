package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dto.RecensioneDTO;
import com.entity.Recensione;
import com.repository.ProdottoRepository;
import com.repository.RecensioneRepository;
import com.repository.UserInfoRepository;

@Service
public class RecensioneServiceImpl implements RecensioneService {

	@Autowired
	private RecensioneRepository rr;

	@Autowired
	private ProdottoRepository pr;

	@Autowired
	private UserInfoRepository ur;

	@Override
	public List<Recensione> getRecensioni() {
		return rr.findAll();
	}

	@Override
	public List<Recensione> findRecensioniByUtente(Integer utenteId) {
		return rr.findRecensioniByUtente(utenteId);
	}

	public List<Recensione> findRecensioniByProdotto(Integer prodottoId) {
		return rr.findRecensioniByProdotto(prodottoId);
	}

	@Override
	public ResponseEntity<Recensione> postRecensione(RecensioneDTO recensioneDTO) {
		try {
			Recensione r = toEntity(recensioneDTO);
			rr.save(r);
			return new ResponseEntity<>(r, HttpStatus.ACCEPTED);
		} catch (OptimisticLockingFailureException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

	@Override
	public ResponseEntity<Recensione> updateRecensione(RecensioneDTO recensioneDTO) {
		try {
			Recensione r = rr.findById(recensioneDTO.getProdottoId()).get();
			r.setMessaggio(recensioneDTO.getMessaggio());
			rr.save(r);
			return new ResponseEntity<>(r, HttpStatus.ACCEPTED);
		} catch (OptimisticLockingFailureException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

	@Override
	public ResponseEntity<String> deleteRecensione(Integer id) {
		try {
			rr.deleteById(id);
			String message = "cancellazione avvenuta con successo";
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
		}
		String message = "cancellazione fallita";
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

	private Recensione toEntity(RecensioneDTO recensioneDTO) {
		Recensione r = new Recensione();

		r.setMessaggio(recensioneDTO.getMessaggio());
		r.setVoto(recensioneDTO.getVoto());
		r.setProdotto(pr.findById(recensioneDTO.getProdottoId()).get());
		r.setUtente(ur.findById(recensioneDTO.getUtenteId()).get());

		return r;

	}

}
