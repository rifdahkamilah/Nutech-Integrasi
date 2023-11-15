package com.boot.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.demo.model.Balance;
import com.boot.demo.repository.BalanceRepository;

@Service
public class BalanceServiceImpl implements BalanceService {
	
	@Autowired
	private BalanceRepository balanceRepository;

	@Override
	public Optional<Balance> getBalanceByUserEmail(String userEmail) {
		return balanceRepository.findByUserEmail(userEmail);
	}

	@Override
	public Optional<Balance> topUpBalance(String userEmail, Integer top_up_amount) {

		Optional<Balance> optionalBalance = balanceRepository.findByUserEmail(userEmail);

		if (optionalBalance.isPresent()) {
			Balance balance = optionalBalance.get();

			Integer currentBalance = balance.getBalance();
			if (currentBalance != null) {
				balance.setBalance(currentBalance + top_up_amount);
				balance.setTransaction_type("TOPUP");
				balanceRepository.save(balance);

				return Optional.of(balance);
			}
		}

		return Optional.empty();
	}
}