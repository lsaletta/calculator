package com.sanitas.poc.ws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanitas.poc.CalculatorApplication;
import com.sanitas.poc.model.dto.Calculation;
import com.sanitas.poc.model.dto.CalculatorRequest;
import com.sanitas.poc.model.enums.EOperationType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CalculatorApplication.class})
@WebAppConfiguration
public class CalculatorControllerTest {


    @Autowired
    private WebApplicationContext wac;


    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getSumCalculation() throws Exception {

        CalculatorRequest calculatorRequest = buildSumCalculatorRequest();
        mockMvc.perform(MockMvcRequestBuilders
                .post("/calculator")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(calculatorRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(44D));

    }

    @Test
    public void getSubtractCalculation() throws Exception {

        CalculatorRequest calculatorRequest = buildSubtractCalculatorRequest();
        mockMvc.perform(MockMvcRequestBuilders
                .post("/calculator")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(calculatorRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(4D));

    }

    private CalculatorRequest buildSubtractCalculatorRequest() {
        CalculatorRequest calculatorRequest = new CalculatorRequest();
        List<Calculation> operations = new ArrayList<>();
        Calculation calculation = new Calculation();
        calculation.setParameters(Arrays.asList(24D, 20D));
        calculation.setOperationType(EOperationType.SUBTRACT);
        operations.add(calculation);
        calculatorRequest.setOperations(operations);
        return calculatorRequest;
    }

    private CalculatorRequest buildSumCalculatorRequest() {
        CalculatorRequest calculatorRequest = new CalculatorRequest();
        List<Calculation> operations = new ArrayList<>();
        Calculation calculation = new Calculation();
        calculation.setParameters(Arrays.asList(24D, 20D));
        calculation.setOperationType(EOperationType.SUM);
        operations.add(calculation);
        calculatorRequest.setOperations(operations);
        return calculatorRequest;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}