package com.boot.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.boot.demo.model.TransactionHistory;
import com.boot.demo.repository.TransactionHistoryRepository;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {
	@Autowired
	private TransactionHistoryRepository transactionHistoryRepository;

	@Override
	public List<TransactionHistory> getTransactionHistory(String userEmail, Integer offset, Integer limit) {
		Pageable pageable = PageRequest.of(offset, limit, Sort.by("createdOn").descending());
		return transactionHistoryRepository.findByUserEmailOrderByCreatedOnDesc(userEmail, pageable);
	}
}