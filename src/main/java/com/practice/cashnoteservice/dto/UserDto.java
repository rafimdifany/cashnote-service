package com.practice.cashnoteservice.dto;


import com.practice.cashnoteservice.entity.Balance;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {

    private Long id;

    private String username;

    private String password;

    private String email;

    private String phoneNumber;

    private Balance balance;
}
