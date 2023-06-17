package com.angelopicc.saute.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item")
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int hours;

    private int minutes;

    private int seconds;

    @OneToOne
    private Ingredient ingredient;

    @OneToOne
    private Measurement measurement;

    public Item(long id, int hours, int minutes, int seconds, Ingredient ingredient, Measurement measurement) {
        this.id = id;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.ingredient = ingredient;
        this.measurement = measurement;
    }

    public Item() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", hours=" + hours + ", minutes=" + minutes + ", seconds=" + seconds + ", ingredient="
                + ingredient + ", measurement=" + measurement + "]";
    }
}
