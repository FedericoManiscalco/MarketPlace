package com.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dto.ProdottoDTO;
import com.entity.Prodotto;

public interface ProdottoService {

	public List<Prodotto> getProdotti();

	public List<Prodotto> findByProdottiInVendita(Integer utenteId);

	public ResponseEntity<Prodotto> postProdotto(ProdottoDTO prodottoDTO);

	public ResponseEntity<Prodotto> updateProdotto(ProdottoDTO prodottoDTO);

	public ResponseEntity<Prodotto> patchProdotto(ProdottoDTO prodottoDTO);

	public ResponseEntity<String> deleteProdotto(Integer id);

}
