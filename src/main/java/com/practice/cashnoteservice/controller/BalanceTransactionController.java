package com.practice.cashnoteservice.controller;

import com.practice.cashnoteservice.dto.BalanceTransactionDto;
import com.practice.cashnoteservice.service.BalanceTransactionService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balance-transactions")
public class BalanceTransactionController {

    @Autowired
    BalanceTransactionService balanceTransactionService;

    @GetMapping
    public Page<BalanceTransactionDto> getAll(Pageable pageable) {
        return balanceTransactionService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public BalanceTransactionDto getById(@PathVariable Long id) throws NotFoundException {
        return balanceTransactionService.findById(id);
    }

    @PostMapping
    public BalanceTransactionDto create(@RequestBody BalanceTransactionDto balanceTransactionDto) {
        return balanceTransactionService.create(balanceTransactionDto);
    }
}
