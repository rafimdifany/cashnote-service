package com.practice.cashnoteservice.service;

import com.practice.cashnoteservice.dto.BalanceTransactionDto;
import com.practice.cashnoteservice.entity.Balance;
import com.practice.cashnoteservice.entity.BalanceTransaction;
import com.practice.cashnoteservice.entity.User;
import com.practice.cashnoteservice.entity.enums.BalanceType;
import com.practice.cashnoteservice.entity.enums.TransactionType;
import com.practice.cashnoteservice.repository.BalanceRepository;
import com.practice.cashnoteservice.repository.BalanceTransactionRepository;
import com.practice.cashnoteservice.repository.UserRepository;
import javassist.NotFoundException;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class BalanceTransactionService {

    @Autowired
    BalanceTransactionRepository balanceTransactionRepository;

    @Autowired
    MapperFacade mapperFacade;

    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    UserRepository userRepository;

    private static final String NOT_FOUND = "Data Not Found";

    public Page<BalanceTransactionDto> getAll(Pageable pageable) {

    return balanceTransactionRepository.findAll(pageable)
            .map(balanceTransaction -> mapperFacade.map(balanceTransaction, BalanceTransactionDto.class));
    }

    public BalanceTransactionDto findById(Long id) throws NotFoundException {
        return mapperFacade.map(getById(id), BalanceTransactionDto.class);
    }

    @Transactional
    public BalanceTransactionDto create(BalanceTransactionDto balanceTransactionDto) {
        BalanceTransaction balanceTransaction = mapperFacade.map(balanceTransactionDto, BalanceTransaction.class);

        User user = userRepository.getById(balanceTransactionDto.getUser().getId());
        Long balanceId = user.getBalance().getId();
        Balance balance = balanceRepository.getById(balanceId);

        if(balanceTransactionDto.getTransactionType().equals(TransactionType.INCOME)) {
            BalanceType balanceType = balanceTransactionDto.getBalanceType();
            switch (balanceType) {
                case DIGITAL_BALANCE:
                    balance.setDigitalBalance(balance.getDigitalBalance().add(balanceTransactionDto.getTransactionAmount()));
                    balance.setTotalBalance(balance.getDigitalBalance().add(balance.getCashBalance()));
                    break;
                case CASH_BALANCE:
                    balance.setCashBalance(balance.getCashBalance().add(balanceTransactionDto.getTransactionAmount()));
                    balance.setTotalBalance(balance.getDigitalBalance().add(balance.getCashBalance()));
                    break;
                case SAVING:
                    balance.setSaving(balance.getSaving().add(balanceTransactionDto.getTransactionAmount()));
                    break;
            }
        }

        if (balanceTransactionDto.getTransactionType().equals(TransactionType.OUTCOME)) {
            BalanceType balanceType = balanceTransactionDto.getBalanceType();
            switch (balanceType) {
                case DIGITAL_BALANCE:
                    balance.setDigitalBalance(balance.getDigitalBalance().subtract(balanceTransactionDto.getTransactionAmount()));
                    balance.setTotalBalance(balance.getDigitalBalance().add(balance.getCashBalance()));
                    break;
                case CASH_BALANCE:
                    balance.setCashBalance(balance.getCashBalance().subtract(balanceTransactionDto.getTransactionAmount()));
                    balance.setTotalBalance(balance.getDigitalBalance().add(balance.getCashBalance()));
                    break;
                case SAVING:
                    balance.setSaving(balance.getSaving().add(balanceTransactionDto.getTransactionAmount()));
                    break;
            }
        }

        balanceRepository.save(balance);

        balanceTransaction.setUser(user);
        BalanceTransaction savedEntity = balanceTransactionRepository.save(balanceTransaction);
        return mapperFacade.map(savedEntity, BalanceTransactionDto.class);
    }

    private BalanceTransaction getById(Long id) throws NotFoundException {
        return balanceTransactionRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));
    }
}
