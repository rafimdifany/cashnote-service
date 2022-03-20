package com.practice.cashnoteservice.configuration.mapper;

import com.practice.cashnoteservice.dto.BalanceTransactionDto;
import com.practice.cashnoteservice.entity.BalanceTransaction;
import com.practice.cashnoteservice.entity.BalanceTransaction_;
import dev.akkinoc.spring.boot.orika.OrikaMapperFactoryConfigurer;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class BalanceTransactionMapper implements OrikaMapperFactoryConfigurer {
    @Override
    public void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(BalanceTransactionDto.class, BalanceTransaction.class)
                .fieldBToA(BalanceTransaction_.user.getName(), "user")
                .mapNulls(false)
                .byDefault().register();
    }
}
