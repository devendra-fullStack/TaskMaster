package com.taskmaster.controller.advice;




import com.taskmaster.exceptions.*;
import com.taskmaster.model.network.ResponseModel;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseModel<?> handleNotFound(TaskNotFoundException ex) {
        return ResponseModel.failure(ex.getMessage(),HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler({TaskCreationException.class, TaskUpdateException.class, TaskDeleteException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseModel<?> handleTaskOperationExceptions(RuntimeException ex) {
        return ResponseModel.failure(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseModel<?> handleDatabaseErrors(DataAccessException ex) {
        return ResponseModel.failure("Database error: " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseModel<?> handleGenericException(Exception ex) {
        return ResponseModel.failure("Something went wrong: " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}


