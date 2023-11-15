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
@Table(name = "t_banner")
public class Banner {
	
	@Id
	private Integer id;
	
	@Column("BANNER_NAME")
	private String banner_name;
	
	@Column("BANNER_IMAGE")
	private String banner_image;
	
	@Column("DESCRIPTION")
	private String description;
}
