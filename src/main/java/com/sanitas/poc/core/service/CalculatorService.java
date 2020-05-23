package com.sanitas.poc.core.service;

import com.sanitas.poc.core.exception.CalculationException;
import com.sanitas.poc.model.dto.CalculatorRequest;
import org.springframework.stereotype.Service;

@Service
public interface CalculatorService {

    /**
     * Metodo que realiza calculos aritméticos
     *
     * @param calculatorRequest
     * @return
     * @throws CalculationException
     */
    public Double calculate(CalculatorRequest calculatorRequest) throws CalculationException;
}
