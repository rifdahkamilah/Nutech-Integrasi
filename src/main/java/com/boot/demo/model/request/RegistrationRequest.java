package com.boot.demo.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationRequest {
	@Email(message = "Format email tidak valid")
	private String email;
	
	private String first_name;
	
	private String last_name;
	
	@Size(min = 8, message = "Password harus terdiri minimal 8 karakter")
	private String password;
}
