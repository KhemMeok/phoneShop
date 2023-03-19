package com.khem.appspring.springphoneshop.config.security;

import lombok.Getter;

@Getter
public enum PermitionEnum {
    BRAND_READ("brands:read"),BRAND_WRITE("brands:write");
   
    private String description;

    private PermitionEnum(String description){
        this.description = description;
    }
}
  
