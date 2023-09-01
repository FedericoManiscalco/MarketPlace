package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.Prodotto;

public interface ProdottoRepository extends JpaRepository<Prodotto, Integer> {

	@Query("SELECT u.prodottiInVendita FROM Utente u WHERE u.utenteId = :utenteId")
	public List<Prodotto> findByProdottiInVendita(Integer utenteId);

}
