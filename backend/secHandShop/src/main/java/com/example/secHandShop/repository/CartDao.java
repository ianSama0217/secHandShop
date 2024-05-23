package com.example.secHandShop.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.secHandShop.entity.Cart;
import com.example.secHandShop.vo.CartProductVo;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {

	public boolean existsByUserIdAndProductId(int userId, int productId);

	@Transactional
	@Modifying
	@Query(value = "UPDATE cart"//
			+ " SET quantity = :quantity,"//
			+ " price = :price"//
			+ " WHERE cart_id  = :id", nativeQuery = true)
	public int updateCart(@Param("id") int cartId, @Param("quantity") int quantity, @Param("price") int price);

	@Query(value = "SELECT new com.example.secHandShop.vo.CartProductVo"
			+ " (c.cartId, p.name, p.mimeType, p.photo, c.quantity, p.price, c.price, c.productId)" + " FROM Cart AS c"//
			+ " JOIN Product AS p"//
			+ " ON c.productId = p.productId"//
			+ " WHERE c.userId  = :id"//
			+ " ORDER BY c.cartId DESC")
	public List<CartProductVo> getCart(@Param("id") int userId);
}
