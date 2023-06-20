package com.angelopicc.saute.service.impl;

import org.springframework.stereotype.Service;

import com.angelopicc.saute.entity.Item;
import com.angelopicc.saute.entity.Measurement;
import com.angelopicc.saute.repository.MeasurementRepository;
import com.angelopicc.saute.service.MeasurementService;

@Service
public class StandardMeasurementService implements MeasurementService {

    private MeasurementRepository measurementRepository;

    public StandardMeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Override
    public Measurement createMeasurement(double amount, String measurementType) {
        Measurement measurement = new Measurement();
        measurement.setAmount(amount);
        measurement.setMeasurementType(measurementType);

        return measurement;
    }

}
