package com.sanitas.poc.ws.controller;

import com.sanitas.poc.core.service.CalculatorService;
import com.sanitas.poc.model.dto.CalculatorRequest;
import io.corp.calculator.TracerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;
    private TracerImpl tracer = new TracerImpl();


    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }


    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Double> calculator(
            @RequestBody CalculatorRequest request)
            throws Exception {
        Double result = calculatorService.calculate(request);

        tracer.trace(result);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
