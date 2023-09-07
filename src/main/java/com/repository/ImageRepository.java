package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
	Optional<Image> findByName(String name);

	Optional<Image> findById(Long imageId);
}
