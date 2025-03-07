package com.desert.gym.services;

import com.desert.gym.dtos.plan.PlanCreateDto;
import com.desert.gym.models.Plan;
import com.desert.gym.repositories.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    public Plan createPlan(PlanCreateDto createDto) {

        Plan plan = new Plan();


        plan.setName(createDto.getName());
        plan.setDescription(createDto.getDescription());
        plan.setDuration(createDto.getDuration());
        plan.setPrice(createDto.getPrice());
        plan.setActive(createDto.isActive());

        return planRepository.save(plan);
    }
}
