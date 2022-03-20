package com.practice.cashnoteservice.service;

import com.practice.cashnoteservice.dto.UserDto;
import com.practice.cashnoteservice.entity.Balance;
import com.practice.cashnoteservice.entity.User;
import com.practice.cashnoteservice.repository.BalanceRepository;
import com.practice.cashnoteservice.repository.UserRepository;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MapperFacade mapperFacade;

    @Autowired
    BalanceService balanceService;

    @Autowired
    BalanceRepository balanceRepository;

    public Page<UserDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(user -> mapperFacade.map(user, UserDto.class));
    }

    public UserDto create(UserDto userDto) {

        Balance balanceUser = Balance.builder()
                .totalBalance(BigDecimal.ZERO)
                .digitalBalance(BigDecimal.ZERO)
                .cashBalance(BigDecimal.ZERO)
                .saving(BigDecimal.ZERO)
                .build();
        balanceRepository.save(balanceUser);

        User user = mapperFacade.map(userDto, User.class);
        User savedEntity = userRepository.save(user);

        savedEntity.setBalance(balanceUser);
        return mapperFacade.map(savedEntity, UserDto.class);
    }



}
