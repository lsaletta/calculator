package com.sanitas.poc.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sanitas.poc.model.enums.EOperationType;
import lombok.Data;

import java.util.List;

@Data
public class Calculation {

    /**
     * Lista de parametros con los que se realizaran los calculos
     */
    @JsonProperty("parameters")
    private List<Double> parameters;

    /**
     * Parametro que nos indicara el tipo de calculo que se debe realizar
     */
    @JsonProperty("operation_type")
    private EOperationType operationType;
}
