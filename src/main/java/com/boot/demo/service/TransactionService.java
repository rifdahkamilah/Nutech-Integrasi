package com.boot.demo.service;

import com.boot.demo.model.Transaction;

public interface TransactionService {
	
	public Transaction addTransaction(Transaction transaction);
	
	public Transaction performTransaction(String userEmail, String service_code, Integer total_amount);
	
	public String generateInvoiceNumber();
	
	public boolean isBalanceSufficient(String userEmail, Integer total_amount);

}
