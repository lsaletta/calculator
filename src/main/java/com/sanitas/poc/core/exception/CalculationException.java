package com.sanitas.poc.core.exception;

import com.sanitas.poc.model.enums.ECalculationErrorType;

public class CalculationException extends Exception {

    private ECalculationErrorType errorType;

    public ECalculationErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ECalculationErrorType errorType) {
        this.errorType = errorType;
    }

    public CalculationException(ECalculationErrorType errorType) {
        this.errorType = errorType;
    }
}
