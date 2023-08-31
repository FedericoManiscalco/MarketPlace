package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Artigiano;

public interface ArtigianoRepository extends JpaRepository<Artigiano, Integer> {

}
