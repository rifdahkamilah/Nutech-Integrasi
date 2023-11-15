package com.boot.demo.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boot.demo.model.Balance;
import com.boot.demo.model.Transaction;
import com.boot.demo.repository.BalanceRepository;
import com.boot.demo.repository.TransactionRepository;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private BalanceRepository balanceRepository;

	@Override
	public Transaction addTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	@Override
	public Transaction performTransaction(String userEmail, String service_code, Integer total_amount) {

		String transaction_type = "PAYMENT";
		String invoice_number = generateInvoiceNumber();

		Transaction transaction = new Transaction();
		transaction.setUserEmail(userEmail);
		transaction.setService_code(service_code);
		transaction.setTransaction_type(transaction_type);
		transaction.setTotal_amount(total_amount);
		transaction.setInvoice_number(invoice_number);
		transaction.setCreatedOn(new Date());
		
		return transactionRepository.save(transaction);
	}

	@Override
	public boolean isBalanceSufficient(String userEmail, Integer total_amount) {
		Optional<Balance> op = balanceRepository.findByUserEmail(userEmail);

		if (op.isPresent()) {
			Balance balance = op.get();
			return balance.getBalance() >= total_amount;
		}

		return false;
	}

	@Override
	public String generateInvoiceNumber() {
		return "INV" + System.currentTimeMillis();
	}
}
