package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dto.ProdottoDTO;
import com.entity.Prodotto;
import com.repository.ProdottoRepository;
import com.repository.UserInfoRepository;

@Service
public class ProdottoServiceImpl implements ProdottoService {

	@Autowired
	private ProdottoRepository pr;

	@Autowired
	private UserInfoRepository ur;

	@Override
	public List<Prodotto> getProdotti() {
		return pr.findAll();
	}

	@Override
	public List<Prodotto> findByProdottiInVendita(Integer utenteId) {
		return pr.findByProdottiInVendita(utenteId);
	}

	@Override
	public ResponseEntity<Prodotto> postProdotto(ProdottoDTO prodottoDTO) {

		try {
			Prodotto p = toEntity(prodottoDTO);
			pr.save(p);
			return new ResponseEntity<>(p, HttpStatus.ACCEPTED);
		} catch (OptimisticLockingFailureException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

	@Override
	public ResponseEntity<Prodotto> updateProdotto(ProdottoDTO prodottoDTO) {

		try {
			Prodotto p = pr.findById(prodottoDTO.getProdottoId()).get();
			p.setNome(prodottoDTO.getNome());
			p.setMateriale(prodottoDTO.getMateriale());
			p.setPrezzo(prodottoDTO.getPrezzo());
			p.setDescrizione(prodottoDTO.getDescrizione());
			p.setImmagine(prodottoDTO.getImmagine());
			p.setUtente(ur.findById(prodottoDTO.getUtenteId()).get());

			pr.save(p);
			return new ResponseEntity<>(p, HttpStatus.ACCEPTED);
		} catch (OptimisticLockingFailureException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

	@Override
	public ResponseEntity<Prodotto> patchProdotto(ProdottoDTO prodottoDTO) {

		try {
			Prodotto p = pr.findById(prodottoDTO.getProdottoId()).get();
			if (prodottoDTO.getNome() != null) {
				p.setNome(prodottoDTO.getNome());
			}
			if (prodottoDTO.getMateriale() != null) {
				p.setMateriale(prodottoDTO.getMateriale());
			}
			if (prodottoDTO.getPrezzo() != null) {
				p.setPrezzo(prodottoDTO.getPrezzo());
			}
			if (prodottoDTO.getDescrizione() != null) {
				p.setDescrizione(prodottoDTO.getDescrizione());
			}
			if (prodottoDTO.getImmagine() != null) {
				p.setImmagine(prodottoDTO.getImmagine());
			}
			if (prodottoDTO.getUtenteId() != null) {
				p.setUtente(ur.findById(prodottoDTO.getUtenteId()).get());
			}
			pr.save(p);
			return new ResponseEntity<>(p, HttpStatus.ACCEPTED);
		} catch (OptimisticLockingFailureException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

	@Override
	public ResponseEntity<String> deleteProdotto(Integer id) {
		try {
			pr.deleteById(id);
			String message = "cancellazione avvenuta con successo";
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
		}
		String message = "cancellazione fallita";
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

	private Prodotto toEntity(ProdottoDTO prodottoDTO) {
		Prodotto p = new Prodotto();

		p.setNome(prodottoDTO.getNome());
		p.setMateriale(prodottoDTO.getMateriale());
		p.setPrezzo(prodottoDTO.getPrezzo());
		p.setDescrizione(prodottoDTO.getDescrizione());
		p.setImmagine(prodottoDTO.getImmagine());
		p.setUtente(ur.findById(prodottoDTO.getUtenteId()).get());

		return p;

	}

}
