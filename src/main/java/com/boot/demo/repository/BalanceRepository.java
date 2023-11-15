package com.boot.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.boot.demo.model.Balance;

@Repository
public interface BalanceRepository extends CrudRepository<Balance, Integer> {

	public Optional<Balance> findByUserEmail(String userEmail);

}
