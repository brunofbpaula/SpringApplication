package com.blackout.university.exceptions;

public class CourseFullException extends Exception{

    private String message;

    public CourseFullException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
