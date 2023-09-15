package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dto.UtenteDTO;
import com.entity.UserInfo;
import com.repository.UserInfoRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserInfoRepository ur;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<UserInfo> getAllUsers() {
		return ur.findAll();
	}

	public Optional<UserInfo> findByEmail(String email) {
		return ur.findByEmail(email);
	}

	public Optional<String> findIdByEmail(String email) {
		return ur.findIdByEmail(email);
	}

	public UserInfo getUser(int id) {
		Optional<UserInfo> userInfo = ur.findById(id);

		if (userInfo.isPresent()) {
			return userInfo.get();
		}

		throw new RuntimeException("User details not found for id " + id);
	}

	public ResponseEntity<UserInfo> addUser(UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		ur.save(userInfo);
		return new ResponseEntity<>(userInfo, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<UserInfo> updateUtente(UtenteDTO utenteDTO) {
		try {
			UserInfo u = ur.findById(utenteDTO.getUtenteId()).get();

			u.setNome(utenteDTO.getNome());
			u.setCognome(utenteDTO.getCognome());
			u.setCodiceFiscale(utenteDTO.getCodiceFiscale());
			u.setCellulare(utenteDTO.getCellulare());
			u.setEmail(utenteDTO.getEmail());
			u.setResidenza(utenteDTO.getResidenza());

			UserInfo updatedUtente = ur.save(u);

			return new ResponseEntity<>(updatedUtente, HttpStatus.OK);
		} catch (OptimisticLockingFailureException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<UserInfo> patchUtente(UtenteDTO utenteDTO) {
		try {

			UserInfo u = ur.findById(utenteDTO.getUtenteId()).get();

			if (utenteDTO.getCellulare() != null) {
				u.setCellulare(utenteDTO.getCellulare());
			}
			if (utenteDTO.getCodiceFiscale() != null) {
				u.setCodiceFiscale(utenteDTO.getCodiceFiscale());
			}
			if (utenteDTO.getNome() != null) {
				u.setNome(utenteDTO.getNome());
			}
			if (utenteDTO.getCognome() != null) {
				u.setCognome(utenteDTO.getCognome());
			}
			if (utenteDTO.getEmail() != null) {
				u.setEmail(utenteDTO.getEmail());
			}
			if (utenteDTO.getResidenza() != null) {
				u.setResidenza(utenteDTO.getResidenza());
			}

			UserInfo updatedUtente = ur.save(u);
			return new ResponseEntity<>(updatedUtente, HttpStatus.OK);
		} catch (OptimisticLockingFailureException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<String> deleteUtente(Integer id) {
		try {
			ur.deleteById(id);
			String message = "cancellazione avvenuta con successo";
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
		}
		String message = "cancellazione fallita";
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

//	private UserInfo toEntity(UtenteDTO utenteDTO) {
//		UserInfo u = new UserInfo();
//
//		u.setNome(utenteDTO.getNome());
//		u.setCognome(utenteDTO.getCognome());
//		u.setCodiceFiscale(utenteDTO.getCodiceFiscale());
//		u.setCellulare(utenteDTO.getCellulare());
//		u.setEmail(utenteDTO.getEmail());
//		u.setPassword(utenteDTO.getPassword());
//		u.setResidenza(utenteDTO.getResidenza());
//		return u;
//
//	}
}
