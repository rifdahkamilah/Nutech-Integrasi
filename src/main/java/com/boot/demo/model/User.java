package com.boot.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_user")
public class User {
	
	@Id
	private Integer id;
	
	@Column("EMAIL")
	@NotBlank(message = "Email tidak boleh kosong")
	@Email(message = "Format email tidak valid")
	private String email;
	
	@Column("FIRST_NAME")
	private String first_name;
	
	@Column("LAST_NAME")
	private String last_name;
	
	@Column("PASSWORD")
	@NotBlank(message = "Email tidak boleh kosong")
	@Size(min = 8, message = "Password harus terdiri minimal 8 karakter")
	private String password;
}
