package com.desert.gym.controllers;

import com.desert.gym.dtos.ApiResponse;
import com.desert.gym.dtos.plan.PlanCreateDto;
import com.desert.gym.models.Plan;
import com.desert.gym.services.PlanService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    PlanService planService;

    @PostMapping
    public ResponseEntity<ApiResponse<Plan>> createPlan (@RequestBody @Valid PlanCreateDto planCreateDto){
        Plan plan = planService.createPlan(planCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(
                new ApiResponse<Plan>(
                        HttpStatus.CREATED.value(),
                        "Plano criado com sucesso.",
                        plan));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Plan>> getPlan(@PathVariable int id){
        Plan plan  = planService.getPlan(id);
        return ResponseEntity.ok().body(new ApiResponse<Plan>(
                200,
                "Plano recuperado com sucesso.",
                plan
        ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Plan>>> getAllPlans(){
        List<Plan> plans = planService.getAllPlans();
        return ResponseEntity.ok().body(
                new ApiResponse<List<Plan>>(
                        200,
                        "Planos recuperados com sucesso.",
                        plans
                )
        );
    }




}
