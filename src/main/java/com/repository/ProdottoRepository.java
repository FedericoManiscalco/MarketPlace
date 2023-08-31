package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Prodotto;

public interface ProdottoRepository extends JpaRepository<Prodotto, Integer> {

}
