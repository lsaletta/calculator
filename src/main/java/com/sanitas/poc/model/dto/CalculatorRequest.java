package com.sanitas.poc.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CalculatorRequest {

    /**
     * Lista de calculos que se pueden realizar
     */
    @JsonProperty("calculations")
    private List<Calculation> calculations;

}
