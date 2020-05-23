package com.sanitas.poc.core.service.impl;

import com.google.common.collect.Iterables;
import com.google.common.util.concurrent.AtomicDouble;
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

    /**
     * Metodo que realiza calculos aritméticos
     *
     * @param calculatorRequest
     * @return
     * @throws CalculationException
     */
    public Double calculate(CalculatorRequest calculatorRequest) throws CalculationException {

        if (!CollectionUtils.isEmpty(calculatorRequest.getCalculations())) {
            //Para la POC, solo tendremos en cuenta el primer elemento de la lista de calculos.
            Calculation calculation = Iterables.getFirst(calculatorRequest.getCalculations(), null);
            checkCalculation(calculation);

            if (EOperationType.SUM.equals(calculation.getOperationType())) {
                return calculateSum(calculation);
            } else {
                return calculateSubtract(calculation);
            }

        } else {
            throw new CalculationException(ECalculationErrorType.INVALID_CALCULATION);
        }

    }

    /**
     * Realiza la operacion de suma
     *
     * @param calculation
     * @return
     */
    private Double calculateSum(Calculation calculation) {
        return calculation.getParameters().stream()
                .reduce(0D, (a, b) -> a + b);
    }

    /**
     * Realiza la operacion de resta
     *
     * @param calculation
     * @return
     */
    private Double calculateSubtract(Calculation calculation) {
        AtomicDouble atomicDouble = new AtomicDouble(0);

        calculation.getParameters().stream()
                .forEach(a -> {
                    if (atomicDouble.get() == 0D) {
                        atomicDouble.addAndGet(a);
                    } else {
                        atomicDouble.set(atomicDouble.get() - a);
                    }
                });

        return atomicDouble.get();
    }

    /**
     * Comprueba la validez de los parametros de entrada
     *
     * @param calculation
     * @throws CalculationException
     */
    private void checkCalculation(Calculation calculation) throws CalculationException {
        if (calculation.getOperationType() == null) {
            throw new CalculationException(ECalculationErrorType.INVALID_OPERATION_TYPE);
        } else if (CollectionUtils.isEmpty(calculation.getParameters())) {
            throw new CalculationException(ECalculationErrorType.INVALID_PARAMS_NUMBER);
        }
    }
}
