package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.Recensione;

public interface RecensioneRepository extends JpaRepository<Recensione, Integer> {

	@Query("SELECT u.recensioniUtente FROM UserInfo u WHERE u.utenteId = :utenteId")
	public List<Recensione> findRecensioniByUtente(Integer utenteId);

	@Query("SELECT p.recensioniProdotto FROM Prodotto p WHERE p.prodottoId = :prodottoId")
	public List<Recensione> findRecensioniByProdotto(Integer prodottoId);
}
