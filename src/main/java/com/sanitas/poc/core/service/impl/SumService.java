package com.sanitas.poc.core.service.impl;

import com.sanitas.poc.core.service.Operable;
import com.sanitas.poc.core.service.Operation;
import com.sanitas.poc.model.dto.Calculation;
import com.sanitas.poc.model.enums.EOperationType;
import org.springframework.stereotype.Service;

@Service
@Operation
public class SumService implements Operable {
    @Override
    public EOperationType getOperationType() {
        return EOperationType.SUM;
    }

    @Override
    public Double run(Calculation calculation) {
        return calculation.getParameters().stream()
                .reduce(0D, (a, b) -> a + b);
    }
}
