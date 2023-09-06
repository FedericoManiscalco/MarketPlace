package com.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dto.UtenteDTO;
import com.entity.UserInfo;

public interface UserService {

	public ResponseEntity<UserInfo> updateUtente(UtenteDTO utenteDTO);

	public ResponseEntity<UserInfo> patchUtente(UtenteDTO utenteDTO);

	public ResponseEntity<String> deleteUtente(Integer id);

	public UserInfo getUser(int id);

	public String addUser(UserInfo userInfo);

	public List<UserInfo> getAllUsers();

}
