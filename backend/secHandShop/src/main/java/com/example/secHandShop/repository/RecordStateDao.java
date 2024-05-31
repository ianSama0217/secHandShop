package com.example.secHandShop.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.secHandShop.entity.RecordState;

@Repository
public interface RecordStateDao extends JpaRepository<RecordState, Integer> {

	public RecordState findByRecordId(int recordId);

	// 將state設定為(-1:取消)
	@Modifying
	@Transactional
	@Query(value = "UPDATE record_state"//
			+ " SET state = -1"//
			+ " WHERE record_id = :id", nativeQuery = true)
	public int cancelRecord(@Param("id") int recordId);

	// 將state設定為(2:賣家寄出商品)
	@Modifying
	@Transactional
	@Query(value = "UPDATE record_state"//
			+ " SET state = 2"//
			+ " WHERE record_id = :id", nativeQuery = true)
	public int sentProduct(@Param("id") int recordId);

	// 將state設定為(3:買家已收到商品/訂單完成)
	@Modifying
	@Transactional
	@Query(value = "UPDATE record_state"//
			+ " SET state = 3"//
			+ " WHERE record_id = :id", nativeQuery = true)
	public int receivedProduct(@Param("id") int recordId);
}
