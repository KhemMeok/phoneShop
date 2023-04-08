package com.khem.appspring.springphoneshop.controller;

import com.khem.appspring.springphoneshop.config.security.jwt.GetKeyFromYML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/key")
public class GetKeyController {

//    @Autowired
//    private  String getKey;

    @GetMapping("key")
    public ResponseEntity<?> GetKey(){
        return ResponseEntity.ok().body("");
    }

}
