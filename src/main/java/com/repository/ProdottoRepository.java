package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dto.ProdottoDTO;
import com.entity.Prodotto;

public interface ProdottoRepository extends JpaRepository<Prodotto, Integer> {

	@Query("SELECT NEW com.dto.ProdottoDTO(p.prodottoId, p.nome, p.materiale, p.descrizione, p.prezzo, p.image.imageId, p.utente.utenteId) FROM Prodotto p")
	public List<ProdottoDTO> findProdottoLight();

	@Query("SELECT u.prodottiInVendita FROM UserInfo u WHERE u.utenteId = :utenteId")
	public List<Prodotto> findByProdottiInVendita(Integer utenteId);

	public List<Prodotto> findByNomeContaining(String nome);

	public List<Prodotto> findByProdottoIdIn(List<Integer> prodottiCarrello);

	@Query(value = "SELECT * FROM prodotto limit ? offset ?", nativeQuery = true)
	public List<Prodotto> findProdottoWithLimitAndOffset(Integer limit, Integer offset);
}
