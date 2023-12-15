package com.account_management.service;

import com.account_management.payload.PlanDto;

import java.util.List;

public interface PlanService {
    PlanDto getPlanById(Long id);

    List<PlanDto> getAllPlans();

    PlanDto createPlan(PlanDto planDto);

    PlanDto updatePlan(Long id, PlanDto planDto);

    void deletePlan(Long id);

}
