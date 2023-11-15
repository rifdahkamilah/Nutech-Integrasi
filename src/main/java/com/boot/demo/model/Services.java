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
@Table(name = "t_services")
public class Services{
	
	@Id
	private Integer id;
	
	@Column("SERVICE_CODE")
	private String service_code;
	
	@Column("SERVICE_NAME")
	private String service_name;
	
	@Column("SERVICE_ICON")
	private String service_icon;
	
	@Column("SERVICE_TARIF")
	private Integer service_tarif;
}

