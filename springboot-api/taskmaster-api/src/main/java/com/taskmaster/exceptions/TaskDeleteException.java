package com.taskmaster.exceptions;

public class TaskDeleteException extends RuntimeException{
    public TaskDeleteException(String message,Throwable t){
        super(message,t);
    }
}
