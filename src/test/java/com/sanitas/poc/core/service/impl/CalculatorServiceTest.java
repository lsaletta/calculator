package com.sanitas.poc.core.service.impl;

import com.sanitas.poc.core.exception.CalculationException;
import com.sanitas.poc.model.dto.Calculation;
import com.sanitas.poc.model.dto.CalculatorRequest;
import com.sanitas.poc.model.enums.EOperationType;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RunWith(JMockit.class)
public class CalculatorServiceTest {

    @Tested
    private CalculatorServiceImpl calculatorService;


    @Test
    public void calculate_when_is_sum_operation_return_parameters_sum() throws CalculationException {
        CalculatorRequest calculatorRequest = buildSumCalculatorRequest();
        Double result = calculatorService.calculate(calculatorRequest);
        Assert.assertEquals(Double.valueOf(36), result);
    }

    @Test
    public void calculate_when_is_subtract_operation_return_parameters_subtract() throws CalculationException {
        CalculatorRequest calculatorRequest = buildSubtractCalculatorRequest();
        Double result = calculatorService.calculate(calculatorRequest);
        Assert.assertEquals(Double.valueOf(12), result);
    }

    @Test
    public void calculate_when_number_elements_parameters_is_one_return_same_parameter() throws CalculationException {
        CalculatorRequest calculatorRequest = buildOneElementCalculatorRequest();
        Double result = calculatorService.calculate(calculatorRequest);
        Assert.assertEquals(Double.valueOf(24), result);
    }

    @Test(expected = CalculationException.class)
    public void calculate_when_number_elements_parameters_is_empty_or_null_throw_exception() throws CalculationException {
        CalculatorRequest calculatorRequest = buildEmptyParametersCalculatorRequest();
        Double result = calculatorService.calculate(calculatorRequest);
        Assert.assertEquals(Double.valueOf(24), result);
    }


    @Test(expected = CalculationException.class)
    public void calculate_when_number_operation_type_is_empty_or_null_throw_exception() throws CalculationException {
        CalculatorRequest calculatorRequest = buildEmptyOperationTypeCalculatorRequest();
        calculatorService.calculate(calculatorRequest);
    }


    private CalculatorRequest buildEmptyParametersCalculatorRequest() {
        CalculatorRequest calculatorRequest = new CalculatorRequest();
        List<Calculation> operations = new ArrayList<>();
        Calculation calculation = new Calculation();
        calculation.setOperationType(EOperationType.SUM);
        operations.add(calculation);
        calculatorRequest.setOperations(operations);
        return calculatorRequest;
    }

    private CalculatorRequest buildEmptyOperationTypeCalculatorRequest() {
        CalculatorRequest calculatorRequest = new CalculatorRequest();
        List<Calculation> operations = new ArrayList<>();
        Calculation calculation = new Calculation();
        calculation.setParameters(Arrays.asList(24D, 12D));
        operations.add(calculation);
        calculatorRequest.setOperations(operations);
        return calculatorRequest;
    }


    private CalculatorRequest buildOneElementCalculatorRequest() {
        CalculatorRequest calculatorRequest = new CalculatorRequest();
        List<Calculation> operations = new ArrayList<>();
        Calculation calculation = new Calculation();
        calculation.setParameters(Arrays.asList(24D));
        calculation.setOperationType(EOperationType.SUM);
        operations.add(calculation);
        calculatorRequest.setOperations(operations);
        return calculatorRequest;
    }

    private CalculatorRequest buildSubtractCalculatorRequest() {
        CalculatorRequest calculatorRequest = new CalculatorRequest();
        List<Calculation> operations = new ArrayList<>();
        Calculation calculation = new Calculation();
        calculation.setParameters(Arrays.asList(24D, 12D));
        calculation.setOperationType(EOperationType.SUBTRACT);
        operations.add(calculation);
        calculatorRequest.setOperations(operations);
        return calculatorRequest;
    }

    private CalculatorRequest buildSumCalculatorRequest() {
        CalculatorRequest calculatorRequest = new CalculatorRequest();
        List<Calculation> operations = new ArrayList<>();
        Calculation calculation = new Calculation();
        calculation.setParameters(Arrays.asList(24D, 12D));
        calculation.setOperationType(EOperationType.SUM);
        operations.add(calculation);
        calculatorRequest.setOperations(operations);
        return calculatorRequest;
    }
}