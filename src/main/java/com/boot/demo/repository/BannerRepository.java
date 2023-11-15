package com.boot.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.boot.demo.model.Banner;

@Repository
public interface BannerRepository extends CrudRepository<Banner, Integer> {

}
