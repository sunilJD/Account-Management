package com.account_management.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminBalanceDto {
    private Long id;
    private double balance;
    private Date updatedAt;
}
