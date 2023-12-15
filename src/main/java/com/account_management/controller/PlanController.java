package com.account_management.controller;

import com.account_management.payload.PlanDto;
import com.account_management.service.PlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanDto> getPlanById(@PathVariable Long id) {
        PlanDto planDto = planService.getPlanById(id);
        return planDto != null ? ResponseEntity.ok(planDto) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<PlanDto>> getAllPlans() {
        List<PlanDto> plans = planService.getAllPlans();
        return ResponseEntity.ok(plans);
    }

    @PostMapping
    public ResponseEntity<PlanDto> createPlan(@RequestBody PlanDto planDto) {
        PlanDto createdPlan = planService.createPlan(planDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanDto> updatePlan(@PathVariable Long id, @RequestBody PlanDto planDto) {
        PlanDto updatedPlan = planService.updatePlan(id, planDto);
        return updatedPlan != null ? ResponseEntity.ok(updatedPlan) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
        planService.deletePlan(id);
        return ResponseEntity.noContent().build();
    }
}
