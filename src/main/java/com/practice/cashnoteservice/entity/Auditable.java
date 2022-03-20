package com.practice.cashnoteservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Auditable implements Serializable {

    private static final long serialVersionUID = 1L;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @CreationTimestamp
    private LocalDateTime lastModifiedDate;
}
