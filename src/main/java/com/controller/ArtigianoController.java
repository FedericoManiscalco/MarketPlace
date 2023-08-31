package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ArtigianoDTO;
import com.entity.Artigiano;
import com.service.ArtigianoService;

@CrossOrigin
@RequestMapping("/artigiano/v1")
@RestController
public class ArtigianoController {

	@Autowired
	private ArtigianoService as;

	@PostMapping("/postArtigiano")
	public ResponseEntity<Artigiano> postArtigiano(@RequestBody ArtigianoDTO artigianoDTO) {
		return as.postArtigiano(artigianoDTO);
	}

}
