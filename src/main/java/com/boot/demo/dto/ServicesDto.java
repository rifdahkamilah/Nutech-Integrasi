package com.boot.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServicesDto{

	private Integer id;
	private String service_code;
	private String service_name;
	private String service_icon;
	private Integer service_tarif;
}

