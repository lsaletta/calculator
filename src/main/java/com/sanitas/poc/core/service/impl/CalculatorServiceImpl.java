package com.sanitas.poc.core.service.impl;

import com.sanitas.poc.core.exception.CalculationException;
import com.sanitas.poc.core.service.CalculatorService;
import com.sanitas.poc.model.dto.Calculation;
import com.sanitas.poc.model.dto.CalculatorRequest;
import com.sanitas.poc.model.enums.ECalculationErrorType;
import com.sanitas.poc.model.enums.EOperationType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    private static final int FIRTS_ELEMENT = 0;
    private static final int SECOND_ELEMENT = 1;


    public Double calculate(CalculatorRequest calculatorRequest) throws CalculationException {

        if (!CollectionUtils.isEmpty(calculatorRequest.getOperations())) {
            Calculation calculation = calculatorRequest.getOperations().get(FIRTS_ELEMENT);
            checkCalculation(calculation);

            if (EOperationType.SUM.equals(calculation.getOperationType())) {
                return calculation.getParameters().stream()
                        .reduce(0D, (a, b) -> a + b);
            } else {

                final double[] initialValue = {0D};

                calculation.getParameters().stream()
                        .forEach(a -> {
                            if (initialValue[0] == 0D) {
                                initialValue[0] = a;
                            } else {
                                initialValue[0] = initialValue[0] - a;
                            }
                        });

                return initialValue[0];

            }

        } else {
            throw new CalculationException(ECalculationErrorType.INVALID_CALCULATION);
        }

    }

    private void checkCalculation(Calculation calculation) throws CalculationException {
        if (calculation.getOperationType() == null) {
            throw new CalculationException(ECalculationErrorType.INVALID_OPERATION_TYPE);
        } else if (CollectionUtils.isEmpty(calculation.getParameters())) {
            throw new CalculationException(ECalculationErrorType.INVALID_PARAMS_NUMBER);
        }
    }
}
