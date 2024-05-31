package com.example.secHandShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.secHandShop.entity.RecordItem;

@Repository
public interface RecordItemDao extends JpaRepository<RecordItem, Integer> {

}
