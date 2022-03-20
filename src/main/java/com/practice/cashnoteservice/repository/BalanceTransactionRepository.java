package com.practice.cashnoteservice.repository;

import com.practice.cashnoteservice.entity.BalanceTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceTransactionRepository extends JpaRepository<BalanceTransaction, Long> {
}
