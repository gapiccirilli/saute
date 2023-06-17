package com.angelopicc.saute.payload;

public class ItemDto {
    
    private long id;
    private String ingredientName;
    private double amount;
    private String measurementType;
    private int hours;
    private int minutes;
    private int seconds;
    
    public ItemDto(long id, String ingredientName, double amount, String measurementType, int hours, int minutes,
            int seconds) {
        this.id = id;
        this.ingredientName = ingredientName;
        this.amount = amount;
        this.measurementType = measurementType;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public ItemDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
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

    @Override
    public String toString() {
        return "ItemDto [id=" + id + ", ingredientName=" + ingredientName + ", amount=" + amount + ", measurementType="
                + measurementType + ", hours=" + hours + ", minutes=" + minutes + ", seconds=" + seconds + "]";
    }
}
