package com.practice.cashnoteservice.service;

import com.practice.cashnoteservice.dto.BalanceDto;
import com.practice.cashnoteservice.entity.Balance;
import com.practice.cashnoteservice.repository.BalanceRepository;
import javassist.NotFoundException;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {

    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    MapperFacade mapperFacade;

    private static final String NOT_FOUND = "Data Not Found";

    public Page<BalanceDto> getAll(Pageable pageable) {
        return balanceRepository.findAll(pageable).map(balance -> mapperFacade.map(balance, BalanceDto.class));
    }

    public BalanceDto findById(Long id) throws NotFoundException {
        return mapperFacade.map(getById(id), BalanceDto.class);
    }

    public BalanceDto save(BalanceDto balanceDto) {
        Balance balance = mapperFacade.map(balanceDto, Balance.class);
        Balance savedEntity = balanceRepository.save(balance);
        return mapperFacade.map(savedEntity, BalanceDto.class);
    }

    public BalanceDto update(Long id, BalanceDto balanceDto) throws NotFoundException {
        Balance balance = getById(id);
        mapperFacade.map(balanceDto, balance);
        Balance savedEntity = balanceRepository.save(balance);
        return mapperFacade.map(savedEntity, BalanceDto.class);
    }

    private Balance getById(Long id) throws NotFoundException {
        return balanceRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));
    }
}
