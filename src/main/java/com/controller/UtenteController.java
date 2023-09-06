package com.controller;

import java.util.List;
import java.util.Map;

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

import com.dto.LogInDTO;
import com.dto.UtenteDTO;
import com.entity.Utente;
import com.service.UtenteService;

@CrossOrigin
@RequestMapping("/utente/v1")
@RestController
public class UtenteController {

	@Autowired
	private UtenteService us;

	@GetMapping("/getUtente")
	public List<Utente> findAll() {
		return us.getUtenti();
	}

	@GetMapping("/getUtenteByEmail/{email}")
	public ResponseEntity<Utente> findByEmail(@PathVariable String email) {
		return us.findByEmail(email);
	}

	@PostMapping("/postLogIn")
	public ResponseEntity<Map<String, Boolean>> postLogIn(@RequestBody LogInDTO logIn) {
		return us.findByEmailAndPassword(logIn);
	}

	@PostMapping("/postUtente")
	public ResponseEntity<Utente> postProdotto(@RequestBody UtenteDTO utenteDTO) {
		return us.postUtente(utenteDTO);
	}

	@PutMapping("/updateUtente")
	public ResponseEntity<Utente> updateUtente(@RequestBody UtenteDTO utenteDTO) {
		return us.updateUtente(utenteDTO);
	}

	@PatchMapping("/patchUtente")
	public ResponseEntity<Utente> patchUtente(@RequestBody UtenteDTO utenteDTO) {
		return us.patchUtente(utenteDTO);
	}

	@DeleteMapping("/deleteUtente/{id}")
	public ResponseEntity<String> deleteUtente(@PathVariable Integer id) {
		return us.deleteUtente(id);
	}

}
