package com.project.ChatApp.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String fieldName;
    Long value;

    public ResourceNotFoundException(String resourceName,String fieldName,Long value){
        super(String.format("%s not found with %s : %s",resourceName,fieldName,value));
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.value=value;
    }
}
