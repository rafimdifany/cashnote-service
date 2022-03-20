package com.practice.cashnoteservice.configuration.mapper;

import com.practice.cashnoteservice.dto.BalanceDto;
import com.practice.cashnoteservice.entity.Balance;
import com.practice.cashnoteservice.entity.Balance_;
import dev.akkinoc.spring.boot.orika.OrikaMapperFactoryConfigurer;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class BalanceMapper implements OrikaMapperFactoryConfigurer {

    @Override
    public void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(BalanceDto.class, Balance.class)
                .mapNulls(false)
                .byDefault().register();
    }
}
