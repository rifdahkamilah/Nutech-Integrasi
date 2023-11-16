package com.boot.demo.controller;

import java.io.File;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.boot.demo.config.JwtTokenUtil;
import com.boot.demo.config.MyUserDetailsService;
import com.boot.demo.dto.UserDto;
import com.boot.demo.exception.BadRequestException;
import com.boot.demo.exception.UnauthorizedException;
import com.boot.demo.exception.UserAlreadyExistsException;
import com.boot.demo.model.User;
import com.boot.demo.model.UserProfile;
import com.boot.demo.model.request.JwtRequest;
import com.boot.demo.model.request.RegistrationRequest;
import com.boot.demo.model.response.HttpResponseModel;
import com.boot.demo.model.response.JwtResponse;
import com.boot.demo.repository.UserProfileRepository;
import com.boot.demo.repository.UserRepository;
import com.boot.demo.service.UserProfileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/")
public class ModuleMembershipController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private UserRepository repo;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Autowired
	private UserProfileService userProfileService;

	@Operation(summary = "Register new user", description = "Registering for new user")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Request Successfully"),
			@ApiResponse(responseCode = "400", description = "Bad Request") })
	@PostMapping("/registration")
	public HttpResponseModel<UserDto> register(@Valid @RequestBody RegistrationRequest req) {
		HttpResponseModel<UserDto> result = new HttpResponseModel<UserDto>();

		try {
			User user = repo.findByEmail(req.getEmail());
			if (user == null) {
		
				repo.save(User.builder().email(req.getEmail()).first_name(req.getFirst_name())
						.last_name(req.getLast_name()).password(encoder.encode(req.getPassword())).build());

				UserDto resp = UserDto.builder().email(req.getEmail()).first_name(req.getFirst_name())
						.last_name(req.getLast_name()).build();

				result.setStatus(0);
				result.setMessage("Registrasi berhasil silahkan login");
				result.setData(null);

				return result;

			} else {
		
				throw new UserAlreadyExistsException(req.getEmail());
			}
		} catch (Exception e) {
			result.setStatus(102);
			result.setMessage("Parameter email tidak sesuai format");
			result.setData(null);

			return result;
		}
	}

	@Operation(summary = "Login", description = "Login using registered email and password")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Berhasil Login"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized") })
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public HttpResponseModel<JwtResponse> createAuthenticationToken(
			@Valid @RequestBody JwtRequest authenticationRequest) throws Exception {
		HttpResponseModel<JwtResponse> result = new HttpResponseModel<JwtResponse>();
		try {
			validateEmailFormat(authenticationRequest.getEmail());
			validatePasswordLength(authenticationRequest.getPassword());

			authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
			final String token = jwtTokenUtil.generateToken(userDetails);

			result.setStatus(0);
			result.setMessage("Login Sukses");
			result.setData(new JwtResponse(token));

		} catch (BadRequestException e) {
			result.setStatus(102);
			result.setMessage("Parameter email tidak sesuai format");
			result.setData(null);

		} catch (UnauthorizedException e) {
			result.setStatus(103);
			result.setMessage("Username atau password salah");
			result.setData(null);

		}

		return result;
	}

	private void validateEmailFormat(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

		if (!email.matches(emailRegex)) {
			throw new BadRequestException("Parameter email tidak sesuai format");
		}
	}

	private void validatePasswordLength(String password) {
		if (password.length() < 8) {
			throw new UnauthorizedException("Password harus memiliki panjang minimal 8 karakter");
		}
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@Operation(summary = "Get User Profile", description = "Get user profile information")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Request Successfully"),
			@ApiResponse(responseCode = "401", description = "Unauthorized") })
	@GetMapping("/profile")
	@SecurityRequirement(name = "nutechintegrasi")
	public HttpResponseModel<UserProfile> getUserProfile(Authentication authentication) {
		HttpResponseModel<UserProfile> result = new HttpResponseModel<>();

		try {
			if (authentication != null) {
				String userEmail = authentication.name();
				UserProfile userProfile = userProfileService.getUserProfileByEmail(userEmail);

				result.setStatus(0);
				result.setMessage("Sukses");
				result.setData(userProfile);
			} else {
				result.setStatus(401);
				result.setMessage("Unauthorized");
				result.setData(null);
			}
		} catch (Exception e) {
			result.setStatus(108);
			result.setMessage("Token tidak valid atau kadaluwarsa");
			result.setData(null);
		}

		return result;
	}

	@Operation(summary = "Update User Profile", description = "Update user profile information")
	@PutMapping("/profile/update")
	@SecurityRequirement(name = "nutechintegrasi")
	public HttpResponseModel<UserProfile> updateProfile(@RequestBody UserProfile updatedProfile,
			Authentication authentication) {
		HttpResponseModel<UserProfile> result = new HttpResponseModel<>();

		try {
			String userEmail = authentication.name();

			UserProfile existingProfile = userProfileService.getUserProfileByEmail(userEmail);

			existingProfile.setFirst_name(updatedProfile.getFirst_name());
			existingProfile.setLast_name(updatedProfile.getLast_name());

			UserProfile savedProfile = userProfileService.updateUserProfile(existingProfile);

			result.setStatus(0);
			result.setMessage("Update Profile berhasil");
			result.setData(savedProfile);
		} catch (Exception e) {
			result.setStatus(108);
			result.setMessage("Token tidak valid atau kadaluwarsa");
			result.setData(null);
		}

		return result;
	}

	@Operation(summary = "Upload User Profile Image", description = "Upload user profile image")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Request Successfully"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized") })
	@PutMapping(value = "/profile/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	@SecurityRequirement(name = "nutechintegrasi")
	public HttpResponseModel<UserProfile> uploadProfileImage(@RequestParam("file") MultipartFile file,
			Authentication authentication) {
		HttpResponseModel<UserProfile> result = new HttpResponseModel<>();

		try {
			String userEmail = authentication.name();

			if (!file.getContentType().equals("image/jpeg") && !file.getContentType().equals("image/png")) {
				result.setStatus(102);
				result.setMessage("Format Image tidak sesuai");
				result.setData(null);
				return result;
			}

			UserProfile existingProfile = userProfileService.getUserProfileByEmail(userEmail);

			String fileName = "profile-image-" + userEmail + "." + getFileExtension(file.getOriginalFilename());
			String filePath = "uploads/" + fileName; 
			file.transferTo(new File(filePath));

			existingProfile.setProfile_image(filePath);

			UserProfile savedProfile = userProfileService.updateUserProfile(existingProfile);

			result.setStatus(0);
			result.setMessage("Update Profile Image berhasil");
			result.setData(savedProfile);
		} catch (Exception e) {
			result.setStatus(108);
			result.setMessage("Token tidak valid atau kadaluwarsa");
			result.setData(null);
		}

		return result;
	}

	private String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}
}
