package com.boot.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.boot.demo.model.TransactionHistory;

@Repository
public interface TransactionHistoryRepository extends CrudRepository<TransactionHistory, Integer> {

	public List<TransactionHistory> findByUserEmailOrderByCreatedOnDesc(String userEmail, org.springframework.data.domain.Pageable pageable);
}

