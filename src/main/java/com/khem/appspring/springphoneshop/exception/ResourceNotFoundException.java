package com.khem.appspring.springphoneshop.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ResourceNotFoundException extends ApiException{

    private Integer resourceId;
    private String resourceName;

    public ResourceNotFoundException(String resourceName,Integer resourceId){
        super(HttpStatus.NOT_FOUND, String.format("%s not found for id=%d", resourceName,resourceId));
    }
}
