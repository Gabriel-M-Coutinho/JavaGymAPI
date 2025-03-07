package com.desert.gym.dtos.plan;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;


public class PlanCreateDto {


    @NotBlank(message = "O nome do plano é obrigatório")
    @Size(max = 100, message = "O nome do plano deve ter no máximo 100 caracteres")
    private String name;

    @Size(max = 255, message = "A descrição do plano deve ter no máximo 255 caracteres")
    private String description;

    @NotNull(message = "A duração do plano é obrigatória")
    @Min(value = 1, message = "A duração do plano deve ser pelo menos 1")
    private Integer duration;

    @NotNull(message = "O preço do plano é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço do plano deve ser maior que 0")
    @Digits(integer = 10, fraction = 2, message = "O preço do plano deve ter no máximo 10 dígitos inteiros e 2 decimais")
    private BigDecimal price;




    // Construtores
    public void PlanDTO() {
    }

    public void PlanDTO(int id, String name, String description, Integer duration, BigDecimal price ) {

        this.name = name;
        this.description = description;
        this.duration = duration;
        this.price = price;


    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }




}
