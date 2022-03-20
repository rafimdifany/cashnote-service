package com.practice.cashnoteservice.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal totalBalance;

    private BigDecimal digitalBalance;

    private BigDecimal cashBalance;

    private BigDecimal saving;

}
