package com.boot.demo.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.boot.demo.model.UserRole;
import com.boot.demo.model.UserRoleId;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, UserRoleId>  {
	
	@Query("select * from userrole where email=:email")
	public List<UserRole> findByIdUserId(@Param("email") String userId);

}
