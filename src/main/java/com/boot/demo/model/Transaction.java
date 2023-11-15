package com.boot.demo.model;

import java.util.Date;

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
@Table(name = "t_transaction")
public class Transaction {
	
	@Id
    private Integer id;
	
	@Column("USER_EMAIL")
    private String userEmail;

    @Column("INVOICE_NUMBER")
    private String invoice_number;

    @Column("SERVICE_CODE")
    private String service_code;

    @Column("SERVICE_NAME")
    private String service_name;

    @Column("TRANSACTION_TYPE")
    private String transaction_type;

    @Column("TOTAL_AMOUNT")
    private Integer total_amount;

    @Column("CREATED_ON")
    private Date createdOn;
}
