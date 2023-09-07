package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

import com.dto.AuthRequest;
import com.dto.UtenteDTO;
import com.entity.UserInfo;
import com.service.JwtService;
import com.service.UserServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserServiceImpl us;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/isRunning")
	public String isRunning() {
		return "Service is running";
	}

	@PostMapping("/addUser")
	public ResponseEntity<UserInfo> addUser(@RequestBody UserInfo userInfo) {
		return us.addUser(userInfo);
	}

	@GetMapping("/getAllUsers")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<UserInfo> getAllUsers() {
		return us.getAllUsers();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public UserInfo getUserById(@PathVariable int id) {
		return us.getUser(id);
	}

	@PostMapping("/getToken")
	public Map<String, String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

		if (authentication.isAuthenticated()) {
			Map<String, String> tokenJson = new HashMap<String, String>();
			tokenJson.put("token", jwtService.generateToken(authRequest.getEmail()));
			return tokenJson;
		}

		throw new UsernameNotFoundException("invalid user details.");
	}

	@PutMapping("/updateUtente")
	public ResponseEntity<UserInfo> updateUtente(@RequestBody UtenteDTO utenteDTO) {
		return us.updateUtente(utenteDTO);
	}

	@PatchMapping("/patchUtente")
	public ResponseEntity<UserInfo> patchUtente(@RequestBody UtenteDTO utenteDTO) {
		return us.patchUtente(utenteDTO);
	}

	@DeleteMapping("/deleteUtente/{id}")
	public ResponseEntity<String> deleteUtente(@PathVariable Integer id) {
		return us.deleteUtente(id);
	}
}
