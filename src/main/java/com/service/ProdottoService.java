package com.service;

import org.springframework.http.ResponseEntity;

import com.dto.ProdottoDTO;
import com.entity.Prodotto;

public interface ProdottoService {

	public ResponseEntity<Prodotto> postProdotto(ProdottoDTO prodottoDTO);

	public ResponseEntity<Prodotto> updateProdotto(Integer id);

	public ResponseEntity<Prodotto> patchProdotto(Integer id);

	public ResponseEntity<Prodotto> deleteProdotto(Integer id);

}
