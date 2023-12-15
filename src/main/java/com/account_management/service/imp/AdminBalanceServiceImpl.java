package com.account_management.service.imp;

import com.account_management.entities.AdminBalance;
import com.account_management.payload.AdminBalanceDto;
import com.account_management.repository.AdminBalanceRepository;
import com.account_management.service.AdminBalanceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminBalanceServiceImpl implements AdminBalanceService {

    private final AdminBalanceRepository adminBalanceRepository;
    private final ModelMapper modelMapper;

    public AdminBalanceServiceImpl(AdminBalanceRepository adminBalanceRepository, ModelMapper modelMapper) {
        this.adminBalanceRepository = adminBalanceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AdminBalanceDto getAdminBalanceById(Long id) {
        Optional<AdminBalance> optionalAdminBalance = adminBalanceRepository.findById(id);
        return optionalAdminBalance.map(this::mapToDto).orElse(null);
    }

    @Override
    public List<AdminBalanceDto> getAllAdminBalances() {
        List<AdminBalance> adminBalances = adminBalanceRepository.findAll();
        return adminBalances.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public AdminBalanceDto createAdminBalance(AdminBalanceDto adminBalanceDto) {
        AdminBalance adminBalance = mapToEntity(adminBalanceDto);
        AdminBalance savedAdminBalance = adminBalanceRepository.save(adminBalance);
        return mapToDto(savedAdminBalance);
    }

    @Override
    public AdminBalanceDto updateAdminBalance(Long id, AdminBalanceDto adminBalanceDto) {
        Optional<AdminBalance> optionalAdminBalance = adminBalanceRepository.findById(id);
        if (optionalAdminBalance.isPresent()) {
            AdminBalance existingAdminBalance = optionalAdminBalance.get();
            BeanUtils.copyProperties(adminBalanceDto, existingAdminBalance);
            AdminBalance updatedAdminBalance = adminBalanceRepository.save(existingAdminBalance);
            return mapToDto(updatedAdminBalance);
        }
        return null;
    }

    @Override
    public void deleteAdminBalance(Long id) {
        adminBalanceRepository.deleteById(id);
    }

    private AdminBalanceDto mapToDto(AdminBalance adminBalance) {
        return modelMapper.map(adminBalance, AdminBalanceDto.class);
    }

    private AdminBalance mapToEntity(AdminBalanceDto adminBalanceDto) {
        return modelMapper.map(adminBalanceDto, AdminBalance.class);
    }
}
