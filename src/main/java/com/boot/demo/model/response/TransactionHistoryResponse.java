package com.boot.demo.model.response;

import java.util.List;

import com.boot.demo.model.TransactionHistory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionHistoryResponse {
    private int offset;
    private Integer limit;
    private List<TransactionHistory> records;
}
