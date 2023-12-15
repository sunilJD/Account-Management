package com.account_management.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlanDto {
    private Long id;
    private String name;
    private double price;
    private String description;

}
