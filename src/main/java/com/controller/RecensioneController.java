package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.RecensioneDTO;
import com.entity.Recensione;
import com.service.RecensioneService;

@CrossOrigin
@RequestMapping("/recensione/v1")
@RestController
public class RecensioneController {

	@Autowired
	private RecensioneService rs;

	@GetMapping("/getRecensioni")
	public List<Recensione> getRecensioni() {
		return rs.getRecensioni();
	}

	@GetMapping("/findRecensioniByUtente/{utenteId}")
	public List<Recensione> findRecensioniByUtente(@PathVariable Integer utenteId) {
		return rs.findRecensioniByUtente(utenteId);
	}

	@GetMapping("/findRecensioniByProdotto")
	public List<Recensione> findRecensioniByProdotto(@PathVariable Integer prodottoId) {
		return rs.findRecensioniByProdotto(prodottoId);
	}

	@PostMapping("/postRecensione")
	public ResponseEntity<Recensione> postRecensione(@RequestBody RecensioneDTO recensioneDTO) {
		return rs.postRecensione(recensioneDTO);
	}

	@PutMapping("/updateRecensione")
	public ResponseEntity<Recensione> updateRecensione(@RequestBody RecensioneDTO recensioneDTO) {
		return rs.updateRecensione(recensioneDTO);
	}

	@DeleteMapping("/deleteRecensione/{id}")
	public ResponseEntity<String> deleteProdotto(@PathVariable Integer id) {
		return rs.deleteRecensione(id);
	}
}
