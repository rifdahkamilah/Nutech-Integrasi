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
@Table(name = "t_balance")
public class Balance {
	
	@Id
	private Integer id;
	
	@Column("USER_EMAIL")
	private String userEmail;
	
	@Column("BALANCE")
    private Integer balance;
	
	@Column("TRANSACTION_TYPE")
    private String transaction_type;
}
