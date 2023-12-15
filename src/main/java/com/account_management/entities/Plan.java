package com.account_management.entities;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "plans")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAN_ID")
    private Long id;

    @Column(name = "PLAN_NAME")
    private String name;
    @Column(name = "PRICE")
    private double price;
    @Column(name = "DESCRIPTION")
    private String description;

}
