package com.sanitas.poc.core.service.impl;

import com.sanitas.poc.core.service.Operation;
import com.sanitas.poc.model.dto.Calculation;

public class SumService implements Operation {
    @Override
    public Double run(Calculation calculation) {
        return calculation.getParameters().stream()
                .reduce(0D, (a, b) -> a + b);
    }
}
