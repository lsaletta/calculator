package com.sanitas.poc.core.service.impl;

import com.google.common.util.concurrent.AtomicDouble;
import com.sanitas.poc.core.service.Operable;
import com.sanitas.poc.core.service.Operation;
import com.sanitas.poc.model.dto.Calculation;
import com.sanitas.poc.model.enums.EOperationType;
import org.springframework.stereotype.Service;

@Service
@Operation
public class SubtractService implements Operable {
    @Override
    public EOperationType getOperationType() {
        return EOperationType.SUBTRACT;
    }
    @Override
    public Double run(Calculation calculation) {
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
}
