package com.example.secHandShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.secHandShop.entity.Record;

@Repository
public interface RecordDao extends JpaRepository<Record, Integer>{

}
