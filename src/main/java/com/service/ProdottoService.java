package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.dto.ProdottoDTO;
import com.entity.Prodotto;

public interface ProdottoService {

	public List<Prodotto> getProdotti();

	public List<ProdottoDTO> findProdottoLight();

	public List<ProdottoDTO> findByProdottiInVendita(Integer utenteId);

	public List<ProdottoDTO> findByNomeContaining(String nome);

	public List<ProdottoDTO> findByProdottoIdIn(List<Integer> prodottiCarrello);

	public List<ProdottoDTO> findProdottoWithLimitAndOffset(Integer limit, Integer offset);

	public ResponseEntity<Prodotto> postProdotto(ProdottoDTO prodottoDTO);

	public ResponseEntity<Prodotto> updateProdotto(ProdottoDTO prodottoDTO);

	public ResponseEntity<Prodotto> patchProdotto(ProdottoDTO prodottoDTO);

	public ResponseEntity<Map<String, String>> deleteProdotto(Integer id);

}
