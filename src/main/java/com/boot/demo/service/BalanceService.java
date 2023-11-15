package com.boot.demo.service;

import java.util.Optional;

import com.boot.demo.model.Balance;

public interface BalanceService {
	public Optional<Balance> getBalanceByUserEmail(String userEmail);
	public Optional<Balance> topUpBalance(String userEmail, Integer top_up_amount);

}
