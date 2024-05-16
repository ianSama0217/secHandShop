package com.example.secHandShop.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

	@Transactional
	@Modifying
	@Query(value = "UPDATE user"//
			+ " SET name = :name,"//
			+ "  mime_type = :mime,"//
			+ "  photo = :photo,"//
			+ "  phone = :phone"//
			+ " WHERE user_id = :id", nativeQuery = true)
	public int updateAccount(@Param("id") int userId, @Param("name") String name, @Param("mime") String mime,
			@Param("photo") byte[] photo, @Param("phone") String phone);
}
