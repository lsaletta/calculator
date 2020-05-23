package com.sanitas.poc.model.enums;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum ECalculationErrorType {
    INVALID_PARAMS_NUMBER("INVALID_PARAMS_NUMBER", "Invalid numbers of parameters"),
    INVALID_OPERATION_TYPE("INVALID_OPERATION_TYPE", "Invalid operation type"),
    INVALID_CALCULATION("INVALID_CALCULATION", "Invalid calculation");

    private String errorCode;
    private String description;

    public String getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }
}