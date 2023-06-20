package com.angelopicc.saute.service;

import com.angelopicc.saute.entity.Measurement;

public interface MeasurementService {
    
    Measurement createMeasurement(double amount, String measurementType);
}
