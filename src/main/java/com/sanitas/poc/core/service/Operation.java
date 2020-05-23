package com.sanitas.poc.core.service;

import com.sanitas.poc.model.dto.Calculation;

public interface Operation {
    public Double run(Calculation calculation);
}
