package com.angelopicc.saute.service.impl;

import org.springframework.stereotype.Service;

import com.angelopicc.saute.entity.Measurement;
import com.angelopicc.saute.service.MeasurementService;

@Service
public class StandardMeasurementService implements MeasurementService {


    public StandardMeasurementService() {
    }

    @Override
    public Measurement createMeasurement(double amount, String measurementType) {
        Measurement measurement = new Measurement();
        measurement.setAmount(amount);
        measurement.setMeasurementType(measurementType);

        return measurement;
    }

}
