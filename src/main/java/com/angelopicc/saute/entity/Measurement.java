package com.angelopicc.saute.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int amount;

    private String measurementType;

    @OneToOne(mappedBy = "measurement")
    private Item item;

    public Measurement(long id, int amount, String measurementType, Item item) {
        this.id = id;
        this.amount = amount;
        this.measurementType = measurementType;
        this.item = item;
    }

    public Measurement() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Measurement [id=" + id + ", amount=" + amount + ", measurementType=" + measurementType + ", item="
                + item + "]";
    }
}
