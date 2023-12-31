package com.boot.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.boot.demo.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	public User findByEmail(String email);

}
