package com.account_management.repository;

import com.account_management.entities.AdminBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminBalanceRepository extends JpaRepository<AdminBalance, Long> {
}
