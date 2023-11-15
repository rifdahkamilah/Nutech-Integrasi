package com.boot.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.boot.demo.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {

}
