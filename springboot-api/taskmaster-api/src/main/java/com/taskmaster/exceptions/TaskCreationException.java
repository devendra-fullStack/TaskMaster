package com.taskmaster.exceptions;


public class TaskCreationException extends RuntimeException {
    public TaskCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
