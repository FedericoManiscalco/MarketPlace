package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ProdottoDTO;
import com.entity.Prodotto;
import com.service.ProdottoService;

@CrossOrigin
@RequestMapping("/api/v1")
@RestController
public class ProdottoController {

	@Autowired
	private ProdottoService ps;

	@GetMapping("/getProdotto")
	public List<Prodotto> findAll() {
		return ps.getProdotti();
	}

	@GetMapping("/getProdottiInVendita/{utenteId}")
	public List<Prodotto> findProdottiInVendita(@PathVariable Integer utenteId) {
		return ps.findByProdottiInVendita(utenteId);
	}

	@PostMapping("/postProdotto")
	// @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Prodotto> postProdotto(@RequestBody ProdottoDTO prodottoDTO) {
		return ps.postProdotto(prodottoDTO);
	}

	@PutMapping("/updateProdotto")
	public ResponseEntity<Prodotto> updateProdotto(@RequestBody ProdottoDTO prodottoDTO) {
		return ps.updateProdotto(prodottoDTO);
	}

	@PatchMapping("/patchProdotto")
	public ResponseEntity<Prodotto> patchProdotto(@RequestBody ProdottoDTO prodottoDTO) {
		return ps.patchProdotto(prodottoDTO);
	}

	@DeleteMapping("/deleteProdotto/{id}")
	public ResponseEntity<String> deleteProdotto(@PathVariable Integer id) {
		return ps.deleteProdotto(id);
	}

}
