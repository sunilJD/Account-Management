package com.account_management.service.imp;

import com.account_management.entities.Plan;
import com.account_management.payload.PlanDto;
import com.account_management.repository.PlanRepository;
import com.account_management.service.PlanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    private final ModelMapper modelMapper;

    public PlanServiceImpl(PlanRepository planRepository, ModelMapper modelMapper) {
        this.planRepository = planRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PlanDto getPlanById(Long id) {
        Optional<Plan> optionalPlan = planRepository.findById(id);
        return optionalPlan.map(this::mapToDto).orElse(null);
    }

    @Override
    public List<PlanDto> getAllPlans() {
        List<Plan> plans = planRepository.findAll();
        return plans.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public PlanDto createPlan(PlanDto planDto) {
        Plan plan = mapToEntity(planDto);
        Plan savedPlan = planRepository.save(plan);
        return mapToDto(savedPlan);
    }

    @Override
    public PlanDto updatePlan(Long id, PlanDto planDto) {
        Optional<Plan> optionalPlan = planRepository.findById(id);
        if (optionalPlan.isPresent()) {
            Plan existingPlan = optionalPlan.get();
            BeanUtils.copyProperties(planDto, existingPlan);
            Plan updatedPlan = planRepository.save(existingPlan);
            return mapToDto(updatedPlan);
        }
        return null;
    }

    @Override
    public void deletePlan(Long id) {
        planRepository.deleteById(id);
    }

    private PlanDto mapToDto(Plan plan) {
        return modelMapper.map(plan, PlanDto.class);
    }

    private Plan mapToEntity(PlanDto planDto) {
        return modelMapper.map(planDto, Plan.class);
    }
}
