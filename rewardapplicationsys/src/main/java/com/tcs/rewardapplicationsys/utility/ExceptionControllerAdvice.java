package com.tcs.rewardapplicationsys.utility;

import com.tcs.rewardapplicationsys.exception.RewardException;
import jakarta.validation.ConstraintViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    private static final Logger log = LogManager.getLogger(ExceptionControllerAdvice.class);
    @Autowired
    Environment environment;

    @ExceptionHandler(RewardException.class)
    public ResponseEntity<ErrorInfo> meetingSchedulerExceptionHandler (RewardException exception){
        ErrorInfo errorInfo=new ErrorInfo();
        errorInfo.setExceptionMessage(exception.getMessage());
        errorInfo.setErrorCode("E001");
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> validatorExceptionHandler(MethodArgumentNotValidException exception ){
        ErrorInfo errorInfo=new ErrorInfo();
        StringBuilder messages = new StringBuilder();
        exception.getBindingResult().getFieldErrors().forEach(error -> { messages.append(error.getDefaultMessage()).append("; "); });
        errorInfo.setExceptionMessage(messages.toString().trim());
        errorInfo.setErrorCode("E003");
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorInfo> validatorExceptionHandler(ConstraintViolationException exception ){
        ErrorInfo errorInfo=new ErrorInfo();
        StringBuilder messages = new StringBuilder(); exception.getConstraintViolations().forEach(violation -> { messages.append(violation.getMessage()).append("; "); });
        errorInfo.setExceptionMessage(messages.toString().trim());
        errorInfo.setErrorCode("E004");
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exception){
        ErrorInfo errorInfo=new ErrorInfo();
        errorInfo.setExceptionMessage(exception.getMessage());
        errorInfo.setErrorCode("E002");
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

}

