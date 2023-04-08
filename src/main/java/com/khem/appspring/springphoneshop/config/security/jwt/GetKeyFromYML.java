package com.khem.appspring.springphoneshop.config.security.jwt;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Data
public class GetKeyFromYML {

    private final String key;

    public GetKeyFromYML( @Value("${server.key}") String key){
        this.key= key;
    }
}
