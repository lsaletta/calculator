package com.sanitas.poc.ws.controller;

import com.sanitas.poc.model.dto.CalculatorRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/calculator")
public class CalculatorController {


    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Double> calculator(
            @RequestBody CalculatorRequest request)
            throws Exception {
        return new ResponseEntity<>(12D, HttpStatus.OK);
    }

}
