package com.desert.gym.services;

import com.desert.gym.dtos.plan.PlanCreateDto;
import com.desert.gym.models.Plan;
import com.desert.gym.repositories.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


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
        plan.setActive(true);

        return planRepository.save(plan);
    }

    public Plan getPlan(int id){
        Optional<Plan> optionalPlan =  planRepository.findById(id);
        if(optionalPlan.isEmpty()){
            throw new Error("plano não encontrado");
        }
        return optionalPlan.get();
    }

    public List<Plan> getAllPlans(){
        return planRepository.findAll();
    }

}
