package com.boot.demo.model.response;

import java.util.List;

import com.boot.demo.dto.BannerDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HttpResponseModel<D> {
	private int status;
	private String message;
	private D data;
}