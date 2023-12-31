package com.angelopicc.saute.payload;

import java.time.LocalDateTime;

public class Error {
    
    private LocalDateTime dateTime;
    private String message;
    private String description;

    public Error(LocalDateTime dateTime, String message, String description) {
        this.dateTime = dateTime;
        this.message = message;
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
