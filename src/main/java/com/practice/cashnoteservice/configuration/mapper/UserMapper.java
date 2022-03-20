package com.practice.cashnoteservice.configuration.mapper;

import com.practice.cashnoteservice.dto.UserDto;
import com.practice.cashnoteservice.entity.User;
import com.practice.cashnoteservice.entity.User_;
import dev.akkinoc.spring.boot.orika.OrikaMapperFactoryConfigurer;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements OrikaMapperFactoryConfigurer {

    @Override
    public void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(UserDto.class, User.class)
                .fieldBToA(User_.balance.getName(), "balance")
                .mapNulls(false)
                .byDefault().register();
    }
}
