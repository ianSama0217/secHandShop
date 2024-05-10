package com.example.secHandShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.secHandShop.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	public boolean existsByEmail(String email);

	@Query(value = "SELECT * FROM user" //
			+ " WHERE email = :email", nativeQuery = true)
	public User findAccount(@Param("email") String email);
}
