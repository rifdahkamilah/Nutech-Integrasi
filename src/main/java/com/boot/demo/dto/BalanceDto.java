package com.boot.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BalanceDto {
	
	private Integer id;
	private String userEmail;
    private Integer balance;
    private String transaction_type;
}
