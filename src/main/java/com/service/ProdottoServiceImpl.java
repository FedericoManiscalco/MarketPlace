package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dto.ProdottoDTO;
import com.entity.Image;
import com.entity.Prodotto;
import com.repository.ImageRepository;
import com.repository.ProdottoRepository;
import com.repository.UserInfoRepository;

@Service
public class ProdottoServiceImpl implements ProdottoService {

	@Autowired
	private ProdottoRepository pr;

	@Autowired
	private UserInfoRepository ur;

	@Autowired
	ImageRepository ir;

	@Override
	public List<Prodotto> getProdotti() {
		return pr.findAll();
	}

	public List<ProdottoDTO> findProdottoLight() {
		return pr.findProdottoLight();
	}

	@Override
	public List<ProdottoDTO> findByProdottiInVendita(Integer utenteId) {
		return pr.findByProdottiInVendita(utenteId);
	}

	public List<ProdottoDTO> findByNomeContaining(String nome) {
		return pr.findByNomeContaining(nome);
	}

	public List<ProdottoDTO> findByProdottoIdIn(List<Integer> prodottiCarrello) {
		return pr.findByProdottoIdIn(prodottiCarrello);
	}

	public List<ProdottoDTO> findProdottoWithLimitAndOffset(Integer limit, Integer offset) {
		List<ProdottoDTO> lista = pr.findProdottoWithLimitAndOffset(limit, offset);
		if (offset >= 0 && offset < lista.size()) {
			if (offset + limit >= lista.size()) {
				return lista.subList(offset, lista.size());
			}
			return lista.subList(offset, offset + limit);
		}
		return null;
	}

	@Override
	public ResponseEntity<Prodotto> postProdotto(ProdottoDTO prodottoDTO) {
		System.out.println(prodottoDTO);
		try {
			Prodotto p = toEntity(prodottoDTO);
			Optional<Image> img = ir.findById(prodottoDTO.getImageId());
			if (img.isPresent())
				p.setImage(img.get());
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
	public ResponseEntity<Map<String, String>> deleteProdotto(Integer id) {
		Map<String, String> toReturn = new HashMap<String, String>();
		try {
			pr.deleteById(id);

			toReturn.put("message", "cancellazione avvenuta con successo");
			return new ResponseEntity<>(toReturn, HttpStatus.OK);
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
		}
		toReturn.put("message", "cancellazione fallita");
		return new ResponseEntity<>(toReturn, HttpStatus.NOT_FOUND);
	}

	private Prodotto toEntity(ProdottoDTO prodottoDTO) {
		Prodotto p = new Prodotto();

		p.setNome(prodottoDTO.getNome());
		p.setMateriale(prodottoDTO.getMateriale());
		p.setPrezzo(prodottoDTO.getPrezzo());
		p.setDescrizione(prodottoDTO.getDescrizione());
		p.setUtente(ur.findById(prodottoDTO.getUtenteId()).get());

		return p;

	}

}
