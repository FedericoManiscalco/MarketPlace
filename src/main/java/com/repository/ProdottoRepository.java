package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dto.ProdottoDTO;
import com.entity.Prodotto;

public interface ProdottoRepository extends JpaRepository<Prodotto, Integer> {

	@Query("SELECT NEW com.dto.ProdottoDTO(p.prodottoId, p.nome, p.materiale, p.descrizione, p.prezzo, p.image.imageId, p.utente.utenteId) FROM Prodotto p")
	public List<ProdottoDTO> findProdottoLight();

	@Query("SELECT NEW com.dto.ProdottoDTO(p.prodottoId, p.nome, p.materiale, p.descrizione, p.prezzo, p.image.imageId, p.utente.utenteId) FROM Prodotto p JOIN UserInfo u ON u.utenteId = :utenteId")
	public List<ProdottoDTO> findByProdottiInVendita(Integer utenteId);

	@Query("SELECT NEW com.dto.ProdottoDTO(p.prodottoId, p.nome, p.materiale, p.descrizione, p.prezzo, p.image.imageId, p.utente.utenteId) FROM Prodotto p WHERE p.nome LIKE %:nome%")
	public List<ProdottoDTO> findByNomeContaining(String nome);

	@Query("SELECT NEW com.dto.ProdottoDTO(p.prodottoId, p.nome, p.materiale, p.descrizione, p.prezzo, p.image.imageId, p.utente.utenteId) FROM Prodotto p WHERE p.prodottoId IN :prodottiCarrello")
	public List<ProdottoDTO> findByProdottoIdIn(List<Integer> prodottiCarrello);

	@Query("SELECT NEW com.dto.ProdottoDTO(p.prodottoId, p.nome, p.materiale, p.descrizione, p.prezzo, p.image.imageId, p.utente.utenteId) FROM Prodotto p")
	public List<ProdottoDTO> findProdottoWithLimitAndOffset(@Param("limit") Integer limit,
			@Param("offset") Integer offset);
}
