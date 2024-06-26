package com.blackout.university.exceptions;

import org.springframework.stereotype.Service;

@Service
public class ErrorMessage {
    private String error;
    private String description;

    public ErrorMessage() {
    }

    public ErrorMessage(String error, String description) {
        this.error = error;
        this.description = description;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
