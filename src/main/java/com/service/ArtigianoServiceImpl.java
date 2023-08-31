package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dto.ArtigianoDTO;
import com.entity.Artigiano;
import com.repository.ArtigianoRepository;

@Service
public class ArtigianoServiceImpl implements ArtigianoService {

	@Autowired
	private ArtigianoRepository ar;

	@Override
	public ResponseEntity<Artigiano> postArtigiano(ArtigianoDTO artigianoDTO) {
		Artigiano a = toEntity(artigianoDTO);

		try {
			ar.save(a);
			return new ResponseEntity<>(a, HttpStatus.ACCEPTED);
		} catch (OptimisticLockingFailureException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

	@Override
	public ResponseEntity<Artigiano> updateArtigiano(Integer id) {
		try {
			Artigiano a = ar.findById(id).get();
			ar.save(a);
			return new ResponseEntity<>(a, HttpStatus.OK);
		} catch (OptimisticLockingFailureException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Artigiano> patchArtigiano(Integer id) {
		try {
			Artigiano a = ar.findById(id).get();
			ar.save(a);
			return new ResponseEntity<>(a, HttpStatus.OK);
		} catch (OptimisticLockingFailureException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Artigiano> deleteArtigiano(Integer id) {
		try {
			ar.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	private Artigiano toEntity(ArtigianoDTO artigianoDTO) {
		Artigiano a = new Artigiano();

		a.setNome(artigianoDTO.getNome());
		a.setCognome(artigianoDTO.getCognome());
		a.setCodiceFiscale(artigianoDTO.getCodiceFiscale());
		a.setCellulare(artigianoDTO.getCellulare());
		a.setEmail(artigianoDTO.getEmail());
		return a;

	}

}
