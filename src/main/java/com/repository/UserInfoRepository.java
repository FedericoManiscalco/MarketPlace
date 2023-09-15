package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

	public Optional<UserInfo> findByEmail(String email);

	@Query("SELECT u.utenteId FROM UserInfo u WHERE email = :email")
	public Optional<String> findIdByEmail(String email);

}
