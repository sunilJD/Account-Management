package com.account_management.service;

import com.account_management.payload.AdminBalanceDto;

import java.util.List;

public interface AdminBalanceService {
    AdminBalanceDto getAdminBalanceById(Long id);

    List<AdminBalanceDto> getAllAdminBalances();

    AdminBalanceDto createAdminBalance(AdminBalanceDto adminBalanceDto);

    AdminBalanceDto updateAdminBalance(Long id, AdminBalanceDto adminBalanceDto);

    void deleteAdminBalance(Long id);
}
