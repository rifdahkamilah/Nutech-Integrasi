package com.boot.demo.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.demo.dto.BannerDto;
import com.boot.demo.dto.ServicesDto;
import com.boot.demo.model.Banner;
import com.boot.demo.model.Services;
import com.boot.demo.model.response.HttpResponseModel;
import com.boot.demo.repository.BannerRepository;
import com.boot.demo.repository.ServicesRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.PermitAll;

@RestController
@CrossOrigin
@RequestMapping("/")
@SecurityRequirement(name = "nutechintegrasi")
public class ModuleInformationController {

	@Autowired
	private BannerRepository bannerRepository;

	@Autowired
	private ServicesRepository servicesRepository;

	@Operation(summary = "List all banners", description = "Get the list of all banners")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Request Successfully"),
			@ApiResponse(responseCode = "401", description = "Unauthorized") })
	@PermitAll
	@GetMapping("/banner")
	public HttpResponseModel<List<BannerDto>> getAllBanners() {
		HttpResponseModel<List<BannerDto>> result = new HttpResponseModel<>();
		List<BannerDto> banners = StreamSupport.stream(bannerRepository.findAll().spliterator(), true)
				.map(this::convertToDto).collect(Collectors.toList());

		try {
			result.setStatus(0);
			result.setMessage("Sukses");
			result.setData(banners);
		} catch (Exception e) {
			result.setStatus(108);
			result.setMessage("Token tidak valid atau kadaluwarsa");
			result.setData(null);
		}

		return result;
	}

	private BannerDto convertToDto(Banner banner) {
		return BannerDto.builder().id(banner.getId()).banner_name(banner.getBanner_name())
				.banner_image(banner.getBanner_image()).description(banner.getDescription()).build();
	}
	

	@Operation(summary = "List all services", description = "Get the list of all services")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Request Successfully"),
			@ApiResponse(responseCode = "401", description = "Unauthorized") })
	@PermitAll
	@GetMapping("/services")
	public HttpResponseModel<List<ServicesDto>> getAllServices() {
		HttpResponseModel<List<ServicesDto>> result = new HttpResponseModel<>();
		List<ServicesDto> serv = StreamSupport.stream(servicesRepository.findAll().spliterator(), true)
				.map(this::convertToDto).collect(Collectors.toList());

		try {
			
			result.setStatus(0);
			result.setMessage("Sukses");
			result.setData(serv);
			
		} catch (Exception e) {

			result.setStatus(108);
			result.setMessage("Token tidak valid atau kadaluwarsa");
			result.setData(null);
		}

		return result;
	}

	private ServicesDto convertToDto(Services services) {
		return ServicesDto.builder().id(services.getId()).service_code(services.getService_code())
				.service_name(services.getService_name()).service_icon(services.getService_icon())
				.service_tarif(services.getService_tarif()).build();
	}

}
