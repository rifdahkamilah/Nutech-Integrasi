package com.boot.demo.service;

import java.util.List;

import com.boot.demo.model.TransactionHistory;

public interface TransactionHistoryService {

	public List<TransactionHistory> getTransactionHistory(String userEmail, Integer offset, Integer limit);

}
