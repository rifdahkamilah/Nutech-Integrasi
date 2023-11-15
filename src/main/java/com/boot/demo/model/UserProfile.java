package com.boot.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_userprofile")
public class UserProfile {
	@Id
	@Column("PROFILEID")
	private Integer id;
	
	@Column("PROFILE_EMAIL")
	private String email;
	
	@Column("PROFILE_FIRST_NAME")
	private String first_name;
	
	@Column("PROFILE_LAST_NAME")
	private String last_name;
	
	@Column("PROFILE_IMAGE")
	private String profile_image;
}
