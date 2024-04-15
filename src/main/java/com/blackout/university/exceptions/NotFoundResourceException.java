package com.blackout.university.exceptions;

public class NotFoundResourceException extends Throwable {
    private String message;
    public NotFoundResourceException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
