package com.practice.cashnoteservice.dto;

import com.practice.cashnoteservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BalanceDto {

    private Long id;

    private BigDecimal totalBalance;

    private BigDecimal digitalBalance;

    private BigDecimal cashBalance;

    private BigDecimal saving;



}
