package com.example.secHandShop.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.secHandShop.entity.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

	// 搜尋商品
	@Query(value = "SELECT * FROM product"//
			+ " WHERE (:name IS NULL OR name LIKE CONCAT('%', :name, '%'))"//
			+ " AND (:type IS NULL OR type = :type)"//
			+ " AND (:lowPrice IS NULL OR price >= :lowPrice)"//
			+ " AND (:highPrice IS NULL OR price <= :highPrice)" //
			+ " ORDER BY"//
			+ " CASE WHEN :sort IS NULL THEN product_id END DESC,"//
			+ " CASE WHEN :sort = 'price_asc' THEN price END ASC,"//
			+ " CASE WHEN :sort = 'price_desc' THEN price END DESC", nativeQuery = true)
	public List<Product> search(@Param("name") String name, @Param("type") Integer type,
			@Param("lowPrice") Integer lowPrice, @Param("highPrice") Integer highPrice, @Param("sort") String sort);

	// 將state設定為(-1:移除)
	@Modifying
	@Transactional
	@Query(value = "UPDATE product"//
			+ " SET state = -1"//
			+ " WHERE product_id = :id", nativeQuery = true)
	public int updateState(@Param("id") int productId);

	// 更新商品
	@Modifying
	@Transactional
	@Query(value = "UPDATE product"//
			+ " SET name = :name,"//
			+ " description = :description,"//
			+ " type = :type,"//
			+ " photo = :photo,"//
			+ " price = :price,"//
			+ " inventory = :inventory,"//
			+ " state = :state"//
			+ " WHERE product_id = :id", nativeQuery = true)
	public int updateProduct(@Param("id") int productId, @Param("name") String name,
			@Param("description") String description, @Param("type") int type, @Param("photo") byte[] photo,
			@Param("price") int price, @Param("inventory") int inventory, @Param("state") int state);

}
