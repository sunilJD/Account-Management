package com.account_management.controller;

import com.account_management.payload.AdminBalanceDto;
import com.account_management.service.AdminBalanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin-balances")
public class AdminBalanceController {

    private final AdminBalanceService adminBalanceService;

    public AdminBalanceController(AdminBalanceService adminBalanceService) {
        this.adminBalanceService = adminBalanceService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminBalanceDto> getAdminBalanceById(@PathVariable Long id) {
        AdminBalanceDto adminBalanceDto = adminBalanceService.getAdminBalanceById(id);
        return adminBalanceDto != null ? ResponseEntity.ok(adminBalanceDto) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<AdminBalanceDto>> getAllAdminBalances() {
        List<AdminBalanceDto> adminBalances = adminBalanceService.getAllAdminBalances();
        return ResponseEntity.ok(adminBalances);
    }

    @PostMapping
    public ResponseEntity<AdminBalanceDto> createAdminBalance(@RequestBody AdminBalanceDto adminBalanceDto) {
        AdminBalanceDto createdAdminBalance = adminBalanceService.createAdminBalance(adminBalanceDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdminBalance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminBalanceDto> updateAdminBalance(@PathVariable Long id, @RequestBody AdminBalanceDto adminBalanceDto) {
        AdminBalanceDto updatedAdminBalance = adminBalanceService.updateAdminBalance(id, adminBalanceDto);
        return updatedAdminBalance != null ? ResponseEntity.ok(updatedAdminBalance) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdminBalance(@PathVariable Long id) {
        adminBalanceService.deleteAdminBalance(id);
        return ResponseEntity.noContent().build();
    }
}
