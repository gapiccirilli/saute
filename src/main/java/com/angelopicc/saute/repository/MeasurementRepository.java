package com.angelopicc.saute.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angelopicc.saute.entity.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    
}
