package com.sanitas.poc.core.service;

import com.sanitas.poc.model.dto.Calculation;
import com.sanitas.poc.model.enums.EOperationType;

public interface Operable {
    EOperationType getOperationType();

    public Double run(Calculation calculation);
}
