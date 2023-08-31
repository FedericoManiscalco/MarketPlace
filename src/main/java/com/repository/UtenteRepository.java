package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {

}
