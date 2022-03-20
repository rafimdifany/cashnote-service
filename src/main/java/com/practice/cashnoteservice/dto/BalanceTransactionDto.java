package com.practice.cashnoteservice.dto;

import com.practice.cashnoteservice.entity.Auditable;
import com.practice.cashnoteservice.entity.User;
import com.practice.cashnoteservice.entity.enums.BalanceType;
import com.practice.cashnoteservice.entity.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BalanceTransactionDto {
    private Long id;

    private TransactionType transactionType;

    private BalanceType balanceType;

    private BigDecimal transactionAmount;

    private User user;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime lsatModifiedDate;
}
