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
@Table(name = "t_topup")
public class TopUpRequest {
	
	@Id
	private Integer id;
	
	@Column("TOP_UP_AMOUNT")
	private Integer top_up_amount;
	
}
