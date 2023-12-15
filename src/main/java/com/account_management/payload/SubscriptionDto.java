package com.account_management.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscriptionDto {
    private Long id;
    private Long userId;
    private Long planId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long paymentGatewayId;
    private String paymentStatus;

}
