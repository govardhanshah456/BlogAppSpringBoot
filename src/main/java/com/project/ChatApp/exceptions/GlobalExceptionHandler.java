package com.project.ChatApp.exceptions;

import com.project.ChatApp.payloads.ApiResponse;

import jakarta.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse>resourceNotFoundException(ResourceNotFoundException ex){
        String mes=ex.getMessage();
        ApiResponse res=new ApiResponse(mes,false);
        return new ResponseEntity<ApiResponse>(res,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>>methodArgsNotValidException(MethodArgumentNotValidException ex){
        Map<String,String>map=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName=((FieldError)error).getField();
            String message=error.getDefaultMessage();
            map.put(fieldName,message);
        });
        return new ResponseEntity<Map<String,String>>(map,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String,String>>constraintsViolationException(ConstraintViolationException ex){
        Map<String,String>map=new HashMap<>();
        ex.getConstraintViolations().forEach((constraint)->{
        	System.out.print(constraint);
            String fieldName=constraint.getPropertyPath().toString().split("\\.")[2];;
            String message=constraint.getMessage();
            map.put(fieldName,message);
            
        });
        
        return new ResponseEntity<Map<String,String>>(map,HttpStatus.BAD_REQUEST);
    }
}
