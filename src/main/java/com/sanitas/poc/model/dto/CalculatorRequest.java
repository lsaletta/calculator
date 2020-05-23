package com.sanitas.poc.model.dto;

import lombok.Data;

import java.util.List;

/**
 *
 */
@Data
public class CalculatorRequest {

    private List<Calculation> operations;

}
