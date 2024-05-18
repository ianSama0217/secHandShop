package com.example.secHandShop.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.secHandShop.entity.Cart;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {

	@Transactional
	@Modifying
	@Query(value = "UPDATE cart"//
			+ " SET quantity = :quantity"//
			+ " WHERE cart_id  = :id", nativeQuery = true)
	public int updateCart(@Param("id") int cartId, @Param("quantity") int quantity);

	@Query(value = "SELECT * FROM cart"//
			+ " WHERE user_id  = :id"//
			+ " ORDER BY cart_id DESC", nativeQuery = true)
	public List<Cart> getCart(@Param("id") int userId);
}
