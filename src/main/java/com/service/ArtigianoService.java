package com.service;

import org.springframework.http.ResponseEntity;

import com.dto.ArtigianoDTO;
import com.entity.Artigiano;

public interface ArtigianoService {

	public ResponseEntity<Artigiano> postArtigiano(ArtigianoDTO artigianoDTO);

	public ResponseEntity<Artigiano> updateArtigiano(Integer id);

	public ResponseEntity<Artigiano> patchArtigiano(Integer id);

	public ResponseEntity<Artigiano> deleteArtigiano(Integer id);

}
