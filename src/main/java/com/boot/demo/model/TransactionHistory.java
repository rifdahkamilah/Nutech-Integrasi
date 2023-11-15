package com.boot.demo.model;

import java.time.LocalDateTime;

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
@Table(name = "t_transactionhistory")
public class TransactionHistory {
	
    @Id
    private Integer id;

	@Column("USER_EMAIL")
    private String userEmail;
	
    @Column("INVOICE_NUMBER")
    private String invoice_number;

    @Column("TRANSACTION_TYPE")
    private String transaction_type;

    @Column("DESCRIPTION")
    private String description;

    @Column("TOTAL_AMOUNT")
    private Integer total_amount;

    @Column("CREATED_ON")
    private LocalDateTime createdOn;
}
