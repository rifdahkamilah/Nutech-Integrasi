package com.boot.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BannerDto {
	
	private Integer id;
	private String banner_name;
	private String banner_image;
	private String description;
}
