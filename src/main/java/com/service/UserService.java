package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.dto.UtenteDTO;
import com.entity.UserInfo;

public interface UserService {

	public ResponseEntity<UserInfo> updateUtente(UtenteDTO utenteDTO);

	public ResponseEntity<UserInfo> patchUtente(UtenteDTO utenteDTO);

	public ResponseEntity<String> deleteUtente(Integer id);

	public Optional<UserInfo> findByEmail(String email);

	public Optional<String> findIdByEmail(String email);

	public UserInfo getUser(int id);

	public ResponseEntity<UserInfo> addUser(UserInfo userInfo);

	public List<UserInfo> getAllUsers();

}
