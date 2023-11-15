package com.boot.demo.controller;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boot.demo.dto.BalanceDto;
import com.boot.demo.model.Balance;
import com.boot.demo.model.TopUpRequest;
import com.boot.demo.model.Transaction;
import com.boot.demo.model.TransactionHistory;
import com.boot.demo.model.TransactionRequest;
import com.boot.demo.model.response.HttpResponseModel;
import com.boot.demo.model.response.TransactionHistoryResponse;
import com.boot.demo.repository.BalanceRepository;
import com.boot.demo.repository.TransactionRepository;
import com.boot.demo.service.BalanceService;
import com.boot.demo.service.TransactionHistoryService;
import com.boot.demo.service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.PermitAll;

@RestController
@CrossOrigin
@RequestMapping("/")
@SecurityRequirement(name = "nutechintegrasi")
public class ModuleTransactionController {
	
	@Autowired
	private BalanceRepository balanceRepository;

	@Autowired
	private BalanceService balanceService;
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private TransactionHistoryService transactionHistoryService;
	
	@Operation(summary = "Get balance", description = "Get balance")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Get Balance / Saldo Berhasil"),
			@ApiResponse(responseCode = "401", description = "Unauthorized") })
	@PermitAll
	@GetMapping("/balance")
	public HttpResponseModel<BalanceDto> getBalance(@AuthenticationPrincipal UserDetails userDetails,
			Authentication authentication) {
		HttpResponseModel<BalanceDto> result = new HttpResponseModel<>();

		try {
			String userEmail = userDetails.getUsername();
			Optional<Balance> balance = balanceRepository.findByUserEmail(userEmail);
			result.setStatus(0);
			result.setMessage("Get Balance Berhasil");
			result.setData(convertToDto(balance.get()));

		} catch (Exception e) {
			result.setStatus(108);
			result.setMessage("Token tidak valid atau kadaluwarsa");
			result.setData(null);
		}

		return result;
	}

	private BalanceDto convertToDto(Balance balance) {
		return BalanceDto.builder()
				.id(balance.getId())
				.userEmail(balance.getUserEmail())
				.balance(balance.getBalance())
				.build();
	}
	
	@Operation(summary = "Top Up", description = "Top Up")
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Request Successfully"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized")
	})
    @PermitAll
    @PostMapping("/topup")
    public ResponseEntity<HttpResponseModel<Balance>> topupBalance(@RequestBody TopUpRequest topUpRequest,
                                          @AuthenticationPrincipal UserDetails userDetails) {
        
        if (topUpRequest.getTop_up_amount() <= 0) {
            return ResponseEntity.badRequest()
                    .body(new HttpResponseModel<>(102, "Parameter amount hanya boleh angka dan tidak boleh lebih kecil dari 0", null));
        }

        String userEmail = userDetails.getUsername();

        Optional<Balance> updatedBalance = balanceService.topUpBalance(userEmail, topUpRequest.getTop_up_amount());

        return updatedBalance.map(value ->
                ResponseEntity.ok(new HttpResponseModel<>(0, "Top Up Balance berhasil", value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new HttpResponseModel<>(500, "Gagal melakukan Top Up", null)));
    }
	
	@Operation(summary = "Perform Transaction", description = "Transaction")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Request Successfully"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized") })
	@PermitAll
	@PostMapping("/transaction")
	public ResponseEntity<HttpResponseModel<Transaction>> performTransaction(
			@RequestBody TransactionRequest transactionRequest, 
			@AuthenticationPrincipal UserDetails userDetails) {
		try {

			String userEmail = userDetails.getUsername();
			Integer total_amount = transactionRequest.getTotal_amount();

			if (!transactionService.isBalanceSufficient(userEmail, total_amount)) {
				return ResponseEntity.badRequest()
						.body(new HttpResponseModel<>(102, "Service atau Layanan tidak ditemukan", null));
			}

			Transaction result = transactionService.performTransaction(userEmail, 
					transactionRequest.getService_code(), total_amount);

			return ResponseEntity.ok(new HttpResponseModel<>(0, "Transaksi berhasil", result));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new HttpResponseModel<>(108, "Token tidak valid atau kadaluwarsa", null));
		}
	}
	
	@Operation(summary = "Transaction History", description = "Transaction History")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Request Successfully"),
			@ApiResponse(responseCode = "401", description = "Unauthorized") })
	@PermitAll
	@GetMapping("/transaction/history")
	public ResponseEntity<HttpResponseModel<TransactionHistoryResponse>> getTransactionHistory(
			@RequestParam(defaultValue = "0") Integer offset, @RequestParam(required = false) Integer limit,
			@AuthenticationPrincipal UserDetails userDetails) {
		try {

			String userEmail = userDetails.getUsername();

			List<TransactionHistory> history = transactionHistoryService.getTransactionHistory(userEmail, offset,
					limit);

			TransactionHistoryResponse result = new TransactionHistoryResponse(offset, limit, history);

			return ResponseEntity.ok(new HttpResponseModel<>(0, "Get History Berhasil", result));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new HttpResponseModel<>(108, "Token tidak valid atau kadaluwarsa", null));
		}
	}

}
