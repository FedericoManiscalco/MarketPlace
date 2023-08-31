package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dto.ProdottoDTO;
import com.entity.Prodotto;
import com.repository.ArtigianoRepository;
import com.repository.ProdottoRepository;

@Service
public class ProdottoServiceImpl implements ProdottoService {

	@Autowired
	private ProdottoRepository pr;

	@Autowired
	private ArtigianoRepository ar;

	@Override
	public ResponseEntity<Prodotto> postProdotto(ProdottoDTO prodottoDTO) {
		Prodotto p = toEntity(prodottoDTO);
		try {
			pr.save(p);
			return new ResponseEntity<>(p, HttpStatus.ACCEPTED);
		} catch (OptimisticLockingFailureException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

	@Override
	public ResponseEntity<Prodotto> updateProdotto(Integer id) {
		Prodotto p = pr.findById(id).get();
		try {
			pr.save(p);
			return new ResponseEntity<>(p, HttpStatus.ACCEPTED);
		} catch (OptimisticLockingFailureException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

	@Override
	public ResponseEntity<Prodotto> patchProdotto(Integer id) {
		Prodotto p = pr.findById(id).get();
		try {
			pr.save(p);
			return new ResponseEntity<>(p, HttpStatus.ACCEPTED);
		} catch (OptimisticLockingFailureException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

	@Override
	public ResponseEntity<Prodotto> deleteProdotto(Integer id) {
		try {
			pr.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	private Prodotto toEntity(ProdottoDTO prodottoDTO) {
		Prodotto p = new Prodotto();

		p.setNome(prodottoDTO.getNome());
		p.setMateriale(prodottoDTO.getMateriale());
		p.setPrezzo(prodottoDTO.getPrezzo());
		p.setDescrizione(prodottoDTO.getDescrizione());
		p.setRecensione(prodottoDTO.getRecensione());
		p.setArtigiano(ar.findById(prodottoDTO.getArtigianoId()).get());

		return p;

	}

}
