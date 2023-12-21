package com.study.springsecuritystudy.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Slf4j(topic = "AuthTest")
public class AuthTestController {
    @GetMapping
    public ResponseEntity<String> test(){
        return new ResponseEntity<>("잘 들어 옵니다?", HttpStatus.OK);
    }
}
