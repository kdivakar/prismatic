package com.dkothandan.prismatic.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrismaticController {

    @RequestMapping(value = "/ping")
    public ResponseEntity<Object> ping() {
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
