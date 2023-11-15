package com.boot.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.boot.demo.model.Services;

@Repository
public interface ServicesRepository extends CrudRepository<Services, Integer> {

}
