package com.boot.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.boot.demo.model.MyUserDetails;
import com.boot.demo.model.Role;
import com.boot.demo.model.User;
import com.boot.demo.model.UserRole;
import com.boot.demo.repository.UserRepository;
import com.boot.demo.repository.UserRoleRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = this.userRepository.findByEmail(username);
		if (user==null) {
			throw new UsernameNotFoundException("User not found with username: "+username);
		}
		
		try {
	
			List<UserRole> userRoles = this.userRoleRepository.findByIdUserId(username);
			List<Role> roles = new ArrayList<>();
			
			for (UserRole ur : userRoles) {
				Role r = new Role();
				r.setId(ur.getRoleId());
				roles.add(r);
			}
			
			return new MyUserDetails(user, roles);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

}
