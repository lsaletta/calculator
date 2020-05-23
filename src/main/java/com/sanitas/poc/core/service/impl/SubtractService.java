package com.sanitas.poc.core.service.impl;

import com.google.common.util.concurrent.AtomicDouble;
import com.sanitas.poc.core.service.Operation;
import com.sanitas.poc.model.dto.Calculation;

public class SubtractService implements Operation {
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
