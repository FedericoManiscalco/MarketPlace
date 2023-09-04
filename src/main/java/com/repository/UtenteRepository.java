package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {

	public Utente findByEmailAndPassword(String email, String password);

	public Utente findByEmail(String email);

}
