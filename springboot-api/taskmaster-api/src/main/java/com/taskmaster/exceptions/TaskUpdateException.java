package com.taskmaster.exceptions;

public class TaskUpdateException extends RuntimeException{
    public TaskUpdateException(String message,Throwable t){
        super(message,t);
    }
}
